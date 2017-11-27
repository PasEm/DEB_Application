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

    public void initPlayers(String...names){
        for (String name : names) {
            playerCount++;
        }
        players = new Player[playerCount];
        int i = 0;
        for (String name : names) {
            players[i] = new Player(name, i);
            i++;
        }
    }

    public void checkAnswer(String input, Image im){
        for (int i = 0; i < data.getAnswerLength(); i++) {
            if (input.toLowerCase().equals(data.getAnswer(i))){
                System.out.println(data.getImage(i).getClass().getName().equals(im.getClass().getName()));
            }
            if (input.toLowerCase().equals(data.getAnswer(i)) && data.getImage(i).getClass().getName().equals(im.getClass().getName())) {
                players[(playerCount == 1) ? 0 : i % 2].updateScore();
            }
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
                winner = "Победил(а) " + players[0].getName() + ", ваш результат: " + players[0].getScore().toString() + " балл(ов)";
            } else{
                if (players[0].getScore() < players[1].getScore()) {
                    winner = "Победил(а) " + players[1].getName() + ", ваш результат: " + players[1].getScore().toString() + " балл(ов)";
                } else{
                    winner = "Итог игры - ничья: " + players[0].getName() + " и " + players[1].getName() + " набрали " + players[0].getScore().toString() + " балл(а)";
                }
            }
        }
        for (int i = 0; i < playerCount; i++){
            players[i] = null;
        }
        return winner;
    }

    public void newGame(){
        for (int i = 0; i < playerCount; i++){
            players[i] = null;
        }
        playerCount = 0;
    }

    static {
        instance = new Score();
    }

    public static Score getScore(){
        return instance;
    }
}