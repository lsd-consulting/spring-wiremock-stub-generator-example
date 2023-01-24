package io.lsdconsulting.stub.component.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import feign.FeignException;
import io.cucumber.java8.En;
import io.cucumber.spring.CucumberContextConfiguration;
import io.lsdconsulting.stub.api.response.Author;
import io.lsdconsulting.stub.api.response.Response;
import io.lsdconsulting.stub.api.response.ServerResponse;
import io.lsdconsulting.stub.app.ClientApplication;
import io.lsdconsulting.stub.app.controller.JavaControllerStub;
import io.lsdconsulting.stub.app.controller.KotlinControllerStub;
import io.lsdconsulting.stub.client.Client;
import io.lsdconsulting.stub.component.config.WiremockConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@SpringBootTest(webEnvironment = DEFINED_PORT, classes = {ClientApplication.class})
@CucumberContextConfiguration
@ActiveProfiles("test")
@Import({WiremockConfiguration.class})
@EnableFeignClients(clients = Client.class)
public class ClientSteps implements En {
    private final String name1 = randomAlphabetic(10);
    private final String name2 = randomAlphabetic(10);
    private final Integer age = nextInt(0, 125);

    private final KotlinControllerStub kotlinControllerStub = new KotlinControllerStub(new ObjectMapper());
    private final JavaControllerStub javaControllerStub = new JavaControllerStub(new ObjectMapper());

    private Response response;
    private HttpStatus httpStatus;

    public ClientSteps(Client client, WireMockServer wireMockServer) {
        Before(() -> {
            wireMockServer.start();
            configureFor("localhost", wireMockServer.port());
            wireMockServer.resetAll();
        });
        After(wireMockServer::stop);

        Given("^the client is ready to accept requests$", () -> {
            kotlinControllerStub.getGetData(ServerResponse.builder().id("id").author(Author.builder().name(name1).build()).build());
            javaControllerStub.getGetData(ServerResponse.builder().id("id").author(Author.builder().name(name2).build()).build());
        });

        When("^a request is sent to the client1$", () -> {
            try {
                response = client.getData1("someId");
            } catch (final FeignException e) {
                httpStatus = HttpStatus.valueOf(e.status());
            }
        });

        When("^a request is sent to the client2$", () -> {
            try {
                response = client.getData2("someId");
            } catch (final FeignException e) {
                httpStatus = HttpStatus.valueOf(e.status());
            }
        });

        Then("^the data from the server's stub1 is returned$", () -> {
            Response expectedResponse = Response.builder()
                    .id("id")
                    .name(name1)
                    .build();

            assertThat(response, equalTo(expectedResponse));
        });

        Then("^the data from the server's stub2 is returned$", () -> {
            Response expectedResponse = Response.builder()
                    .id("id")
                    .name(name2)
                    .build();

            assertThat(response, equalTo(expectedResponse));
        });

        Then("^the HTTP status code INTERNAL_SERVER_ERROR is returned$", () -> assertThat(httpStatus, equalTo(INTERNAL_SERVER_ERROR)));

    }
}
