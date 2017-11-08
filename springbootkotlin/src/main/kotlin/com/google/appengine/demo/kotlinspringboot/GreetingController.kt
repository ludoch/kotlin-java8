package com.google.appengine.demo.kotlinspringboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class GreetingController {

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Greeting(counter.incrementAndGet(),
                    """"Hello, $name, from a SpringBoot Application written in Kotlin,
                            running on Google App Engine Java8 Standard...""")

    @GetMapping("/lookup")
    fun lookup(@RequestParam(value = "name", defaultValue = "Ludo") name: String) =
            """Hello, $name, you executed the lookup handler in the Kotlin App Engine App.
             """
}
