package io.lsdconsulting.stub.app.controller;

import io.lsdconsulting.stub.annotation.GenerateWireMockStub;
import io.lsdconsulting.stub.java.api.response.Author;
import io.lsdconsulting.stub.java.api.response.ServerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.time.Instant.EPOCH;

@GenerateWireMockStub
@RestController
@RequestMapping("/javaServerResource")
public class JavaController {

    @GetMapping("/123")
    public ServerResponse getData() {
        return ServerResponse.builder()
                .id("someId")
                .author(Author.builder().name("author").build())
                .message("message")
                .created(ZonedDateTime.ofInstant(EPOCH, ZoneId.systemDefault()))
                .build();
    }
}
