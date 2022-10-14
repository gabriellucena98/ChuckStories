package com.project.lucena.redis.health

import java.io.Serializable

open class GenericErrorJSONResponse(
    private var message: String?,
    private var timestamp: Long?,
    private var code: String?
): Serializable {
    fun GenericErrorJSONResponse(message: String, code: String) {
        this.message = message
        this.timestamp = System.currentTimeMillis()
        this.code = code
    }
//
//    fun getMessage(): String {return this.message}
//    fun getTimestamp(): Long {return this.timestamp}
//    fun getCode(): String {return this.code} //em kotlin não precisamos de Getters and Setters, pensar em tirar depois
}