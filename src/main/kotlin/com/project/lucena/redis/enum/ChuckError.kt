package com.project.lucena.redis.enum

enum class ChuckError(
    val message: String,
    val code: String
) {
    CHUCK_ALREADY_EXISTS(message = "That ChuckId already exists in the database: ", code = "error.conflict.chuck-already-exists"),
    CHUCK_NOT_FOUND(message = "That ChuckId was not found in the database: ", code = "error.not-found.chuck-does-not-found")
}