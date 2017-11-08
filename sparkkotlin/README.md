App Engine SparkJava Kotlin with Java8
===

## Sample SparkJava application written in Kotlin for use with App Engine Java8 Standard.

For Spark Kotlin documentation, see [Spark Kotlin](https://github.com/perwendel/spark-kotlin/).

See the [Google App Engine standard environment documentation][ae-docs] for more
detailed instructions.

[ae-docs]: https://cloud.google.com/appengine/docs/java/

* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/download.cgi) (at least 3.5)
* [Google Cloud SDK](https://cloud.google.com/sdk/) (aka gcloud command line tool)

## Setup

* Download and initialize the [Cloud SDK](https://cloud.google.com/sdk/)

    `gcloud init`

* Create an App Engine app within the current Google Cloud Project

    `gcloud app create`

## Maven
### Running locally

`mvn appengine:run`

To use vist: http://localhost:8080/

### Deploying

`mvn appengine:deploy`

To use vist:  https://YOUR-PROJECT-ID.appspot.com

## Testing

`mvn verify`

As you add / modify the source code (`src/main/java/...`) it's very useful to add [unit testing](https://cloud.google.com/appengine/docs/java/tools/localunittesting)
to (`src/main/test/...`).  The following resources are quite useful:

* [Junit4](http://junit.org/junit4/)
* [Mockito](http://mockito.org/)
* [Truth](http://google.github.io/truth/)


For further information, consult the
[Java App Engine](https://developers.google.com/appengine/docs/java/overview) documentation.



# MYSQL setting (read https://cloud.google.com/sql/docs/mysql/quickstart)
project id is kotlin-java8
Cloud SQL instance is kotlindb
Cloud SQL database is kotlindatabase

gcloud beta sql connect kotlindb --user=root


USE kotlindatabase;
CREATE TABLE entries (guestName VARCHAR(255), content VARCHAR(255),
    entryID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(entryID));
    INSERT INTO entries (guestName, content) values ("ludovic", "Ludo complete Info is there");
    INSERT INTO entries (guestName, content) values ("ludo", "Ludo complete Info is there");
    INSERT INTO entries (guestName, content) values ("eamonn", "Eamonn complete info from the database");
    INSERT INTO entries (guestName, content) values ("?amonn", "Eamonn complete info from the database");


    URL
    <property name="cloudsql.url" value="jdbc:google:mysql://kotlin-java8:us-central1:kotlindb/kotlindatabase?user=root"/>

