package com.project.lucena.redis.exception

import java.lang.RuntimeException

sealed class NotFoundException(message: String, val Id: String, val code: String) : RuntimeException("$message: $Id")

class ChuckNotFoundException(message: String, Id: String, code: String) : NotFoundException(message, Id, code)
