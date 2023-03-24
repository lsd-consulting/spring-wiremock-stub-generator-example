package io.lsdconsulting.stub.app.controller

import io.lsdconsulting.stub.annotation.GenerateWireMockStub
import io.lsdconsulting.stub.api.response.Author
import io.lsdconsulting.stub.api.response.ServerResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@GenerateWireMockStub
@RestController
@RequestMapping("/kotlinServerResource")
class KotlinController {

    @GetMapping("/123")
    fun getData(): ServerResponse {
        return ServerResponse(
            id = "someId",
            author = Author(name = "author"),
            message = "message",
            created = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault())
        )
    }
}