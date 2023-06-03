package com.m5s3.blog.service.exception
abstract class BusinessLogicException: RuntimeException {
    constructor(): super()
    constructor(message: String): super(message)
}