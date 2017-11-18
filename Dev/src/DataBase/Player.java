package DataBase;

public class Player {
    private String name;
    private Integer score;

    public Player(String name, int index){
        this.score = 0;
        setName(name, index);
    }

    public void setName(String s, int index) {
        StringBuffer name = new StringBuffer();
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
            name.append("Player").append(index);
        }
        this.name = "";
        for (int i = 0; i < name.length(); i++) {
            this.name += name.charAt(i);
        }
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public void updateScore(){
            this.score++;
        }
}
