package DataBase.Image;

import javafx.scene.image.Image;

public class DataImages {
    private static final DataImages instance;
    private final int COUNT = 12;
    private boolean check;
    private Image[] images = new Image[COUNT + 3];
    private String[] answers = new String[COUNT];

    private DataImages(){
        check = false;
        images[0] = new Image(getClass().getResourceAsStream("География.png"));
        images[1] = new Image(getClass().getResourceAsStream("Геометрия.png"));
        images[2] = new Image(getClass().getResourceAsStream("Русский Язык.png"));
        images[3] = new Image(getClass().getResourceAsStream("Рисование.png"));
        images[4] = new Image(getClass().getResourceAsStream("История.png"));
        images[5] =  new Image(getClass().getResourceAsStream("Отрезок.png"));
        images[6] =  new Image(getClass().getResourceAsStream("Арбуз.jpg"));
        images[7] =  new Image(getClass().getResourceAsStream("Кисточка.jpg"));
        images[8] =  new Image(getClass().getResourceAsStream("Задача.jpg"));
        images[9] =   new Image(getClass().getResourceAsStream("Господин.jpg"));
        images[10] =  new Image(getClass().getResourceAsStream("Карлсон.jpg"));
        images[11] =  new Image(getClass().getResourceAsStream("Урок.jpg"));
        images[images.length - 3] =  new Image(getClass().getResourceAsStream("itis.jpg"));
        images[images.length - 2] =  new Image(getClass().getResourceAsStream("11-702.jpg"));
        images[images.length - 1] =  new Image(getClass().getResourceAsStream("deb.jpg"));
        answers[0] = "география";
        answers[1] = "геометрия";
        answers[2] = "русский язык";
        answers[3] = "рисование";
        answers[4] = "история";
        answers[5] = "отрезок";
        answers[6] = "арбуз";
        answers[7] = "кисточка";
        answers[8] = "задача";
        answers[9] = "господин";
        answers[10] = "карлсон";
        answers[11] = "урок";
    }

    static {
        instance = new DataImages();
    }

    public static DataImages getDataImages(){
        return instance;
    }

    public void changeCheck(){
        check = !check;
    }

    public Boolean getCheck() {
        return check;
    }

    public Image getImage(int i) {
        return images[i];
    }

    public String getAnswer(int i) {
        return answers[i];
    }

    public int getAnswerLength(){
        return answers.length;
    }

    public int getImagesLength(){
        return images.length;
    }
}