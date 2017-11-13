package DataBase;

import javafx.scene.image.Image;

import java.util.LinkedHashMap;

public class Answers {
    LinkedHashMap<Object, String> answer = new LinkedHashMap<>();
    public Answers(){
        Image im1 = new Image(getClass().getResourceAsStream("География.png"));
        Image im2 = new Image(getClass().getResourceAsStream("Геометрия.png"));
        Image im3 = new Image(getClass().getResourceAsStream("Русский Язык.png"));
        Image im4 = new Image(getClass().getResourceAsStream("Рисование.png"));
        Image im5 = new Image(getClass().getResourceAsStream("История.png"));
        answer.put(im1, "география");
        answer.put(im2, "геометрия");
        answer.put(im3, "русский язык");
        answer.put(im4, "рисование");
        answer.put(im5, "история");
    }
}
