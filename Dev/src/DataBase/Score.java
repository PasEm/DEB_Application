package DataBase;

import javafx.scene.image.Image;

public class Score {
    public Player[] players;
    private int playerCount;
    private Data data = new Data();

    public Score(){
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
        /*for (Map.Entry<Image, String> map : maps.answer.entrySet()){     // проверка по мапу
            if (map.getKey().equals(im) && map.getValue().equals(input.toLowerCase())){
                players[count = (playerCount == 1) ? 0 : ++count % 2].updateScore();
                break;
            }
        }*/
        // при проверке изображений всегда выдается false - непонятно, нужна помощь (im.equals(
      for (int i = 0; i < data.answers.length; i++){
          if (input.toLowerCase().equals(data.answers[i])){
              players[(playerCount == 1) ? 0 : i % 2].updateScore();
          }
      }
    }

    public String getWinner(){
        String winner;
        if (playerCount == 1){
            winner = players[0].getName() + ", ваш результат: " + players[0].getScore().toString();
        } else{
            if (players[0].getScore() > players[1].getScore()){
                winner = "Победил " + players[0].getName() + ", ваш результат: " + players[0].getScore().toString() + " балл(ов)";
            } else{
                if (players[0].getScore() < players[1].getScore()) {
                    winner = "Победил " + players[1].getName() + ", ваш результат: " + players[1].getScore().toString() + " балл(ов)";
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

}