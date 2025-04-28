import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;

public class game extends JFrame {
    private gBoard gBoard;
    private JButton[][] buttons;
    private tictactoePlayer player1, player2;
    private JLabel infoLabel;
    private boolean gameEnded = false;
    private Random random;

    public game(tictactoePlayer p1, tictactoePlayer p2) {
        this.player1 = p1;
        this.player2 = p2;
        this.random = new Random();
        this.gBoard = new gBoard();
        this.buttons = new JButton[3][3];

        setTitle("Tic Tac Toe Game");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        infoLabel = new JLabel("", SwingConstants.CENTER);
        infoLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        add(infoLabel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(3,3));
        add(boardPanel, BorderLayout.CENTER);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i, col = j;
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 70));
                boardPanel.add(buttons[i][j]);

                buttons[i][j].addActionListener(e -> {
                    if (!gameEnded && gBoard.makeMove(row, col)) {
                        char mark = gBoard.getCurrentPlayerMark();
                        buttons[row][col].setText(String.valueOf(mark));
                        if (mark == 'X') {
                            buttons[row][col].setForeground(Color.RED);
                        } else if (mark == 'O') {
                            buttons[row][col].setForeground(Color.BLUE);
                        }

                        char winner = gBoard.checkWinner();
                        if (winner != '-') {
                            endGame(winner);
                        } else if (gBoard.isFull()) {
                            endGame('-'); // tie
                        } else {
                            gBoard.changePlayer();
                            updateInfo();
                        }
                    }
                });
            }
        }

        randomFirstTurn();
        updateInfo();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void randomFirstTurn() {
        if (random.nextBoolean()) {
            gBoard.setCurrentPlayerMark('X');
        } else {
            gBoard.setCurrentPlayerMark('O');
        }
    }

    private void updateInfo() {
        String text = "<html>"
                + "<b>" + player1.name + "</b> (Points: " + player1.points + ") vs "
                + "<b>" + player2.name + "</b> (Points: " + player2.points + ")<br>";

        if (gBoard.getCurrentPlayerMark() == 'X') {
            text += player1.name + "'s move (X)</html>";
        } else {
            text += player2.name + "'s move (O)</html>";
        }
        infoLabel.setText(text);
    }

    private void endGame(char winnerMark) {
        gameEnded = true;
        if (winnerMark == '-') {
            JOptionPane.showMessageDialog(this, "It's a Tie!");
            updatePoints(player1.email, 1);
            updatePoints(player2.email, 1);
            player1.points += 1;
            player2.points += 1;
        } else {
            tictactoePlayer winner = (winnerMark == 'X') ? player1 : player2;
            JOptionPane.showMessageDialog(this, winner.name + " Wins!");
            updatePoints(winner.email, 3);
            winner.points += 3;
        }

        int option = JOptionPane.showConfirmDialog(this, "Continue Playing?", "Tic Tac Toe", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetBoard();
        } else {
            System.exit(0);
        }
    }

    private void resetBoard() {
        gBoard.showBoard();
        gameEnded = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("-");
                buttons[i][j].setForeground(Color.BLACK);
            }
        }

        randomFirstTurn();
        updateInfo(); // Update players' info with current points
    }

    private void updatePoints(String email, int pointsToAdd) {
        try (Connection conn = dbConnect.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE playerInfo SET Point = Point + ? WHERE Email = ?");
            ps.setInt(1, pointsToAdd);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
