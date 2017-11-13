package DataBase;

import javafx.scene.image.Image;

import java.util.Map;

public class Score {
    public Player[] players;
    private int playerCount;

    public Score(int playerCount){
        this.playerCount = playerCount;
        players = new Player [playerCount];
    }

    public void initPlayers(String name1, String name2){
        Player player1 = new Player(name1, 1);
        Player player2 = new Player (name2, 2);
    }

    public void initPlayer(String name){
        Player player = new Player(name, 1);
        players[0] = player;
    }

    public void checkAnswer(Player player, String answer, Image im){
        Answers maps = new Answers();
        for (Map.Entry<Image, String> map : maps.answer.entrySet()){
            if (map.getKey().equals(im) && map.getValue().equals(answer)){
                player.updateScore();
            }
        }
    }

}


