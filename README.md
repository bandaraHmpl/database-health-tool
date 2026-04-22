# 🚀 Database Health Analysis Tool

A Java-based tool that analyzes database tables and generates health reports.

## 🔍 Features

* Scan full database
* Scan individual tables
* Detect missing primary keys
* Detect missing indexes
* Generate health score
* Export report to file (`report.txt`)

## 🛠 Technologies

* Java
* JDBC
* MySQL

## ▶️ How to Run

1. Configure database:

```
src/main/resources/config.properties
```

2. Run:

```
Main.java
```

## 📊 Example Output

```
Table: users
Primary Key: YES
Columns: 5
Index Count: 1
Warning: No issue found

Health Score: 100/100
Overall Status: Excellent
```

## 📁 Project Structure

```
src/
 ├── config/
 ├── model/
 ├── scanner/
 ├── report/
 └── Main.java
```

## 👨‍💻 Author

Lakshan
