<p align="center">
  <a href="./docs/README.en.md">
    <img src="https://img.shields.io/badge/Language-English-blue?style=for-the-badge">
  </a>
</p>

# Sistema de Estacionamento

Sistema de gerenciamento de estacionamento desenvolvido em Java utilizando JDBC e MySQL.

O projeto permite:

- Cadastro de clientes
- Cadastro de veículos
- Registro de entrada de veículos
- Cálculo de estadia
- Persistência de dados com MySQL
- Organização em camadas (`controller`, `service`, `repository`, `model`)

# Tecnologias Utilizadas

- Java
- JDBC
- MySQL
- Maven
- IntelliJ IDEA

# Estrutura do Projeto

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
│   └── classes de domínio
│
├── repository/
│
├── service/
│
└── pagamento/
```

# Funcionalidades

## Cadastro de Cliente

Permite cadastrar:
- Nome
- Telefone

## Cadastro de Veículo

Permite cadastrar:
- Placa
- Cor
- Modelo
- Tipo do motor
- Tipo do veículo

### Tipos de Motor

- Combustão
- Elétrico

### Tipos de Veículo

- Popular
- Van
- Moto

## Registro de Entrada

O sistema:
- busca o veículo pela placa
- registra automaticamente a data e hora de entrada utilizando `LocalDateTime.now()`

## Cálculo da Estadia

O valor é calculado por hora:

```text
R$10,00 por hora
```

O cálculo utiliza:

```java
Duration.between()
```

e arredondamento para cima:

```java
Math.ceil()
```

### Exemplo

| Tempo | Valor |
|---|---|
| 10 min | R$10 |
| 1h 20min | R$20 |
| 2h 01min | R$30 |

## Relatórios Financeiros

O sistema possui relatórios analíticos utilizando SQL avançado:

- faturamento por mês
- quantidade de veículos por tipo
- agrupamento de dados
- histórico financeiro
- consultas com JOIN, SUM, COUNT e GROUP BY

## Sistema de Pagamento

O sistema possui múltiplas formas de pagamento
implementadas com orientação a objetos e polimorfismo:

- PIX
- Cartão
- Dinheiro

# Banco de Dados

## Criação do Banco

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
modelo VARCHAR(45) NOT NULL,
tipo_motor VARCHAR(20) NOT NULL,
tipo_veiculo VARCHAR(20) NOT NULL,
idPessoa_fk INT, 

FOREIGN KEY (idPessoa_fk) REFERENCES pessoa(idPessoa) ON DELETE CASCADE
);

CREATE TABLE estadia(
idEstadia INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
data_entrada DATETIME(0) NOT NULL,
data_saida DATETIME(0),
valor_pago DECIMAL(10, 2),
idVeiculo_fk INT,

FOREIGN KEY (idVeiculo_fk) REFERENCES veiculo(idVeiculo) ON DELETE CASCADE
);
```

# Configuração da Conexão

Configure sua conexão na classe:

```java
database.Conexao
```

### Exemplo

```java
final private String URL =
"jdbc:mysql://localhost:3306/db_estacionamneto";

final private String USER = "root";

final private String PASSWORD = "sua_senha";
```

# Como Executar

## 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
```

## 2. Configure o banco MySQL

Execute o script SQL presente neste README.

## 3. Configure a conexão com o banco

Edite:

```java
Conexao.java
```

## 4. Execute o projeto

Execute a classe:

```text
Main.java
```

# Arquitetura Utilizada

O projeto utiliza separação em camadas:

| Camada | Responsabilidade |
|---|---|
| Controller | Entrada de dados |
| Service | Regras de negócio |
| Repository | Comunicação com banco |
| Model | Entidades do sistema |

# Conceitos Aplicados

- Programação Orientada a Objetos (POO)
- Interfaces
- Polimorfismo
- JDBC
- SQL Relacional
- Arquitetura em Camadas
- Relacionamento entre Entidades
- Tratamento de Exceções

# Melhorias Futuras

- Interface gráfica
- API REST com Spring Boot
- Sistema de autenticação
- Deploy em nuvem

# Autor

Lucas Carvalho
