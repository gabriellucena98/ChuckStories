package com.project.lucena.redis.repository.mapper

import com.project.lucena.redis.model.Chuck
import java.sql.ResultSet
import org.springframework.jdbc.core.RowMapper

class ChuckMapper: RowMapper<Chuck> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Chuck {
        return Chuck(
            id = rs.getString("id"),
            created_at = rs.getString("created_at"),
            value = rs.getString("value")
        )
    }
}