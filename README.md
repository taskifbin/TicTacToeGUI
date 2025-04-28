#TicTacToeGUI

This is a Java Swing GUI-based Tic Tac Toe game where players must register and log in before playing. The players' Name, Email, Password, and Points are saved and managed using a MySQL database running on XAMPP.

✨ Features

✅ Player Registration (Name, Email, Password)

✅ Login system for two players

✅ Tic Tac Toe game with a friendly GUI

✅ Points are tracked and stored in the database

✅ MySQL (XAMPP) integration

✅ Error handling for wrong credentials

🛠 Requirements

Java JDK 8 or later

IntelliJ IDEA (or any Java IDE)

XAMPP Control Panel (MySQL)

MySQL Connector/J (JDBC driver)

📦 How to Set Up

    Install and Run XAMPP

    Download XAMPP and install.

    Open XAMPP Control Panel.

    Start Apache (optional) and MySQL (must).

    Create the Database

    Open phpMyAdmin (http://localhost/phpmyadmin).

    Create a new database called (example) tic_tac_toe_db.

    Run this SQL command:

CREATE TABLE playerInfo ( id INT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(100) NOT NULL, Email VARCHAR(100) UNIQUE NOT NULL, Password VARCHAR(100) NOT NULL, Point INT DEFAULT 0 );

✅ This will create a table to store players' information. 3. Setup Java Project

Clone or download this repository.

Import project into IntelliJ IDEA (or your favorite IDE).

Add MySQL Connector/J to your project dependencies.

    If using Maven, add:

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.4.0</version>
    </dependency>

    Or manually add the .jar file (via Project Structure → Libraries).

Edit your DBConnection.java like this:

import java.sql.Connection; import java.sql.DriverManager; import java.sql.SQLException;

public class DBConnection { public static Connection getConnection() throws SQLException { return DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoeGUI", "root", ""); } }

(Assuming username = root, password = empty) 🚀 How to Run

Run the Main.java file.

Register two players.

Login with those accounts.

Start playing Tic Tac Toe!

🏆 Winner can have their points updated .

📜 License

This project is Open-Source.
