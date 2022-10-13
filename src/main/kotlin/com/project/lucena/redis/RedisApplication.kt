package com.project.lucena.redis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class RedisApplication

fun main(args: Array<String>) {
	runApplication<RedisApplication>(*args)
}
