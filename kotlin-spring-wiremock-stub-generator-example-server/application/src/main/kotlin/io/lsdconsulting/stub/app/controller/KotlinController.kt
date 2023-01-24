package io.lsdconsulting.stub.app.controller

import io.lsdconsulting.stub.api.response.Author
import io.lsdconsulting.stub.api.response.ServerResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@Validated
@RestController("/kotlinServerResource")
class KotlinController {

    @GetMapping("/123")
    fun getData(): ServerResponse {
        return ServerResponse.builder()
            .id("someId")
            .author(Author.builder().name("author").build())
            .message("message")
            .created(ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault()))
            .build()
    }
}