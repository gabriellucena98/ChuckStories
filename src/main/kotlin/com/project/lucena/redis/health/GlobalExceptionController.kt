package com.project.lucena.redis.health

import com.project.lucena.redis.exception.AlreadyExistsException
import com.project.lucena.redis.exception.ChuckNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionController(
) {
    private val logger = LoggerFactory.getLogger(GlobalExceptionController::class.java)

    @ExceptionHandler(AlreadyExistsException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    fun handlerAlreadyExistsException(exception: AlreadyExistsException): String {
        logger.error("That Chuck Already Exists.", exception)
        return "${exception.message} ${exception.Ids} \n ${exception.code}"
    }

    @ExceptionHandler(ChuckNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handlerChuckNotFoundException(exception: ChuckNotFoundException): String {
        logger.error("Chuck Does Not Found.", exception)
        return "${exception.message} ${exception.Id} \n ${exception.code}"
    }
}