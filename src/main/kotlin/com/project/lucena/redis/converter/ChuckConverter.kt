package com.project.lucena.redis.converter

import com.project.lucena.redis.dto.ChuckDTO
import com.project.lucena.redis.model.Chuck
import java.time.LocalDateTime

object ChuckConverter {

    fun toDTO(chuck: Chuck): ChuckDTO {
        return with(chuck) {
            ChuckDTO(
                id = id,
                created_at = created_at,
                value = value
            )
        }
    }

    fun toDomain(chuckDTO: ChuckDTO): Chuck {
        return with(chuckDTO) {
            Chuck(
                id = id,
                created_at = created_at,
                update_at = LocalDateTime.now().toString(),
                value = value
            )
        }
    }
}