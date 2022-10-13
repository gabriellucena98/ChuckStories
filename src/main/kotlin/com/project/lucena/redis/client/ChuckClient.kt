package com.project.lucena.redis.client

import com.project.lucena.redis.model.Chuck
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(name = "ChuckNorris", url = "https://api.chucknorris.io/jokes")
interface ChuckClient {

    @RequestMapping(method = [RequestMethod.GET], path = ["/random"] )
    fun chuckRandom(): Chuck

    @RequestMapping(method = [RequestMethod.GET], path = ["/{id}"])
    fun chuckById(@PathVariable("id") id: String): Chuck
}