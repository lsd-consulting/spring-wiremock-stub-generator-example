package io.lsdconsulting.stub.kotlin.client

import io.lsdconsulting.stub.kotlin.api.response.ServerResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "kotlinServerClient", url = "\${server.url}")
interface KotlinServerClient {
    @GetMapping("/kotlinServerResource/123")
    fun serverData(): ServerResponse
}
