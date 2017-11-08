package sql

import java.io.IOException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement


    fun main(args: Array<String>) {

        // The instance connection name can be obtained from the instance overview page in Cloud Console
        // or by running "gcloud sql instances describe <instance> | grep connectionName".
        val instanceConnectionName = "kotlin-java8:us-central1:kotlindb"

        // The database from which to list tables.
        val databaseName = "kotlindatabase"

        val username = "root"

        // This is the password that was set via the Cloud Console or empty if never set
        // (not recommended).
        val password = ""


        val jdbcUrl = String.format(
                "jdbc:mysql://google/%s?cloudSqlInstance=%s&" + "socketFactory=com.google.cloud.sql.mysql.SocketFactory",
                databaseName,
                instanceConnectionName)

        val connection = DriverManager.getConnection(jdbcUrl, username, password)

        connection.createStatement().use { statement ->
            val resultSet = statement.executeQuery("SHOW TABLES")
            while (resultSet.next()) {
                println(resultSet.getString(1))
            }
        }

    }