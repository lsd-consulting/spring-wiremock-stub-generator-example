package io.lsdconsulting.stub.client;

import io.lsdconsulting.stub.api.response.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "javaServerClient", url = "${server.url}")
public interface JavaServerClient {

    @GetMapping("/javaServerResource/123")
    ServerResponse getServerData();

}
