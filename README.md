<h1 align='center'> Welcome to Java Bank's README :) </h1>

                    /$$$$$                                /$$$$$$$                      /$$     \s
                   |__  $$                               | $$__  $$                    | $$     \s
                      | $$  /$$$$$$  /$$    /$$  /$$$$$$ | $$  \\ $$  /$$$$$$  /$$$$$$$ | $$   /$$
                      | $$ |____  $$|  $$  /$$/ |____  $$| $$$$$$$  |____  $$| $$__  $$| $$  /$$/
                 /$$  | $$  /$$$$$$$ \\  $$/$$/   /$$$$$$$| $$__  $$  /$$$$$$$| $$  \\ $$| $$$$$$/\s
                | $$  | $$ /$$__  $$  \\  $$$/   /$$__  $$| $$  \\ $$ /$$__  $$| $$  | $$| $$_  $$\s
                |  $$$$$$/|  $$$$$$$   \\  $/   |  $$$$$$$| $$$$$$$/|  $$$$$$$| $$  | $$| $$ \\  $$
                 \\______/  \\_______/    \\_/     \\_______/|_______/  \\_______/|__/  |__/|__/  \\__/
                                                                                                
                                                                                                
 ## Overview                                                                                               
 Java bank is a console application written in Java (obviously) with the help of Intelij IDE that simulates number of bank functions.
The app uses two types of databases - SQLite and a file one which can be choosed from the UI. Having logged, user can create up to 3 accounts,
add money in ATM, import them to an account, withdraw money, exhcnage money and transfer to other user (if created).
+ Currency currently supported: BGN and RSD, live updated via exchangerate.host api

## Used Technology
+ SDK: openjdk-18
+ Java SE 18
+ Maven
+ SQLite 3
## Requirements before powering up

+ TODO (db)

## Start application
+ on app's home directory from the terminal
```
 #  java -classpath /home/spooky/IdeaProjects/JavaBank/target/classes:/home/spooky/.m2/repository/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar:/home/spooky/.m2/repository/ch/qos/logback/logback-classic/1.2.11/logback-classic-1.2.11.jar:/home/spooky/.m2/repository/ch/qos/logback/logback-core/1.2.11/logback-core-1.2.11.jar:/home/spooky/.m2/repository/org/xerial/sqlite-jdbc/3.39.2.0/sqlite-jdbc-3.39.2.0.jar:/home/spooky/.m2/repository/mysql/mysql-connector-java/8.0.30/mysql-connector-java-8.0.30.jar:/home/spooky/.m2/repository/com/google/protobuf/protobuf-java/3.19.4/protobuf-java-3.19.4.jar:/home/spooky/.m2/repository/org/postgresql/postgresql/42.4.1/postgresql-42.4.1.jar:/home/spooky/.m2/repository/org/checkerframework/checker-qual/3.5.0/checker-qual-3.5.0.jar:/home/spooky/.m2/repository/org/apache/httpcomponents/httpclient/4.5.13/httpclient-4.5.13.jar:/home/spooky/.m2/repository/org/apache/httpcomponents/httpcore/4.4.13/httpcore-4.4.13.jar:/home/spooky/.m2/repository/commons-logging/commons-logging/1.2/commons-logging-1.2.jar:/home/spooky/.m2/repository/commons-codec/commons-codec/1.11/commons-codec-1.11.jar:/home/spooky/.m2/repository/com/googlecode/json-simple/json-simple/1.1.1/json-simple-1.1.1.jar:/home/spooky/.m2/repository/junit/junit/4.10/junit-4.10.jar:/home/spooky/.m2/repository/org/hamcrest/hamcrest-core/1.1/hamcrest-core-1.1.jar:/home/spooky/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.13.3/jackson-databind-2.13.3.jar:/home/spooky/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.13.3/jackson-annotations-2.13.3.jar:/home/spooky/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.13.3/jackson-core-2.13.3.jar:/home/spooky/.m2/repository/com/google/code/gson/gson/2.9.1/gson-2.9.1.jar Main
   ```
+ or use favourite Java IDE (Intelij, Eclipse, NetBeans etc)

