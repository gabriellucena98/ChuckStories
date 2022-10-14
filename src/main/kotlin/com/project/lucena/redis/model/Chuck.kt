package com.project.lucena.redis.model

import java.io.Serializable

data class Chuck(
    val created_at: String,
    val id: String,
    val update_at: String? = null,
    val value: String
): Serializable
