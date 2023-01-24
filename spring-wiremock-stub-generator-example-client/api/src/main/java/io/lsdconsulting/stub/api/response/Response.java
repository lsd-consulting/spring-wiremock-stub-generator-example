package io.lsdconsulting.stub.api.response;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Response {
    String id;
    String name;
    Integer age;
}
