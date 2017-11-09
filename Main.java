package PersonandScore;

public class Main {
    public static void main(String[] args){

    }

    public class Answers{
        public static final int answerCount = 5; // количество вопросов в приложении, пока что в наличии 5 ребусов
        public String[] answerData = new String [answerCount];
        /*
         *здесь идет конструктов класса - т.е. вбивание данных в массив answerData
         * его можно заменить на обычный массив ответов, все зависит от того, чем удобней пользоваться
         * этот класс нужно доработать, связать с изображениями, будет готово ближе к субботе, если мне покажете,
         * как они буду вводиться, возможно нужно будет полностью переделать Contoller.java and sample.fxml
         */
        public Answers(){
            answerData[0] = "геометрия";
            answerData[1] = "рисование";
            answerData[2] = "география";
            answerData[3] = "история";
            answerData[4] = "русский язык";
        }

    }
    public class Player{
        private StringBuffer name;
        private int score;
        public Player(StringBuffer name, int index){
            this.score = 0;
            setName(name, index);
        }

        public void setName(StringBuffer name, int index) {
            for (int i = 0; i < name.length(); i++){
                if (name.charAt(i) == ' '){
                    name.deleteCharAt(i);
                    i--;
                } else {
                    break;
                }
            }
            if (name.length()> 15){
                name.delete(16, name.length());
            }
            if (name.length() == 0){
                name.append("Player").append(index);
            }
            this.name = name;
        }

        public StringBuffer getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
        // обновления результата Игрока
        public void updateScore(int count){
            this.score += count;
        }
    }

    public class playerScore{
        private Player[] players;
        private int playerCount;

        private playerScore(int playerCount){
            this.playerCount = playerCount;
            players = new Player [playerCount];
        }
        // метод инициализации для 2 игроков
        public void initPlayers(StringBuffer name1, StringBuffer name2){
            Player player1 = new Player(name1, 1);
            Player player2 = new Player (name2, 2);
        }
        // метод инициализации 1 игрока
        public void initPlayer(StringBuffer name){
            Player player = new Player(name, 1);
        }
        // проверка ответа Игрока, пока что образно, так как нет связи с изображениями
        public void checkAnswer(Answers data, Player player, String answer, int answerIndex){
            if (data.answerData[answerIndex].equals(answer.toLowerCase())){
                player.updateScore(1);
            }
        }

    }
}
