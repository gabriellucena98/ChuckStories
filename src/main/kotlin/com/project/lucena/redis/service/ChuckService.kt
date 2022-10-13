package com.project.lucena.redis.service

import com.project.lucena.redis.client.ChuckClient
import com.project.lucena.redis.enum.ChuckError
import com.project.lucena.redis.exception.AlreadyExistsException
import com.project.lucena.redis.exception.ChuckNotFoundException
import com.project.lucena.redis.model.Chuck
import com.project.lucena.redis.repository.Repository
import org.springframework.stereotype.Service

@Service
class ChuckService(
    private val chuckClient: ChuckClient,
    private val repository: Repository
) {
    fun chuckRandom(): Chuck {
        return chuckClient.chuckRandom()
    }

    fun chuckById(id: String): Chuck {
        return chuckClient.chuckById(id)
    }

    fun saveChuckById(id: String) {
        if (validateChuck(id) != null) {
            throw AlreadyExistsException(
                message = ChuckError.CHUCK_ALREADY_EXISTS.message,
                Ids = id,
                code = ChuckError.CHUCK_ALREADY_EXISTS.code
            )
        }

        val chuckValue = chuckById(id)
        repository.saveChuckById(
            id = id,
            date = chuckValue.created_at,
            value = chuckValue.value
        )
    }

    fun getAllChucks(): List<Chuck> {
        return repository.getAllChucks()
    }

    fun getChuckById(id: String): Chuck? {
        return repository.getChuckById(id) ?: throw ChuckNotFoundException(
            message = ChuckError.CHUCK_NOT_FOUND.message,
            Id = id,
            code = ChuckError.CHUCK_NOT_FOUND.code
        )
    }

    fun deleteChuckById(id: String) {
        getChuckById(id)
        repository.deleteChuckById(id)
    }

    fun validateChuck(id: String): Chuck? {
        return repository.getChuckById(id)
    }

    fun updateChuckById(id: String, chuck: Chuck) {
        getChuckById(id)
        repository.updateChuckById(id, chuck)
    }
}