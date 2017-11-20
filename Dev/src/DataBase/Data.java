package DataBase;

import javafx.scene.image.Image;

import java.util.LinkedHashMap;

public class Data {
    public LinkedHashMap<Image, String> answer = new LinkedHashMap<>();
    public Image[] images = new Image[6];
    public String[] answers = new String[6];

    public Data(){
        Image im1 = new Image(getClass().getResourceAsStream("География.png"));
        Image im2 = new Image(getClass().getResourceAsStream("Геометрия.png"));
        Image im3 = new Image(getClass().getResourceAsStream("Русский Язык.png"));
        Image im4 = new Image(getClass().getResourceAsStream("Рисование.png"));
        Image im5 = new Image(getClass().getResourceAsStream("История.png"));
        Image im6 = new Image(getClass().getResourceAsStream("Отрезок.png"));

        /*        answer.put(im1, "география");
                answer.put(im2, "геометрия");
                answer.put(im3, "русский язык");
                answer.put(im4, "рисование");
                answer.put(im5, "история");
                answer.put(im6, "отрезок");
        */
        images[0] = im1;
        images[1] = im2;
        images[2] = im3;
        images[3] = im4;
        images[4] = im5;
        images[5] = im6;
        answers[0] = "география";
        answers[1] = "геометрия";
        answers[2] = "русский язык";
        answers[3] = "рисование";
        answers[4] = "история";
        answers[5] = "отрезок";
    }

}