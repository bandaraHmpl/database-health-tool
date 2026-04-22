# Database Health Tool

Java-based tool to analyze database structure and generate health reports with SQL optimization suggestions.

## Features

* Scan full database or single table
* Detect missing primary keys and indexes
* Generate health score
* Export report to file
* Provide SQL suggestions

## Tech Stack

* Java (JDK 17)
* JDBC
* MySQL

## Run

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.example.dbhealthtool.Main"
```

## Config

Edit:

```
src/main/resources/config.properties
```

## Author

Lakshan – University of Ruhuna
