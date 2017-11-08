/**
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.google.appengine.api.datastore.*
import datastore.transaction
import datastore.get
import datastore.set
import html.html
import spark.kotlin.Http
import spark.kotlin.ignite
import spark.servlet.SparkApplication
import java.sql.DriverManager

/**
 * Example usage of spark-kotlin.
 * See https://github.com/perwendel/spark-kotlin
 */
class MainApp : SparkApplication {
    override fun init() {

        val http: Http = ignite()

        http.get("/") {
            //    """Hello Spark Kotlin running on Java8 App Engine Standard.
            //    <p>You can try /hello<p> or /lookup/:name<p> or redirect
            //    <p>or /nothing"""


            html {
                body {
                    p { +"Hello Spark Kotlin running on Java8 App Engine Standard" }
                    p {
                        +"You can try "
                        a(href = "/hello") { +"/hello" }
                        +" URL."
                    }
                    p {
                        +"Or "
                        a(href = "/lookup/hello") { +"/lookup/hello" }
                        +" URL."
                    }
                }
            }
        }
        http.get("/hello") {
            "Hello Spark Kotlin running on Java8 App Engine Standard."
        }

        http.get("/nothing") {
            status(404)
            "Oops, we couldn't find what you're looking for."
        }

        http.get("/lookup/:name") {
            "Hello from Kotlin: " + params(":name")
        }

        http.get("/redirect") {
            redirect("/hello");
        }

        http.get("/incrementcounter") {
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
            """Counter Value stored in the App Engine Datastore is now: ${entity["count"]}
            <pre>

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

            </pre>
            """.trimMargin()
        }

            http.get("/sql") {

            val instanceConnectionName = "kotlin-java8:us-central1:kotlindb"
            val databaseName = "kotlindatabase"

            val username = "root"
            val password = ""


            val jdbcUrl = String.format(
                    "jdbc:google:mysql://google/%s?cloudSqlInstance=%s&" + "socketFactory=com.google.cloud.sql.mysql.SocketFactory",
                    databaseName,
                    instanceConnectionName)
            //    < value="jdbc:google:mysql://jcandystore:ludo1/jcandystore?user=root"

            val jdbcUrl2 ="jdbc:google:mysql://kotlin-java8:us-central1:kotlindb/kotlindatabase?user=root"
            try {
                val connection = DriverManager.getConnection(jdbcUrl2, username, password)
                var s = ""
                connection.createStatement().use { statement ->
                    val resultSet = statement.executeQuery("SHOW TABLES")
                    while (resultSet.next()) {
                        s +=" Table= " + resultSet.getString(1)
                    }
                }
                s
            } catch (e: Exception) {
                " " + e + " " + jdbcUrl2
            }
        }
    }
}