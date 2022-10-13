package com.project.lucena.redis.exception

open class AlreadyExistsException(message: String, val Ids: List<String>, val code: String) : RuntimeException("$message: $Ids") {
    constructor(message: String, Ids: String, code: String) : this(message, listOf(Ids), code)
}