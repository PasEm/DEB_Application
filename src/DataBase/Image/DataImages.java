package DataBase.Image;

import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.Random;

public class DataImages {
    private static final DataImages instance;
    private final int COUNT = 32;
    private Image[] images = new Image[COUNT + 3];
    private String[] answers = new String[COUNT];
    private int[] questions = new int[COUNT];

    private DataImages(){
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
        images[12] = new Image(getClass().getResourceAsStream("Банан.png"));
        images[13] = new Image(getClass().getResourceAsStream("Брюнет.png"));
        images[14] = new Image(getClass().getResourceAsStream("Вазон.png"));
        images[15] = new Image(getClass().getResourceAsStream("Вокзал.png"));
        images[16] = new Image(getClass().getResourceAsStream("Ворон.png"));
        images[17] = new Image(getClass().getResourceAsStream("Забота.png"));
        images[18] = new Image(getClass().getResourceAsStream("Заяц.png"));
        images[19] = new Image(getClass().getResourceAsStream("Камин.png"));
        images[20] = new Image(getClass().getResourceAsStream("Карусель.png"));
        images[21] = new Image(getClass().getResourceAsStream("Косуля.png"));
        images[22] = new Image(getClass().getResourceAsStream("Купол.png"));
        images[23] = new Image(getClass().getResourceAsStream("Лодка.png"));
        images[24] = new Image(getClass().getResourceAsStream("Манеж.png"));
        images[25] = new Image(getClass().getResourceAsStream("Манекен.png"));
        images[26] = new Image(getClass().getResourceAsStream("Медведь.png"));
        images[27] = new Image(getClass().getResourceAsStream("Переделка.png"));
        images[28] = new Image(getClass().getResourceAsStream("Подвал.png"));
        images[29] = new Image(getClass().getResourceAsStream("Река.png"));
        images[30] = new Image(getClass().getResourceAsStream("Светофор.png"));
        images[31] = new Image(getClass().getResourceAsStream("Японец.png"));
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
        answers[12] = "банан";
        answers[13] = "брюнет";
        answers[14] = "вазон";
        answers[15] = "вокзал";
        answers[16] = "ворон";
        answers[17] = "забота";
        answers[18] = "заяц";
        answers[19] = "камин";
        answers[20] = "карусель";
        answers[21] = "косуля";
        answers[22] = "купол";
        answers[23] = "лодка";
        answers[24] = "манеж";
        answers[25] = "манекен";
        answers[26] = "медведь";
        answers[27] = "переделка";
        answers[28] = "подвал";
        answers[29] = "река";
        answers[30] = "светофор";
        answers[31] = "японец";
    }

    static {
        instance = new DataImages();
    }

    public static DataImages getDataImages(){
        return instance;
    }

    public Image getImage(int i) {
        return images[i];
    }

    public String getAnswer(int i) {
        return answers[i];
    }

    public int getQuestions(int index){
        return questions[index];
    }

    public int getImagesLength(){
        return images.length;
    }

    public void randomize(){
        final Random random = new Random();
        LinkedList<Integer> index = new LinkedList<>();
        int count = COUNT;
        for (int i = 0; i < questions.length; i++){
            index.add(i);
        }
        for (int i = 0; i < questions.length; i++){
            int position = random.nextInt(count);
            questions[i] = index.get(position);
            index.remove(position);
            count--;
        }
    }
}
