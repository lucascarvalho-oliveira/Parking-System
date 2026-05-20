<p align="center">
  <a href="../README.md">
    <img src="https://img.shields.io/badge/Language-Português-green?style=for-the-badge">
  </a>
</p>

# Parking Management System

Parking management system developed in Java using JDBC and MySQL.

The project allows:

- Customer registration
- Vehicle registration
- Vehicle entry registration
- Parking stay calculation
- Data persistence with MySQL
- Layered architecture organization (`controller`, `service`, `repository`, `model`)

# Technologies Used

- Java
- JDBC
- MySQL
- Maven
- IntelliJ IDEA

# Project Structure

```text
src/
│
├── application/
│   ├── controller/
│   └── Main.java
│
├── database/
│   └── Conexao.java
│
├── model/
│   ├── enums/
│   └── domain classes
│
├── repository/
│
├── service/
│
└── pagamento/
```

# Features

## Customer Registration

Allows registering:
- Name
- Phone number

## Vehicle Registration

Allows registering:
- License plate
- Color
- Model
- Engine type
- Vehicle type

### Engine Types

- Combustion
- Electric

### Vehicle Types

- Economy
- Van
- Motorcycle

## Entry Registration

The system:
- searches for the vehicle by license plate
- automatically registers the entry date and time using `LocalDateTime.now()`

## Parking Stay Calculation

The amount is calculated per hour:

```text
R$10.00 per hour
```

The calculation uses:

```java
Duration.between()
```

and rounding up:

```java
Math.ceil()
```

### Example

| Time | Amount |
|---|---|
| 10 min | R$10 |
| 1h 20min | R$20 |
| 2h 01min | R$30 |

## Financial Reports

The system has analytical reports using advanced SQL:

- monthly revenue
- quantity of vehicles by type
- data grouping
- financial history
- queries using JOIN, SUM, COUNT and GROUP BY

## Payment System

The system has multiple payment methods
implemented using object-oriented programming and polymorphism:

- PIX
- Card
- Cash

# Database

## Database Creation

```sql
CREATE DATABASE db_estacionamneto;

USE db_estacionamneto;

CREATE TABLE pessoa(
idPessoa INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
telefone VARCHAR(15)
);

CREATE TABLE veiculo(
idVeiculo INT PRIMARY KEY AUTO_INCREMENT,
placa VARCHAR(45) NOT NULL UNIQUE,
cor VARCHAR(45) NOT NULL,
modelo VARCHAR(10) NOT NULL,
tipo_motor VARCHAR(20) NOT NULL,
tipo_veiculo VARCHAR(20) NOT NULL,
idPessoa_fk INT,

FOREIGN KEY (idPessoa_fk)
REFERENCES pessoa(idPessoa)
ON DELETE CASCADE
);

CREATE TABLE estadia(
idEstadia INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
data_entrada DATETIME(0) NOT NULL,
data_saida DATETIME(0),
idVeiculo_fk INT,

FOREIGN KEY (idVeiculo_fk)
REFERENCES veiculo(idVeiculo)
ON DELETE CASCADE
);
```

# Connection Configuration

Configure your connection in the class:

```java
database.Conexao
```

### Example

```java
final private String URL =
"jdbc:mysql://localhost:3306/db_estacionamneto";

final private String USER = "root";

final private String PASSWORD = "your_password";
```

# How to Run

## 1. Clone the repository

```bash
git clone https://github.com/your-username/your-repository.git
```

## 2. Configure the MySQL database

Run the SQL script present in this README.

## 3. Configure the database connection

Edit:

```java
Conexao.java
```

## 4. Run the project

Run the class:

```text
Main.java
```

# Architecture Used

The project uses layered architecture:

| Layer | Responsibility |
|---|---|
| Controller | Data input |
| Service | Business rules |
| Repository | Database communication |
| Model | System entities |

# Applied Concepts

- Object-Oriented Programming (OOP)
- Interfaces
- Polymorphism
- JDBC
- Relational SQL
- Layered Architecture
- Entity Relationships
- Exception Handling

# Future Improvements

- Graphical interface
- REST API with Spring Boot
- Authentication system
- Cloud deployment

# Author

Lucas Carvalho
