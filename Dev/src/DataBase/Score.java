package DataBase;

import DataBase.DataImages.Data;
import javafx.scene.image.Image;

import java.util.HashMap;

public class Score {
    private static final Score instance;
    public Player[] players;
    private int playerCount;
    private int index;
    private Data data = Data.getData();

    private Score() {
        playerCount = 0;
        index = 0;
    }

    private void initPlayers(String... names) {
        players = new Player[playerCount];
        int i = 0;
        for (String name : names) {
            players[i] = new Player(name, i);
            i++;
        }
    }

    public void checkAnswer(String input, int image) {
        if (input.toLowerCase().equals(data.getAnswer(image))) {
            players[(playerCount == 1) ? 0 :index++  % 2].updateScore();
        }
    }

    public String getWinner() {
        String winner = "";
        if (playerCount == 1) {
            String name = players[0].getName();
            name = (name.equals("Player1")) ? "Player" : name;
            winner = name + ", ваш результат: " + players[0].getScore().toString();
        } else {
            for (int i = 0; i < playerCount; i++){
                winner += "Счет игрока " + players[i].getName() + " : " + players[i].getScore().toString() + " балл(ов)" + "\n";
            }
            if (players[0].getScore() > players[1].getScore()) {
                winner += "Победил(а) " + players[0].getName() + ", ваш результат: " + players[0].getScore().toString() + " балл(ов)";
            } else {
                if (players[0].getScore() < players[1].getScore()) {
                    winner += "Победил(а) " + players[1].getName() + ", ваш результат: " + players[1].getScore().toString() + " балл(ов)";
                } else {
                    winner = "\n" + "Итог игры - ничья: " + players[0].getName() + " и " + players[1].getName() + " набрали " + players[0].getScore().toString() + " балл(а)";
                }
            }
        }
        return winner;
    }

    public void newGame(String... names) {
        for (int i = 0; i < playerCount; i++) {
            players[i] = null;
        }
        playerCount = 0;
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
        instance = new Score();
    }

    public static Score getScore() {
        return instance;
    }
}