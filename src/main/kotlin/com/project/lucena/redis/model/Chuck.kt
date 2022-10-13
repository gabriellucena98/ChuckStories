package com.project.lucena.redis.model

data class Chuck(
    val created_at: String,
    val id: String,
    val update_at: String? = null,
    val value: String
)
