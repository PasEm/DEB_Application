package sample;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override

/////000000000000000000000000000000000000000000000000000000000000000000000000000000

    public void start(Stage primaryStage) throws Exception{
        Pane root = new Pane();
        Image image = new Image(getClass().getResourceAsStream("deb.jpg")); //вставка картинки на фон
        ImageView img = new ImageView(image);
        img.setTranslateX(60);
        img.setFitWidth(1800);
        img.setFitHeight(1080);
        root.getChildren().add(img);

        Image itis = new Image(getClass().getResourceAsStream("ITIS.jpg"));
        ImageView imgItis = new ImageView(itis);
        imgItis.setTranslateX(1740);
        imgItis.setTranslateY(960);
        imgItis.setFitWidth(120);
        imgItis.setFitHeight(120);
        root.getChildren().add(imgItis);

        Image itis11702 = new Image(getClass().getResourceAsStream("11-702.jpg"));
        ImageView imgItis11702 = new ImageView(itis11702);
        imgItis11702.setTranslateX(1620);
        imgItis11702.setTranslateY(960);
        imgItis11702.setFitWidth(120);
        imgItis11702.setFitHeight(120);
        root.getChildren().add(imgItis11702);

        MenuItem startgame = new MenuItem("Новая игра"); // создаем пункты меню
        MenuItem settings = new MenuItem("Настройки");
        MenuItem help = new MenuItem("Справка");
        MenuItem credits = new MenuItem("Авторы");
        MenuItem exitgame = new MenuItem("Выход");
        SubMenu mainmenu = new SubMenu(startgame, settings, help, credits, exitgame); // создаем страницу с данными пунктами

        MenuItem singleplay = new MenuItem("1 игрок");
        MenuItem multiplay = new MenuItem("2 игрока");
        MenuItem backToMainMenu = new MenuItem("Назад");
        SubMenu numberOfPlayers = new SubMenu(singleplay, multiplay, backToMainMenu);

        Label reference = new Label("Описание игры, помощь и прочее");
        MenuItem helpPageBack = new MenuItem("Назад");
        SubMenu helpPage = new SubMenu(reference, helpPageBack);

        Label authors = new Label("В разработке игры участвовали:\nБулат Каюмов\nЭмиль Пашаев\nКакая-то красотка");
        MenuItem creditsPageBack = new MenuItem("Назад");
        SubMenu creditsPage = new SubMenu(authors, creditsPageBack);

        Label exitAsk = new Label("Вы уверены, что хотите выйти?");
        MenuItem exitYes = new MenuItem("Да");
        MenuItem exitNo = new MenuItem("Нет");
        SubMenu exitPage = new SubMenu(exitAsk, exitYes, exitNo);

        Label enterPlayer = new Label("Введите имя игрока");
        TextField enterName = new TextField("Player1");
        enterName.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        enterName.setMinHeight(90);
        MenuItemSmall backToNumberOfPlayers = new MenuItemSmall("Назад");
        MenuItemSmall enter = new MenuItemSmall("Ввод");
        SubMenu enterPlayerName = new SubMenu(enterPlayer, enterName, backToNumberOfPlayers, enter);

        MenuBox menubox = new MenuBox(mainmenu);

        startgame.setOnMouseClicked(event -> menubox.setSubMenu(numberOfPlayers));// переключение между пунктами меню (см. SetSubMenu)
        exitgame.setOnMouseClicked(event -> menubox.setSubMenu(exitPage));
        exitYes.setOnMouseClicked(event -> System.exit(0));
        exitNo.setOnMouseClicked(event -> menubox.setSubMenu(mainmenu));
        backToMainMenu.setOnMouseClicked(event -> menubox.setSubMenu(mainmenu));
        help.setOnMouseClicked(event -> menubox.setSubMenu(helpPage));
        credits.setOnMouseClicked(event -> menubox.setSubMenu(creditsPage));
        helpPageBack.setOnMouseClicked(event -> menubox.setSubMenu(mainmenu));
        creditsPageBack.setOnMouseClicked(event -> menubox.setSubMenu(mainmenu));
        singleplay.setOnMouseClicked(event -> menubox.setSubMenu(enterPlayerName));
        backToNumberOfPlayers.setOnMouseClicked(event -> menubox.setSubMenu(numberOfPlayers  ));

        root.getChildren().addAll(menubox);

        Label name = new Label();
        name.setText("D  E  B");// текст
        name.setTranslateX(700);// координты текста
        name.setTranslateY(120);
        name.setFont(Font.font("Showcard gothic", FontWeight.BOLD, 150)); // шрифт и размер шрифта
        name.setTextFill(Color.DARKGOLDENROD);
        name.setScaleX(1);// толщина
        name.setScaleY(1);

        Scene scene = new Scene(root, 1920, 1080);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                FadeTransition ft = new FadeTransition(Duration.seconds(1),menubox);
                if (!menubox.isVisible()) {
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();
                    menubox.setVisible(true);
                }
                else{
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt ->   menubox.setVisible(false));
                    ft.play();
                }
            }
        });

        root.getChildren().addAll(name);

        primaryStage.setTitle("DEB"); // задаем заголовок окна
        primaryStage.setScene(scene);
        primaryStage.show(); // запускаем окно
    }

    public class Player{
        private int count = 0;
        private StringBuffer name;
        private int score;

        public Player (StringBuffer name){
            setName(name);
            this.name = name;
            this.score = 0;
            count++;
        }

        public void setName(StringBuffer name) {
            if (name.length() > 30){
                name.delete(31, name.length());
            }
            for (int i = 0; i < name.length(); i++){
                char c = name.charAt(i);
                if (c == ' '){
                    name.deleteCharAt(i);
                    i--;
                }
            }
            if (name.length() == 0){
                name.append("Player").append(count);
            }
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public StringBuffer getName() {
            return name;
        }
    }

    private static class MenuItem extends StackPane{ //пункт меню (кнопка)
        public MenuItem(String name){
            Rectangle bq = new Rectangle(900, 90, Color.WHITE); // размер и цвет пункта
            bq.setOpacity(0.5); // прозрачность

            Text text = new Text(name); // текст
            text.setFill(Color.WHITE); // цвет текста
            text.setFont(Font.font("Arial", FontWeight.BOLD, 50)); // шрифт, параметр(не обязательно, в данном случае - жирный), размер

            setAlignment(Pos.CENTER); //расположение
            getChildren().addAll(bq, text);
            FillTransition st = new FillTransition(Duration.seconds(0.5), bq); //при наведение мыши меняет цвет. Так же в скобках указывается время смены цвета
            setOnMouseEntered(event -> {
                st.setFromValue(Color.DARKGREY);
                st.setToValue(Color.DARKGOLDENROD);
                st.setCycleCount(Animation.INDEFINITE);
                st.setAutoReverse(true); //постоянно меняет и обратно
                st.play();
            });
            setOnMouseExited(event -> { //если убрать мышь, то придет в изначальное состояние
                st.stop();
                bq.setFill(Color.WHITE);;
            });
        }
    }

    public static class MenuBox extends Pane{//панель меню
        static SubMenu submenu;
        public MenuBox(SubMenu submenu) {
            MenuBox.submenu = submenu;

            setVisible(false);//изначально не видно
            Rectangle bq = new Rectangle(1920, 1080, Color.LIGHTBLUE);// размер и цвет
            bq.setOpacity(0.4);// прозрачность
            getChildren().addAll(bq, submenu);
        }

            public void setSubMenu(SubMenu submenu){ // переключение между пунктами меню
                getChildren().remove(MenuBox.submenu);
                MenuBox.submenu = submenu;
                getChildren().add(MenuBox.submenu);
        }
    }

    public static class SubMenu extends VBox{ //пункты меню
        public SubMenu(MenuItem...items){
            setSpacing(30);// отступ
            setTranslateX(510);// координаты
            setTranslateY(400);
            for(MenuItem item : items){//несколько пунктов
                getChildren().addAll(item);
            }
        }
        public SubMenu(Label text, MenuItem...items){
            setSpacing(30);
            setTranslateX(510);
            setTranslateY(400);
            text.setFont(Font.font("Arial", FontPosture.ITALIC, 50));
            text.setTextFill(Color.DARKGOLDENROD);
            text.setMaxWidth(1000);
            text.setWrapText(true);
            getChildren().addAll(text);
            for (MenuItem item : items) {
                getChildren().addAll(item);
            }
        }
        public SubMenu(Label text, TextField field, MenuItemSmall...items){
            HBox box = new HBox();
            setSpacing(30);
            setTranslateX(510);
            setTranslateY(400);
            text.setFont(Font.font("Arial", FontPosture.ITALIC, 50));
            text.setTextFill(Color.DARKGOLDENROD);
            text.setMaxWidth(1000);
            text.setWrapText(true);
            box.setSpacing(30);
            getChildren().addAll(text, field, box);
            for(MenuItemSmall item : items) {
                box.getChildren().addAll(item);
            }
        }
    }

    private static class MenuItemSmall extends StackPane{ // (кнопка Ввод, возможно, в будущем, еще какая-нибудь)
        public MenuItemSmall(String name){
            Rectangle bq = new Rectangle(435, 90, Color.WHITE); // размер и цвет пункта
            bq.setOpacity(0.5); // прозрачность

            Text text = new Text(name); // текст
            text.setFill(Color.WHITE); // цвет текста
            text.setFont(Font.font("Arial", FontWeight.BOLD, 50)); // шрифт, параметр(не обязательно, в данном случае - жирный), размер

            setAlignment(Pos.CENTER); //расположение
            getChildren().addAll(bq, text);
            FillTransition st = new FillTransition(Duration.seconds(0.5), bq); //при наведение мыши меняет цвет. Так же в скобках указывается время смены цвета
            setOnMouseEntered(event -> {
                st.setFromValue(Color.DARKGREY);
                st.setToValue(Color.DARKGOLDENROD);
                st.setCycleCount(Animation.INDEFINITE);
                st.setAutoReverse(true); //постоянно меняет и обратно
                st.play();
            });
            setOnMouseExited(event -> { //если убрать мышь, то придет в изначальное состояние
                st.stop();
                bq.setFill(Color.WHITE);
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}