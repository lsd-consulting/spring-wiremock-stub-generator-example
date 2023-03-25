package io.lsdconsulting.stub.component.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import io.cucumber.java8.En;
import io.lsdconsulting.stub.api.response.Response;
import io.lsdconsulting.stub.app.controller.JavaControllerStub;
import io.lsdconsulting.stub.client.Client;
import io.lsdconsulting.stub.java.api.response.Author;
import io.lsdconsulting.stub.java.api.response.ServerResponse;
import org.springframework.http.HttpStatus;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class JavaClientSteps implements En {
    private final String name = randomAlphabetic(10);

    private final JavaControllerStub javaControllerStub = new JavaControllerStub(new ObjectMapper());

    private Response response;
    private HttpStatus httpStatus;

    public JavaClientSteps(Client client) {
        Given("^the java client is ready to accept requests$", () -> {
            javaControllerStub.getData(ServerResponse.builder().id("id").author(Author.builder().name(name).build()).build());
        });

        When("^a request is sent to the client2$", () -> {
            try {
                response = client.getData2("someId");
            } catch (final FeignException e) {
                httpStatus = HttpStatus.valueOf(e.status());
            }
        });

        Then("^the data from the server's stub2 is returned$", () -> {
            Response expectedResponse = Response.builder()
                    .id("id")
                    .name(name)
                    .build();

            assertThat(response, equalTo(expectedResponse));
        });

        Then("^the HTTP status code INTERNAL_SERVER_ERROR is returned by client2$", () -> assertThat(httpStatus, equalTo(INTERNAL_SERVER_ERROR)));
    }
}
