public class tictactoePlayer extends player {
    private String password;

    public tictactoePlayer(String name, String email, String password, int points) {
        super(name, email, points);
        this.password = password;
    }

    public String getPass() {
        return password;
    }
}
