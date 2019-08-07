# TestCmsMongo

Spring Boot Application for testing CRUD operations on Mongo DB.

## Requires Windows 64 bit, java 1.8, Maven 3 & Mongo DB 4. 

To Begin with clone this repo on a local drive using the following GIT command.

````$xslt
git clone https://github.com/ISingh2015/TestMongo.git
````
 
## Windows Usage:
Open the Project in your favourite IDE, or open windows CLI / power shell & CD into the project directory. Update Mongo database URL. Continue to run maven from your IDE or use the CLi / power shell as below. 

````$xslt
cd project_directory
````

````$xslt
mvn clean test 
````
On successful maven test execution, build the jar file and install it to the M2 directory, using the following.
````$xslt
mvn install
````

Finally, run the Mongo DB backend REST Services using Maven or SpringBoot or Java as below. 
To Run with Maven type the following 
````$xslt
cd project_directory\target
mvn -Dmaven.test.skip=true exec:java
````

To Run with SpringBoot type the following 
````$xslt
cd project_directory\target
mvn -Dmaven.test.skip=true spring-boot:run 
or 
mvn -DskipTests spring-boot:run
````
To Run with Java type the following 
````$xslt
java -jar jarfileName.jar
````
You will see the application up on port 8080. Browse the application, open chrome browser and navigate to http://localhost:8080

Add Sample Audio files to <MongoDBName> using Mongo DB CLI as below
````$xslt
mongofiles -d <MongoDBName> put song.mp3
````
