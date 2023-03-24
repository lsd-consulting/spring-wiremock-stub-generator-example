package io.lsdconsulting.stub.api.response

import java.time.ZonedDateTime

data class ServerResponse (
    private var id: String,
    private var message: String,
    private var author: Author,
    private var created: ZonedDateTime
)
