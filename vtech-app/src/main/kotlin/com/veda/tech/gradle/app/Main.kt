package com.veda.tech.gradle.app

import com.veda.tech.gradle.shared.Person
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    println(Person("asdas", "dsaasdas"))
//    runApplication<DemoApplication>(*args)
}
