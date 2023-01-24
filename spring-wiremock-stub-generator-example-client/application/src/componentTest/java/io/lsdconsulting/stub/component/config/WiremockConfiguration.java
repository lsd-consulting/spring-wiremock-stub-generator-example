package io.lsdconsulting.stub.component.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@TestConfiguration
public class WiremockConfiguration {

    @Value("${wiremock.server.port}")
    private int wiremockPort;

    @Bean
    public WireMockServer wiremockserver() {
        return new WireMockServer(options().port(wiremockPort));
    }
}
