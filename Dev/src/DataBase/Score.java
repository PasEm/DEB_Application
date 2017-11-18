package DataBase;

import javafx.scene.image.Image;

import java.util.Map;

public class Score {
    public Player[] players;
    private int playerCount;
    private int count = -1;

    public Score(){
       playerCount = 0;
    }

    public void initPlayers(String...names){
        for (String name : names) {
            playerCount++;
        }
        players = new Player[playerCount];
        int i = -1;
        for (String name : names) {
            players[++i] = new Player(name, i);
        }
    }


    public void checkAnswer(Answers data, String input, Image im){

      Answers maps = new Answers();
        /*for (Map.Entry<Image, String> map : maps.answer.entrySet()){     // проверка по мапу
            if (map.getKey().equals(im) && map.getValue().equals(input.toLowerCase())){
                players[count = (playerCount == 1) ? 0 : ++count % 2].updateScore();
                break;
            }
        }*/
      for (int i = 0; i < data.answers.length; i++){
          if (input.equals(data.answers[i]) && im.equals(data.images[i])){
              players[count = (playerCount == 1) ? 0 : ++count % 2].updateScore();
              break;
          }
      }
    }

    public String getWinner(){
        String winner;
        if (playerCount == 1){
            winner = players[0].getName() + ", ваш результат: " + players[0].getScore().toString();
        } else{
            if (players[0].getScore() > players[1].getScore()){
                winner = "Победил " + players[0].getName() + ", ваш результат: " + players[0].getScore().toString();
            } else{
                if (players[0].getScore() < players[1].getScore()) {
                    winner = "Победил " + players[1].getName() + ", ваш результат: " + players[0].getScore().toString();
                } else{
                    winner = "Итог игры: ничья. " + players[0].getName() + " и " + players[1].getName() + " набрали по " + players[0].getScore().toString();
                }
            }
        }
        return winner;
    }

}


