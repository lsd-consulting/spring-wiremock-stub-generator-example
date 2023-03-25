package io.lsdconsulting.stub.component.steps;

import com.github.tomakehurst.wiremock.WireMockServer;
import feign.FeignException;
import io.cucumber.java8.En;
import io.cucumber.spring.CucumberContextConfiguration;
import io.lsdconsulting.stub.api.response.Response;
import io.lsdconsulting.stub.app.ClientApplication;
import io.lsdconsulting.stub.client.Client;
import io.lsdconsulting.stub.component.config.WiremockConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
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
    public ClientSteps(WireMockServer wireMockServer) {
        Before(() -> {
            wireMockServer.start();
            configureFor("localhost", wireMockServer.port());
            wireMockServer.resetAll();
        });
        After(wireMockServer::stop);
    }
}
