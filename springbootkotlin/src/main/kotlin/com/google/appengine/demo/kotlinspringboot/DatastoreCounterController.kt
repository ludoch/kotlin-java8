package com.google.appengine.demo.kotlinspringboot

import com.google.appengine.api.datastore.DatastoreServiceFactory
import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.datastore.EntityNotFoundException
import com.google.appengine.api.datastore.KeyFactory
import datastore.get
import datastore.set
import datastore.transaction
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DatastoreCounterController {


    @GetMapping("/incrementcounter")
    fun incrementCounter(): String {
        val counterKey = KeyFactory.createKey("Counter", "MyCounter")
        val datastore = DatastoreServiceFactory.getDatastoreService()
        val entity = try {
            datastore[counterKey]
        } catch (_: EntityNotFoundException) {
            val newEntity = Entity(counterKey)
            newEntity["count"] = 0L
            datastore.transaction { put(it, newEntity) }
            newEntity
        }
        entity["count"] = (entity["count"] as Long) + 1
        datastore.transaction { put(it, entity) }
        val s = "\"\"\""
        return """Counter Value stored in the App Engine Datastore is now: ${entity["count"]}


            Code snippet is:

            val counterKey = KeyFactory.createKey("Counter", "MyCounter")
            val datastore = DatastoreServiceFactory.getDatastoreService()
            val entity = try {
                datastore[counterKey]
            } catch (_: EntityNotFoundException) {
                val newEntity = Entity(counterKey)
                newEntity["count"] = 0L
                datastore.transaction { put(it, newEntity) }
                newEntity
            }
            entity["count"] = (entity["count"] as Long) + 1
            datastore.transaction { put(it, entity) }
            ${s}Counter Value stored in the App Engine Datastore is now: ${'$'}{entity["count"]}${s}


            """
    }
}
