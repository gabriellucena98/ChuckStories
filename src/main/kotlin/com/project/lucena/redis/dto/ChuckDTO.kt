package com.project.lucena.redis.dto

import java.io.Serializable

data class ChuckDTO(
    val created_at: String,
    val id: String,
    val value: String
): Serializable
