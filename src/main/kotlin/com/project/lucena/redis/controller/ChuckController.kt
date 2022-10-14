package com.project.lucena.redis.controller

import com.project.lucena.redis.converter.ChuckConverter
import com.project.lucena.redis.dto.ChuckDTO
import com.project.lucena.redis.service.ChuckService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chuck")
class ChuckController(
    private val chuckService: ChuckService
) {

    @GetMapping("/random")
    fun chuckRandom(): ChuckDTO {
        return ChuckConverter.toDTO(chuckService.chuckRandom())
    }

    @GetMapping("/{id}")
    fun chuckById(@PathVariable("id") id: String): ChuckDTO {
        return ChuckConverter.toDTO(chuckService.chuckById(id))
    }

    @PostMapping("/db/{id}")
    @CacheEvict(value = ["getChuckById"])
    fun saveChuckById(@PathVariable("id") id: String) {
        chuckService.saveChuckById(id)
    }

    @GetMapping("/db")
    fun getAllChucks(): List<ChuckDTO> {
        return chuckService.getAllChucks().map { ChuckConverter.toDTO(it) }
    }

    @GetMapping("/db/{id}")
    fun getChuckById(@PathVariable("id") id: String): ChuckDTO? {
        return chuckService.getChuckById(id)?.let { ChuckConverter.toDTO(it) }
    }

    @DeleteMapping("/db/{id}")
    @CacheEvict(value = ["getChuckById"])
    fun deleteChuckById(@PathVariable("id") id: String) {
        chuckService.deleteChuckById(id)
    }
    //tem muito para melhorar esse patch
    @PatchMapping("db/{id}")
    @CacheEvict(value = ["getChuckById"])
    fun updateChuckById(
        @PathVariable("id") id: String,
        @RequestBody chuckDTO: ChuckDTO
    ) {
        chuckService.updateChuckById(id, ChuckConverter.toDomain(chuckDTO))
    }
}