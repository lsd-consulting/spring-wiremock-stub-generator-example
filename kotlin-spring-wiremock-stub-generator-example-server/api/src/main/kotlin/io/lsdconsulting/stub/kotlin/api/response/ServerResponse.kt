package io.lsdconsulting.stub.kotlin.api.response

import java.time.ZonedDateTime

data class ServerResponse (
    var id: String,
    var message: String?,
    var author: Author,
    var created: ZonedDateTime?
)
