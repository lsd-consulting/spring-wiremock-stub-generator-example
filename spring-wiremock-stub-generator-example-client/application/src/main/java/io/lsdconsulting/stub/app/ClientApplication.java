package io.lsdconsulting.stub.app;

import io.lsdconsulting.stub.client.JavaServerClient;
import io.lsdconsulting.stub.client.KotlinServerClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {KotlinServerClient.class, JavaServerClient.class})
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
