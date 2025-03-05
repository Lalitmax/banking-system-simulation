# Banking System

BankingSystem is a console-based Java application that simulates essential banking operations with MySQL database integration. It allows users to create accounts, deposit, withdraw, and check balances securely through an interactive menu-driven interface.

## 📂 Folder Structure
![image](https://github.com/user-attachments/assets/6417ad4f-c9c7-4324-a0b7-6582db2695df)

 
## 📌 Setup Instructions

### 1️⃣ Clone the Repository
```sh
git clone https://github.com/your-repo/BankingApp.git
cd BankingApp
```

### 2️⃣ Create a `.env` File
Inside the `root/` directory, create a `.env` file:
```ini
DB_URL=jdbc:mysql://localhost:3306/bank
DB_USER=root
DB_PASSWORD=yourpassword
```

### 3️⃣ Setup MySQL Database

Create Database
```
CREATE DATABASE bank;
USE bank;
```

Create mangalbank
```

CREATE TABLE mangalbank (
    bank_id INT PRIMARY KEY AUTO_INCREMENT, 
    bank_name VARCHAR(100) NOT NULL, 
    branch_name VARCHAR(100), 
    total_balance DOUBLE NOT NULL DEFAULT 0, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

```

Create Accounts
```sql

CREATE TABLE accounts (
    account_number VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    pin double NOT NULL,
    balance DECIMAL(10,2) NOT NULL
);
```

### 4️⃣ Compile and Run the Project

#### 🔹 Compile
```sh
javac -cp . src/main/BankImplementation.java
```

#### 🔹 Run
```sh
java -cp . src.main.BankImplementation
```

## 🚀 Features
✔ Secure database connection using `.env`  
✔ Create bank accounts  
✔ Deposit, Withdraw, and Check Balance  
✔ Supports MySQL database  

## 🛠 Technologies Used
- Java
- MySQL
- JDBC
- `.env` for secure credentials management

# Contribute💓
 
