import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Tic Tac Toe Game");
        setSize(420, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton registerButton = new JButton("Register");
        JButton playButton = new JButton("LogIn");

        add(registerButton);
        add(playButton);

        registerButton.addActionListener(e -> {
            dispose();
            register();
        });

        playButton.addActionListener(e -> {
            dispose();
            login();
        });

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void register() {
        JFrame regFrame = new JFrame("Register");
        regFrame.setSize(300, 250);
        regFrame.setLayout(new GridLayout(4, 2));

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passField = new JPasswordField();

        JButton submitButton = new JButton("Register ");

        regFrame.add(new JLabel("Name:"));
        regFrame.add(nameField);
        regFrame.add(new JLabel("Email:"));
        regFrame.add(emailField);
        regFrame.add(new JLabel("Password:"));
        regFrame.add(passField);
        regFrame.add(submitButton);

        submitButton.addActionListener(e -> {
            try (Connection conn = dbConnect.getConnection()) {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO playerInfo (Name, Email, Password) VALUES (?, ?, ?)");
                ps.setString(1, nameField.getText());
                ps.setString(2, emailField.getText());
                ps.setString(3, new String(passField.getPassword()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(regFrame, "Registration Successful!");
                regFrame.dispose();
                new Main();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(regFrame, "Error: " + ex.getMessage());
            }
        });

        regFrame.setVisible(true);
        regFrame.setLocationRelativeTo(null);
    }

    private void login() {
        JFrame loginFrame = new JFrame("Login Players");
        loginFrame.setSize(300, 300);
        loginFrame.setLayout(new GridLayout(5, 2));

        JTextField email1 = new JTextField();
        JPasswordField pass1 = new JPasswordField();
        JTextField email2 = new JTextField();
        JPasswordField pass2 = new JPasswordField();
        JButton loginButton = new JButton("Login and Play");

        loginFrame.add(new JLabel("Player 1 Email:"));
        loginFrame.add(email1);
        loginFrame.add(new JLabel("Player 1 Password:"));
        loginFrame.add(pass1);
        loginFrame.add(new JLabel("Player 2 Email:"));
        loginFrame.add(email2);
        loginFrame.add(new JLabel("Player 2 Password:"));
        loginFrame.add(pass2);
        loginFrame.add(loginButton);

        loginButton.addActionListener(e -> {
            try (Connection conn = dbConnect.getConnection()) {
                // Separate PreparedStatement for Player 1
                PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM playerInfo WHERE Email=? AND Password=?");
                ps1.setString(1, email1.getText());
                ps1.setString(2, new String(pass1.getPassword()));
                ResultSet rs1 = ps1.executeQuery();

                // Separate PreparedStatement for Player 2
                PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM playerInfo WHERE Email=? AND Password=?");
                ps2.setString(1, email2.getText());
                ps2.setString(2, new String(pass2.getPassword()));
                ResultSet rs2 = ps2.executeQuery();

                if (rs1.next() && rs2.next()) {
                    tictactoePlayer p1 = new tictactoePlayer(rs1.getString("Name"), rs1.getString("Email"), rs1.getString("Password"), rs1.getInt("Point"));
                    tictactoePlayer p2 = new tictactoePlayer(rs2.getString("Name"), rs2.getString("Email"), rs2.getString("Password"), rs2.getInt("Point"));
                    loginFrame.dispose();
                    new game(p1, p2);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid login. Try again!");
                }

                // Close resources
                rs1.close();
                ps1.close();
                rs2.close();
                ps2.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(loginFrame, "Error: " + ex.getMessage());
            }
        });

        loginFrame.setVisible(true);
        loginFrame.setLocationRelativeTo(null);
    }


    public static void main(String[] args) {
        new Main();
    }
}
