package com.project.lucena.redis.repository

import com.project.lucena.redis.model.Chuck
import com.project.lucena.redis.repository.mapper.ChuckMapper
import org.springframework.cache.annotation.Cacheable
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class Repository(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) {
    private val SAVE_CHUCK = """
        INSERT INTO chuckValues(id, created_at, value)
        VALUES (:id, :created_at, :value)
    """.trimIndent()

    private val SELECT_CHUCKS = """
        SELECT * FROM chuckValues
    """.trimIndent()

    private val SELECT_CHUCK_BY_ID = """
        SELECT * FROM chuckValues
        WHERE id = :id
    """.trimIndent()

    private val DELETE_CHUCK_BY_ID = """
        DELETE FROM chuckValues
        WHERE id = :id
    """.trimIndent()

    private val UPDATE_CHUCK_BY_ID = """
        UPDATE chuckValues
        SET id = :newId, created_at = :created_at, value = :value
        WHERE id = :id
    """.trimIndent()

    fun saveChuckById(id: String, date: String, value: String) {
        val params = mapOf(
            "id" to id,
            "created_at" to date,
            "value" to value
        )
        namedParameterJdbcTemplate.update(SAVE_CHUCK, params)
    }

    fun getAllChucks(): List<Chuck> {
        return namedParameterJdbcTemplate.query(SELECT_CHUCKS, ChuckMapper())
    }
    @Cacheable("getChuckById")
    fun getChuckById(id: String): Chuck? {
        return namedParameterJdbcTemplate.query(SELECT_CHUCK_BY_ID, mapOf("id" to id), ChuckMapper()).firstOrNull()
    }

    fun deleteChuckById(id: String) {
        namedParameterJdbcTemplate.update(DELETE_CHUCK_BY_ID, mapOf("id" to id))
    }

    fun updateChuckById(id: String, chuck: Chuck) {
        val params = mapOf(
            "newId" to id + "ALTER",
            "id" to id,
            "created_at" to chuck.created_at,
            "value" to chuck.value
        )
        namedParameterJdbcTemplate.update(UPDATE_CHUCK_BY_ID, params)
    }
    fun validateChuck(id: String): Boolean {
        return namedParameterJdbcTemplate.query(SELECT_CHUCK_BY_ID, mapOf("id" to id), ChuckMapper()).isNotEmpty()
    }
}