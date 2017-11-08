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
    public void start(Stage primaryStage) throws Exception{
        Pane root = new Pane();
        Image image = new Image(getClass().getResourceAsStream("S05KA3351.jpg")); //вставка картинки на фон
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
        TextMenu helpPage = new TextMenu(reference, helpPageBack);

        Label authors = new Label("В разработке игры участвовали:\nБулат Каюмов\nЭмиль Пашаев\nДиляра Мухамедшина");
        MenuItem creditsPageBack = new MenuItem("Назад");
        TextMenu creditsPage = new TextMenu(authors, creditsPageBack);

        MenuBox menubox = new MenuBox(mainmenu);

        startgame.setOnMouseClicked(event -> menubox.setSubMenu(numberOfPlayers));// переключение между пунктами меню (см. SetSubMenu)
        exitgame.setOnMouseClicked(event -> System.exit(0));
        backToMainMenu.setOnMouseClicked(event -> menubox.setSubMenu(mainmenu));
        help.setOnMouseClicked(event -> menubox.setSubMenuToTextMenu(helpPage));
        credits.setOnMouseClicked(event -> menubox.setSubMenuToTextMenu(creditsPage));
        helpPageBack.setOnMouseClicked(event -> menubox.setTextMenuToSubmenu(mainmenu));
        creditsPageBack.setOnMouseClicked(event -> menubox.setTextMenuToSubmenu(mainmenu));

        root.getChildren().addAll(menubox);

        Label name = new Label();
        name.setText("Name of Game");// текст
        name.setTranslateX(450);// координты текста
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
        static TextMenu textmenu; // пробую с текстменю
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
            public void setSubMenuToTextMenu(TextMenu textmenu){
                getChildren().remove(MenuBox.submenu);
                MenuBox.textmenu = textmenu;
                getChildren().add(MenuBox.textmenu);
            }
            public void setTextMenuToSubmenu(SubMenu submenu){ // переключение между пунктами меню
            getChildren().remove(MenuBox.textmenu);
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
    }

    public static class TextMenu extends VBox{ // Текстовые страницы + кнопка Назад
        public TextMenu(Label text, MenuItem item){
            setSpacing(30);
            setTranslateX(510);
            setTranslateY(400);
            text.setFont(Font.font("Arial", FontPosture.ITALIC, 50));
            text.setTextFill(Color.DARKGOLDENROD);
            text.setMaxWidth(1000);
            text.setWrapText(true);
            getChildren().addAll(text, item);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}