package DataBase;

public class GameScore {
    private static final GameScore instance;
    private Player[] players;
    private int playerCount;
    private int playerIndex;
    private int questionIndex;
    private DataImages data = DataImages.getDataImages();

    private GameScore() {
    }

    private void initPlayers(String... names) {
        players = new Player[playerCount];
        int i = 0;
        for (String name : names) {
            players[i] = new Player(name, i, playerCount);
            i++;
        }
    }

    public void checkAnswer(String input, int question) {
        if (input.toLowerCase().equals(data.getAnswer(question))) {
            players[(playerCount == 1) ? 0 : playerIndex % 2].updateScore();
        }
        playerIndex = (playerCount == 1) ? 0 : (playerIndex == 0) ? 1 : 0;
        questionIndex++;
    }

    private int getPlayerIndex() {
        return playerIndex;
    }

    public String getLastScore(){
        return players[getPlayerIndex()].getScore().toString();
    }

    public String getLastName(){
        return players[getPlayerIndex()].getName();
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public String getWinner() {
        StringBuilder winner = new StringBuilder();
        if (playerCount == 1) {
            winner.append(players[0].getName()).append(", ваш результат: ").append(players[0].getScore().toString());
        } else {
            if (players[0].getScore().equals(players[1].getScore())){
                winner.append("Итог игры - ничья: ").append(players[0].getName() + " и ").append("\n").append(players[1].getName()).append("Итоговый счёт : ").append(players[0].getScore().toString());
            } else {
                for (int i = 0; i < playerCount; i++) {
                    winner.append("Счет игрока ").append(players[i].getName()).append(" : ").append(players[i].getScore().toString()).append("\n");
                }
                if (players[0].getScore() > players[1].getScore()) {
                    winner.append("Победил(а) ").append(players[0].getName()).append(", ваш результат: ").append(players[0].getScore().toString());
                } else {
                    winner.append("Победил(а) ").append(players[1].getName()).append(", ваш результат: ").append(players[1].getScore().toString());
                }
            }
        }
        return winner.toString();
    }

    public void newGame(String... names) {
        for (int i = 0; i < playerCount; i++) {
            players[i] = null;
        }
        playerCount = playerIndex = questionIndex = 0;
        String name[] = new String[2];
        for (String input : names) {
            name[playerCount] = input;
            playerCount++;
        }
        if (playerCount == 1) {
            initPlayers(name[0]);
        } else {
            initPlayers(name);
        }
    }

    static {
        instance = new GameScore();
    }

    public static GameScore getGameScore() {
        return instance;
    }
}
