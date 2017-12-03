package DataBase;

import DataBase.Image.DataImages;

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
            players[i] = new Player(name, i);
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

    public String getPlayerScore(){
        return players[getPlayerIndex()].getScore().toString();
    }

    public String getName(){
        return players[getPlayerIndex()].getName();
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public String getWinner() {
        String winner = "";
        if (playerCount == 1) {
            String name = (players[0].getName().equals("Player1")) ? "Player" : players[0].getName();
            winner = name + ", ваш результат: " + players[0].getScore().toString();
        } else {
            if (players[0].getScore() == players[1].getScore()){
                winner = "Итог игры - ничья: " + players[0].getName() + " и " + players[1].getName() + " набрали " + players[0].getScore().toString() + " балл(а)";
            } else {
                for (int i = 0; i < playerCount; i++) {
                    winner += "Счет игрока " + players[i].getName() + " : " + players[i].getScore().toString() + " балл(ов)" + "\n";
            }
                if (players[0].getScore() > players[1].getScore()) {
                    winner += "Победил(а) " + players[0].getName() + ", ваш результат: " + players[0].getScore().toString() + " балл(ов)";
                } else {
                    winner += "Победил(а) " + players[1].getName() + ", ваш результат: " + players[1].getScore().toString() + " балл(ов)";
                }
            }
        }
        return winner;
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