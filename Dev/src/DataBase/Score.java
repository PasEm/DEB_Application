package DataBase;

import DataBase.DataImages.Data;
import javafx.scene.image.Image;

public class Score {
    private static final Score instance;
    public Player[] players;
    private int playerCount;
    private Data data = Data.getData();

    private Score(){
       playerCount = 0;
    }

    private void initPlayers(String...names){
        players = new Player[playerCount];
        int i = 0;
        for (String name : names) {
            players[i] = new Player(name, i);
            i++;
        }
    }

    public void checkAnswer(String input, String answer, int k){
            if (input.toLowerCase().equals(answer)) {
                players[(playerCount == 1) ? 0 : k % 2].updateScore();
        }
    }

    public String getWinner(){
        String winner;
        if (playerCount == 1){
            String name = players[0].getName();
            name = (name.equals("Player1")) ? "Player" : name;
            winner = name + ", ваш результат: " + players[0].getScore().toString();
        } else{
            if (players[0].getScore() > players[1].getScore()){
                winner = "Победил(а) " + players[0].getName() + ", ваш результат: " + players[0].getScore().toString() + " очк";
                if(players[0].getScore() / 10 != 1 && players[0].getScore() % 10 == 1){
                    winner += "о";
                } else {
                    if(players[0].getScore() / 10 != 1 && (players[0].getScore() % 10 == 2 || players[0].getScore() % 10 == 3 || players[0].getScore() % 10 == 4)){
                        winner += "а";
                    } else {
                        winner += "ов";
                    }
                }
            } else{
                if (players[0].getScore() < players[1].getScore()) {
                    winner = "Победил(а) " + players[1].getName() + ", ваш результат: " + players[1].getScore().toString() + " очк";
                } else{
                    winner = "Итог игры - ничья: " + players[1].getName() + " и " + players[1].getName() + " набрали " + players[1].getScore().toString() + " очк";
                }
                if(players[1].getScore() / 10 != 1 && players[1].getScore() % 10 == 1){
                    winner += "о";
                } else {
                    if(players[1].getScore() / 10 != 1 && (players[1].getScore() % 10 == 2 || players[1].getScore() % 10 == 3 || players[1].getScore() % 10 == 4)){
                        winner += "а";
                    } else {
                        winner += "ов";
                    }
                }
            }
        }
        for (int i = 0; i < playerCount; i++){
            players[i] = null;
        }
        return winner;
    }

    public void newGame(String...names){
        for (int i = 0; i < playerCount; i++){
            players[i] = null;
        }
        playerCount = 0;
        String name[] = new String[2];
        for (String input: names){
            name[playerCount] = input;
            playerCount++;
        }
        if (playerCount == 1) {
            initPlayers(name[0]);
        } else{
            initPlayers(name);
        }
    }

    static {
        instance = new Score();
    }

    public static Score getScore(){
        return instance;
    }

    public Player getPlayer(int i) {
        return players[i];
    }
}