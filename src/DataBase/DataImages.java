package DataBase;

import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.Random;

public class DataImages {
    private static final DataImages instance;
    private final int COUNT = 32;
    private LinkedList<Image> images = new LinkedList<>();
    private LinkedList<String> answers = new LinkedList<>();
    private int[] questions = new int[COUNT];

    private DataImages(){
        images.add(new Image("file:Resources/Images/География.png"));
        images.add(new Image("file:Resources/Images/Геометрия.png"));
        images.add(new Image("file:Resources/Images/Русский Язык.png"));
        images.add(new Image("file:Resources/Images/Рисование.png"));
        images.add(new Image("file:Resources/Images/История.png"));
        images.add(new Image("file:Resources/Images/Отрезок.png"));
        images.add(new Image("file:Resources/Images/Арбуз.jpg"));
        images.add(new Image("file:Resources/Images/Кисточка.jpg"));
        images.add(new Image("file:Resources/Images/Задача.jpg"));
        images.add(new Image("file:Resources/Images/Господин.jpg"));
        images.add(new Image("file:Resources/Images/Карлсон.jpg"));
        images.add(new Image("file:Resources/Images/Урок.jpg"));
        images.add(new Image("file:Resources/Images/Банан.png"));
        images.add(new Image("file:Resources/Images/Брюнет.png"));
        images.add(new Image("file:Resources/Images/Вазон.png"));
        images.add(new Image("file:Resources/Images/Вокзал.png"));
        images.add(new Image("file:Resources/Images/Ворон.png"));
        images.add(new Image("file:Resources/Images/Забота.png"));
        images.add(new Image("file:Resources/Images/Заяц.png"));
        images.add(new Image("file:Resources/Images/Камин.png"));
        images.add(new Image("file:Resources/Images/Карусель.png"));
        images.add(new Image("file:Resources/Images/Косуля.png"));
        images.add(new Image("file:Resources/Images/Купол.png"));
        images.add(new Image("file:Resources/Images/Лодка.png"));
        images.add(new Image("file:Resources/Images/Манеж.png"));
        images.add(new Image("file:Resources/Images/Манекен.png"));
        images.add(new Image("file:Resources/Images/Медведь.png"));
        images.add(new Image("file:Resources/Images/Переделка.png"));
        images.add(new Image("file:Resources/Images/Подвал.png"));
        images.add(new Image("file:Resources/Images/Река.png"));
        images.add(new Image("file:Resources/Images/Светофор.png"));
        images.add(new Image("file:Resources/Images/Японец.png"));
        images.add(new Image("file:Resources/Images/itis.jpg"));
        images.add(new Image("file:Resources/Images/11-702.jpg"));
        images.add(new Image("file:Resources/Images/deb.jpg"));
        answers.add("география");
        answers.add("геометрия");
        answers.add("русский язык");
        answers.add("рисование");
        answers.add("история");
        answers.add("отрезок");
        answers.add("арбуз");
        answers.add("кисточка");
        answers.add("задача");
        answers.add("господин");
        answers.add("карлсон");
        answers.add("урок");
        answers.add("банан");
        answers.add("брюнет");
        answers.add("вазон");
        answers.add("вокзал");
        answers.add("ворон");
        answers.add("забота");
        answers.add("заяц");
        answers.add("камин");
        answers.add("карусель");
        answers.add("косуля");
        answers.add("купол");
        answers.add("лодка");
        answers.add("манеж");
        answers.add("манекен");
        answers.add("медведь");
        answers.add("переделка");
        answers.add("подвал");
        answers.add("река");
        answers.add("светофор");
        answers.add("японец");
    }

    static {
        instance = new DataImages();
    }

    public static DataImages getDataImages(){
        return instance;
    }

    public Image getImage(int i) {
        return images.get(i);
    }

    String getAnswer(int i) {
        return answers.get(i);
    }

    public int getQuestions(int index){
        return questions[index];
    }

    public int getImagesLength(){
        return images.size();
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
