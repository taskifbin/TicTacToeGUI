# 🎮 TicTacToeGUI

A Java Swing-based Tic Tac Toe game where players must **register and log in** before playing.  
Player information — **Name**, **Email**, **Password**, and **Points** — is stored and managed using a **MySQL database** powered by **XAMPP**.

---

## ✨ Features

- ✅ Player Registration (Name, Email, Password)
- ✅ Login system for two players
- ✅ Friendly GUI for Tic Tac Toe gameplay
- ✅ Points are tracked and stored in the database
- ✅ MySQL (XAMPP) database integration
- ✅ Error handling for wrong credentials

---

## 🛠 Requirements

- Java JDK 8 or later
- IntelliJ IDEA (or any Java IDE)
- XAMPP Control Panel (MySQL)
- MySQL Connector/J (JDBC driver)

---

## 📦 How to Set Up

### 1. Install and Run XAMPP
- Download and install [XAMPP](https://www.apachefriends.org/index.html).
- Open the XAMPP Control Panel.
- Start **MySQL** (Apache is optional).

### 2. Create the Database
- Open [phpMyAdmin](http://localhost/phpmyadmin).
- Create a new database named **`tictactoeGUI`**.
- Run the following SQL command to create the `playerInfo` table:
```sql
CREATE TABLE playerInfo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Password VARCHAR(100) NOT NULL,
    Point INT DEFAULT 0
);
```

✅ This will create a table to store player data.

---

## ⚙️ Set Up the Java Project

    Clone or download this repository.

    Import the project into IntelliJ IDEA (or your favorite IDE).

    Add MySQL Connector/J to your project dependencies.

If using Maven, add this to your pom.xml:
```pom.xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.4.0</version>
</dependency>
```
Or manually add the .jar file:

    Go to File → Project Structure → Libraries → Add the MySQL Connector/J .jar.

---

### Configure Database Connection

Edit your DBConnection.java like this:
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoeGUI", "root", "");
    }
}
```
(Assuming your MySQL username is root and password is empty.)

---

## 🚀 How to Run

    Run the Main.java file.

    Register two players.

    Log in using their accounts.

    Start playing Tic Tac Toe!

🏆 The winner's points will be updated automatically in the database.

---

## 📜 License

This project is **Open-Source** and free to use.

