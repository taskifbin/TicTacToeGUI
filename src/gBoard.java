public class gBoard {
    private char[][] board;
    private char currentPlayerMark;

    public gBoard() {
        board = new char[3][3];
        showBoard();
        currentPlayerMark = 'X';
    }

    public void showBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = '-';
    }

    public boolean isFull() {
        for (char[] row : board)
            for (char c : row)
                if (c == '-') return false;
        return true;
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col] == '-') {
            board[row][col] = currentPlayerMark;
            return true;
        }
        return false;
    }

    public void setCurrentPlayerMark(char mark) {
        this.currentPlayerMark = mark;
    }

    public void changePlayer() {
        currentPlayerMark = (currentPlayerMark == 'X') ? 'O' : 'X';
    }

    public char getCurrentPlayerMark() {
        return currentPlayerMark;
    }

    public char checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++)
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0];

        for (int j = 0; j < 3; j++)
            if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
                return board[0][j];

        // Check diagonals
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0];
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[0][2];

        return '-';
    }
}
