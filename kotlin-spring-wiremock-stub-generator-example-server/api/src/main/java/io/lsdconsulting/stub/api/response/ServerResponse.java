package io.lsdconsulting.stub.api.response;

import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;

@Builder
@Value
public class ServerResponse {
    String id;
    String message;
    Author author;
    ZonedDateTime created;
}
