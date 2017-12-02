package DataBase;

class Player {
    private String name;
    private int score;

    Player(String name, int index){
        this.score = 0;
        setName(name, index);
    }

    private void setName(String s, int index) {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            name.append(s.charAt(i));
        }
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == ' ') {
                name.deleteCharAt(i);
                i--;
            } else {
                break;
            }
        }
        if (name.length() > 15) {
            name.delete(16, name.length());
        }
        if (name.length() == 0) {
            name.append("Player").append(++index);
        }
        this.name = name.toString();
    }

    String getName() {
        return name;
    }

    Integer getScore() {
        return score;
    }

    void updateScore(){
            score++;
        }
}