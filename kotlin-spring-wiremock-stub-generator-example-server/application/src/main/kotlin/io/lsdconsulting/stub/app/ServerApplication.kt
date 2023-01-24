package io.lsdconsulting.stub.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServerApplication

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}

//@SpringBootApplication
//object ServerApplication {
//
//    @JvmStatic
//    fun main(args: Array<String>) {
//        SpringApplication.run(ServerApplication::class.java, *args)
//    }
//}