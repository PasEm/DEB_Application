package DataBase;

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
        images[0] = new Image("file:Resources/Images/География.png");
        images[1] = new Image("file:Resources/Images/Геометрия.png");
        images[2] = new Image("file:Resources/Images/Русский Язык.png");
        images[3] = new Image("file:Resources/Images/Рисование.png");
        images[4] = new Image("file:Resources/Images/История.png");
        images[5] = new Image("file:Resources/Images/Отрезок.png");
        images[6] = new Image("file:Resources/Images/Арбуз.jpg");
        images[7] = new Image("file:Resources/Images/Кисточка.jpg");
        images[8] = new Image("file:Resources/Images/Задача.jpg");
        images[9] = new Image("file:Resources/Images/Господин.jpg");
        images[10] = new Image("file:Resources/Images/Карлсон.jpg");
        images[11] = new Image("file:Resources/Images/Урок.jpg");
        images[12] = new Image("file:Resources/Images/Банан.png");
        images[13] = new Image("file:Resources/Images/Брюнет.png");
        images[14] = new Image("file:Resources/Images/Вазон.png");
        images[15] = new Image("file:Resources/Images/Вокзал.png");
        images[16] = new Image("file:Resources/Images/Ворон.png");
        images[17] = new Image("file:Resources/Images/Забота.png");
        images[18] = new Image("file:Resources/Images/Заяц.png");
        images[19] = new Image("file:Resources/Images/Камин.png");
        images[20] = new Image("file:Resources/Images/Карусель.png");
        images[21] = new Image("file:Resources/Images/Косуля.png");
        images[22] = new Image("file:Resources/Images/Купол.png");
        images[23] = new Image("file:Resources/Images/Лодка.png");
        images[24] = new Image("file:Resources/Images/Манеж.png");
        images[25] = new Image("file:Resources/Images/Манекен.png");
        images[26] = new Image("file:Resources/Images/Медведь.png");
        images[27] = new Image("file:Resources/Images/Переделка.png");
        images[28] = new Image("file:Resources/Images/Подвал.png");
        images[29] = new Image("file:Resources/Images/Река.png");
        images[30] = new Image("file:Resources/Images/Светофор.png");
        images[31] = new Image("file:Resources/Images/Японец.png");
        images[images.length - 3] = new Image("file:Resources/Images/itis.jpg");
        images[images.length - 2] = new Image("file:Resources/Images/11-702.jpg");
        images[images.length - 1] = new Image("file:Resources/Images/deb.jpg");
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

    String getAnswer(int i) {
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
