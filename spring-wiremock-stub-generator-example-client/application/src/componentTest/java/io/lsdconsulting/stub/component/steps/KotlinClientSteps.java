package io.lsdconsulting.stub.component.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import io.cucumber.java8.En;
import io.lsdconsulting.stub.api.response.Response;
import io.lsdconsulting.stub.app.controller.KotlinControllerStub;
import io.lsdconsulting.stub.client.Client;
import io.lsdconsulting.stub.kotlin.api.response.Author;
import io.lsdconsulting.stub.kotlin.api.response.ServerResponse;
import org.springframework.http.HttpStatus;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class KotlinClientSteps implements En {
    private final String name = randomAlphabetic(10);

    private final KotlinControllerStub kotlinControllerStub = new KotlinControllerStub(new ObjectMapper());

    private Response response;
    private HttpStatus httpStatus;

    public KotlinClientSteps(Client client) {
        Given("^the kotlin client is ready to accept requests$", () -> {
            kotlinControllerStub.getData(new ServerResponse("id", null, new Author(name), null));
        });

        When("^a request is sent to the client1$", () -> {
            try {
                response = client.getData1("someId");
            } catch (final FeignException e) {
                httpStatus = HttpStatus.valueOf(e.status());
            }
        });

        Then("^the data from the server's stub1 is returned$", () -> {
            Response expectedResponse = Response.builder()
                    .id("id")
                    .name(name)
                    .build();

            assertThat(response, equalTo(expectedResponse));
        });

        Then("^the HTTP status code INTERNAL_SERVER_ERROR is returned by client1$", () -> assertThat(httpStatus, equalTo(INTERNAL_SERVER_ERROR)));
    }
}
