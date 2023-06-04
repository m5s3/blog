package com.m5s3.blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.m5s3")
class BlogApplication

fun main(args: Array<String>) {
    runApplication<BlogApplication>(*args)
}
