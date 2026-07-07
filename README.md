# Library Management System

A simple console-based **Library Management System** built with **Java**, **Maven**, **JDBC**, and **PostgreSQL**.

The application demonstrates the implementation of **CRUD** operations while following the **DAO** design pattern.

---

## Features

- 📖 View all books
- 📚 Add a new book
- 🔍 Find a book by ID
- 🗑️ Delete a book

---

## Tech Stack

- **Java 25**
- **Maven 3.9.16**
- **JDBC 42.7.12**
- **PostgreSQL 18.4**

---

## Prerequisites

Before running the project, make sure you have the following installed:

- Java 25 or newer
- Maven
- PostgreSQL

Verify the installation:

```bash
java -version
mvn -version
psql --version
```

---

# Installation

## 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/Library-app.git
```

Go into the project directory:

```bash
cd Library-app
```

---

# PostgreSQL Setup

## 1. Install PostgreSQL

### Windows

Download PostgreSQL from:

https://www.postgresql.org/download/windows/

Run the installer and remember:

- PostgreSQL username (usually `postgres`)
- Password
- Port (default is `5432`)

---

### Linux
#### Arch Linux

```bash
sudo pacman -S postgresql
```

Initialize the database (first installation only):

```bash
sudo -iu postgres initdb -D /var/lib/postgres/data
```

Start PostgreSQL:

```bash
sudo systemctl start postgresql
```

---

## 2. Create the database

Open PostgreSQL:

```bash
psql -U postgres
```

Create the database:

```sql
CREATE DATABASE library;
```

Exit:

```sql
\q
```

---

## 3. Create the tables

The project already contains a `schema.sql` file.

Run:

```bash
psql -U postgres -d library -f schema.sql
```

This will automatically create all the required tables.

---

## 4. Configure the database connection

Open:

```
src/main/resources/db.properties
```

Replace the placeholders with your PostgreSQL credentials.

Example:

```properties
db.url=jdbc:postgresql://localhost:5432/library
db.username=postgres
db.password=your_password
```

If your PostgreSQL server uses a different username, password, port, or database name, update these values accordingly.

---

# Build the project

Compile the project:

```bash
mvn clean compile
```

---

# Run the project

Run the application using Maven:

```bash
mvn exec:java -Dexec.mainClass=com.elabiad.Main
```

---

# Project Structure

```
Library-app
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com/elabiad
│   │   │       ├── config
│   │   │       ├── dao
│   │   │       ├── model
│   │   │       └── Main.java
│   │   │
│   │   └── resources
│   │       └── db.properties
│   │
│   └── test
│
├── schema.sql
├── pom.xml
├── README.md
└── .gitignore
```

---
# Author

**Mohamed El-Abiad**