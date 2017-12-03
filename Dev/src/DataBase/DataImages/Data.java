package DataBase.DataImages;

import javafx.scene.image.Image;

public class Data {
    private static final Data instance;
    private static final int COUNT = 12;
    private boolean check;
    private static Image[] images = new Image[COUNT + 3];
    private static String[] answers = new String[COUNT];

    private Data(){
        check = false;
        Image im1 = new Image(getClass().getResourceAsStream("География.png"));
        Image im2 = new Image(getClass().getResourceAsStream("Геометрия.png"));
        Image im3 = new Image(getClass().getResourceAsStream("Русский Язык.png"));
        Image im4 = new Image(getClass().getResourceAsStream("Рисование.png"));
        Image im5 = new Image(getClass().getResourceAsStream("История.png"));
        Image im6 = new Image(getClass().getResourceAsStream("Отрезок.png"));
        Image im7 = new Image(getClass().getResourceAsStream("Арбуз.jpg"));
        Image im8 = new Image(getClass().getResourceAsStream("Косточка.jpg"));
        Image im9 = new Image(getClass().getResourceAsStream("Задача.jpg"));
        Image im10 = new Image(getClass().getResourceAsStream("Господин.jpg"));
        Image im11 = new Image(getClass().getResourceAsStream("Карлсон.jpg"));
        Image im12 = new Image(getClass().getResourceAsStream("Урок.jpg"));
        Image im13 = new Image(getClass().getResourceAsStream("itis.jpg"));
        Image im14 = new Image(getClass().getResourceAsStream("11-702.jpg"));
        Image im15 = new Image(getClass().getResourceAsStream("deb.jpg"));
        images[0] = im1;
        images[1] = im2;
        images[2] = im3;
        images[3] = im4;
        images[4] = im5;
        images[5] = im6;
        images[6] = im7;
        images[7] = im8;
        images[8] = im9;
        images[9] = im10;
        images[10] = im11;
        images[11] = im12;
        images[images.length - 3] = im13;
        images[images.length - 2] = im14;
        images[images.length - 1] = im15;
        answers[0] = "география";
        answers[1] = "геометрия";
        answers[2] = "русский язык";
        answers[3] = "рисование";
        answers[4] = "история";
        answers[5] = "отрезок";
        answers[6] = "арбуз";
        answers[7] = "косточка";
        answers[8] = "задача";
        answers[9] = "господин";
        answers[10] = "карлсон";
        answers[11] = "урок";
    }

    static {
        instance = new Data();
    }

    public static Data getData(){
        return instance;
    }

    public static int getCOUNT() {
        return COUNT;
    }

    public void changeCheck(){
        check = !check;
    }

    public Boolean getCheck() {
        return check;
    }

    public static Image getImage(int i) {
        return images[i];
    }

    public static String getAnswer(int i) {
        return answers[i];
    }

    public int getAnswerLength(){
        return answers.length;
    }

    public int getImagesLength(){
        return images.length;
    }
}