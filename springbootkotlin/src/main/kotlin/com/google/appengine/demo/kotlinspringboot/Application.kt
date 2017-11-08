// See https://github.com/JetBrains/kotlin-examples/blob/master/LICENSE
package com.google.appengine.demo.kotlinspringboot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.support.SpringBootServletInitializer

@SpringBootApplication
class Application : SpringBootServletInitializer() {

}

fun main(args: Array<String>) {
	SpringApplication.run(Application::class.java, *args)
}
