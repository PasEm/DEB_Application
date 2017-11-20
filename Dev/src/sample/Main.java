package sample;

import DataBase.Data;
import DataBase.Score;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            completeTask();
        }

        private void completeTask() {
            try {
                // допустим, выполнение займет 10 секунд
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Image image = new Image(getClass().getResourceAsStream("deb.jpg"));
        ImageView img = new ImageView(image);
        img.setTranslateX(60);
        img.setFitWidth(1800);
        img.setFitHeight(1080);

        Image itis = new Image(getClass().getResourceAsStream("ITIS.jpg"));
        ImageView imgItis = new ImageView(itis);
        imgItis.setTranslateX(1740);
        imgItis.setTranslateY(960);
        imgItis.setFitWidth(120);
        imgItis.setFitHeight(120);

        Image itis11702 = new Image(getClass().getResourceAsStream("11-702.jpg"));
        ImageView imgItis11702 = new ImageView(itis11702);
        imgItis11702.setTranslateX(1620);
        imgItis11702.setTranslateY(960);
        imgItis11702.setFitWidth(120);
        imgItis11702.setFitHeight(120);
        root.getChildren().addAll(img, imgItis, imgItis11702);

        /////////////////////////////

        MenuItem startGame = new MenuItem("Новая игра");
        MenuItem settings = new MenuItem("Настройки");
        MenuItem help = new MenuItem("Справка");
        MenuItem credits = new MenuItem("Авторы");
        MenuItem exitGame = new MenuItem("Выход");
        MenuItem singleplay = new MenuItem("1 игрок");
        MenuItem multiplay = new MenuItem("2 игрока");

        MenuItem backCredits = new MenuItem("Назад");
        MenuItem backHelp = new MenuItem("Назад");
        MenuItem backToMenu = new MenuItem("Назад");
        MenuItem backSettings = new MenuItem("Назад");
        MenuItemSmall backSingle = new MenuItemSmall("Назад");
        MenuItemSmall backMulty = new MenuItemSmall("Назад");
        MenuItem backStartgame = new MenuItem("Назад");

        MenuItemSmall enterSingle = new MenuItemSmall("Ввод");
        MenuItemSmall enterMulty = new MenuItemSmall("Ввод");

        /////////////////////////////////////////////////////

        SubMenu mainMenu = new SubMenu(startGame, settings, help, credits, exitGame);

        SubMenu helpPage = new SubMenu(new Label("Описание игры, помощь и прочее"), backHelp);

        Label authors = new Label("В разработке игры участвовали:\nБулат Каюмов\nЭмиль Пашаев\nКакая-то красотка");
        SubMenu creditsPage = new SubMenu(authors, backCredits);

        SubMenu settingsPage = new SubMenu(new Label("Настройки"), backSettings);

        MenuItem exitYes = new MenuItem("Да");
        MenuItem exitNo = new MenuItem("Нет");
        SubMenu exitPage = new SubMenu(new Label("Вы уверены, что хотите выйти?"), exitYes, exitNo);

        TextField player = new TextField("Player1");
        SubMenu enterName = new SubMenu(new Label("Введите имя игрока:"), player, backSingle, enterSingle);

        TextField player1 = new TextField("Player1");
        TextField player2 = new TextField("Player2");
        SubMenu enterNames = new SubMenu(new Label("Введите имена игроков:"), player1, player2, backMulty, enterMulty);

        MenuItem goMenu = new MenuItem("Выход в главное меню");

        /////////////////////
        TimerTask timerTask = new MyTimerTask();
        Timer timer = new Timer(true);
        //////////////////////////
        Data data = new Data();
        MenuBox menubox = new MenuBox(mainMenu);
        SubMenu numberOfPlayers = new SubMenu(singleplay, multiplay, backToMenu);
        startGame.setOnMouseClicked(event -> menubox.setSubMenu(numberOfPlayers));
        backStartgame.setOnMouseClicked(event -> menubox.setSubMenu(numberOfPlayers));
        singleplay.setOnMouseClicked(event -> menubox.setSubMenu(enterName));
        enterSingle.setOnMouseClicked(event -> {
            Score result = new Score();
            result.initPlayers(player.getText());
            MenuItemSmall enterInput = new MenuItemSmall("Ввод ответа");
            TextField input = new TextField("Введите ваш ответ");
            SubMenu gamePage = new SubMenu(data.images[0], input, enterInput);
            menubox.setSubMenu(gamePage);

            MenuItemSmall enterInput1 = new MenuItemSmall("Ввод ответа");
            TextField input1 = new TextField("Введите ваш ответ");
            SubMenu gamePage1 = new SubMenu(data.images[1], input1, enterInput1);
            enterInput.setOnMouseClicked(event1 -> {
                result.checkAnswer(input.getText(), data.images[0]);
                menubox.setSubMenu(gamePage1);
            });

            MenuItemSmall enterInput2 = new MenuItemSmall("Ввод ответа");
            TextField input2 = new TextField("Введите ваш ответ");
            SubMenu gamePage2 = new SubMenu(data.images[2], input2, enterInput2);
            enterInput1.setOnMouseClicked(event1 -> {
                result.checkAnswer(input1.getText(), data.images[1]);
                menubox.setSubMenu(gamePage2);
            });

            MenuItemSmall enterInput3 = new MenuItemSmall("Ввод ответа");
            TextField input3 = new TextField("Введите ваш ответ");
            SubMenu gamePage3 = new SubMenu(data.images[3], input3, enterInput3);
            enterInput2.setOnMouseClicked(event1 -> {
                result.checkAnswer(input2.getText(), data.images[2]);
                menubox.setSubMenu(gamePage3);
            });

            MenuItemSmall enterInput4 = new MenuItemSmall("Ввод ответа");
            TextField input4 = new TextField("Введите ваш ответ");
            SubMenu gamePage4 = new SubMenu(data.images[4], input4, enterInput4);
            enterInput3.setOnMouseClicked(event1 -> {
                result.checkAnswer(input3.getText(), data.images[3]);
                menubox.setSubMenu(gamePage4);
            });

            MenuItemSmall enterInput5 = new MenuItemSmall("Ввод ответа");
            TextField input5 = new TextField("Введите ваш ответ");
            SubMenu gamePage5 = new SubMenu(data.images[5], input5, enterInput5);
            enterInput4.setOnMouseClicked(event1 -> {
                result.checkAnswer(input4.getText(), data.images[4]);
                menubox.setSubMenu(gamePage5);
            });

            enterInput5.setOnMouseClicked(event1 -> {
                result.checkAnswer(input5.getText(), data.images[5]);
                String s = result.getWinner();
                System.out.println(s);
                Label resultLabel = new Label();
                resultLabel.setText(s);
                SubMenu resultPage = new SubMenu(resultLabel, goMenu);
                menubox.setSubMenu(resultPage);
            });

        });
        backSingle.setOnMouseClicked(event -> menubox.setSubMenu(numberOfPlayers));
        multiplay.setOnMouseClicked(event -> menubox.setSubMenu(enterNames));
        enterMulty.setOnMouseClicked(event -> {
            Score result = new Score();
            result.initPlayers(player1.getText(), player2.getText());
            MenuItemSmall enterInput = new MenuItemSmall("Ввод ответа");
            TextField input = new TextField("Введите ваш ответ");
            SubMenu gamePage = new SubMenu(data.images[0], input, enterInput);
            menubox.setSubMenu(gamePage);

            MenuItemSmall enterInput1 = new MenuItemSmall("Ввод ответа");
            TextField input1 = new TextField("Введите ваш ответ");
            SubMenu gamePage1 = new SubMenu(data.images[1], input1, enterInput1);
            enterInput.setOnMouseClicked(event1 -> {
                result.checkAnswer(input.getText(), data.images[0]);
                menubox.setSubMenu(gamePage1);
            });

            MenuItemSmall enterInput2 = new MenuItemSmall("Ввод ответа");
            TextField input2 = new TextField("Введите ваш ответ");
            SubMenu gamePage2 = new SubMenu(data.images[2], input2, enterInput2);
            enterInput1.setOnMouseClicked(event1 -> {
                result.checkAnswer(input1.getText(), data.images[1]);
                menubox.setSubMenu(gamePage2);
            });

            MenuItemSmall enterInput3 = new MenuItemSmall("Ввод ответа");
            TextField input3 = new TextField("Введите ваш ответ");
            SubMenu gamePage3 = new SubMenu(data.images[3], input3, enterInput3);
            enterInput2.setOnMouseClicked(event1 -> {
                result.checkAnswer(input2.getText(), data.images[2]);
                menubox.setSubMenu(gamePage3);
            });

            MenuItemSmall enterInput4 = new MenuItemSmall("Ввод ответа");
            TextField input4 = new TextField("Введите ваш ответ");
            SubMenu gamePage4 = new SubMenu(data.images[4], input4, enterInput4);
            enterInput3.setOnMouseClicked(event1 -> {
                result.checkAnswer(input3.getText(), data.images[3]);
                menubox.setSubMenu(gamePage4);
            });

            MenuItemSmall enterInput5 = new MenuItemSmall("Ввод ответа");
            TextField input5 = new TextField("Введите ваш ответ");
            SubMenu gamePage5 = new SubMenu(data.images[5], input5, enterInput5);
            enterInput4.setOnMouseClicked(event1 -> {
                result.checkAnswer(input4.getText(), data.images[4]);
                menubox.setSubMenu(gamePage5);
            });

            enterInput5.setOnMouseClicked(event1 -> {
                result.checkAnswer(input5.getText(), data.images[5]);
                String s = result.getWinner();
                System.out.println(s);
                Label resultLabel = new Label();
                resultLabel.setText(s);
                SubMenu resultPage = new SubMenu(resultLabel, goMenu);
                menubox.setSubMenu(resultPage);
            });

        });
        backMulty.setOnMouseClicked(event -> menubox.setSubMenu(numberOfPlayers));
        settings.setOnMouseClicked(event -> menubox.setSubMenu(settingsPage));
        backSettings.setOnMouseClicked(event -> menubox.setSubMenu(mainMenu));
        help.setOnMouseClicked(event -> menubox.setSubMenu(helpPage));
        backHelp.setOnMouseClicked(event -> menubox.setSubMenu(mainMenu));
        credits.setOnMouseClicked(event -> menubox.setSubMenu(creditsPage));
        backCredits.setOnMouseClicked(event -> menubox.setSubMenu(mainMenu));
        exitGame.setOnMouseClicked(event -> menubox.setSubMenu(exitPage));
        exitYes.setOnMouseClicked(event -> System.exit(0));
        exitNo.setOnMouseClicked(event -> menubox.setSubMenu(mainMenu));
        backToMenu.setOnMouseClicked(event -> menubox.setSubMenu(mainMenu));
        goMenu.setOnMouseClicked(event1 -> menubox.setSubMenu(mainMenu));

        root.getChildren().addAll(menubox);
        Scene scene = new Scene(root, 1920, 1080);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                FadeTransition ft = new FadeTransition(Duration.seconds(0.225), menubox);
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
        primaryStage.setTitle("DEB");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static class MenuItem extends StackPane{ //пункт меню (кнопка)
        MenuItem(String name){
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
                bq.setFill(Color.WHITE);
            });
        }
    }

    static class MenuBox extends Pane{
        static SubMenu submenu;
        MenuBox(SubMenu submenu) {
            MenuBox.submenu = submenu;

            setVisible(false);//изначально не видно
            Rectangle bq = new Rectangle(1920, 1080, Color.LIGHTBLUE);// размер и цвет
            bq.setOpacity(0.4);// прозрачность
            getChildren().addAll(bq, submenu);
        }

        void setSubMenu(SubMenu submenu){
            getChildren().remove(MenuBox.submenu);
            MenuBox.submenu = submenu;
            getChildren().add(MenuBox.submenu);
        }
    }

    static class SubMenu extends VBox{

        SubMenu(MenuItem...items){
            setSpacing(30);
            setTranslateX(510);
            setTranslateY(400);
            for(MenuItem item : items){
                getChildren().addAll(item);
            }
        }

        SubMenu(Label text, MenuItem...items){
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

        SubMenu(Label text, TextField field, MenuItemSmall...items){
            HBox box = new HBox();
            setSpacing(30);
            setTranslateX(510);
            setTranslateY(400);
            field.maxWidth(300);
            field.setFont(Font.font("Arial", FontWeight.BOLD, 40));
            field.setMinHeight(90);
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

        SubMenu(Label text, TextField field1, TextField field2, MenuItemSmall...items){
            HBox box = new HBox();
            setSpacing(30);
            setTranslateX(510);
            setTranslateY(400);
            field1.maxWidth(485);
            field2.maxWidth(485);
            field1.setFont(Font.font("Arial", FontWeight.BOLD, 40));
            field2.setFont(Font.font("Arial", FontWeight.BOLD, 40));
            field1.setMinHeight(90);
            field2.setMinHeight(90);
            text.setFont(Font.font("Arial", FontPosture.ITALIC, 50));
            text.setTextFill(Color.DARKGOLDENROD);
            text.setMaxWidth(1000);
            text.setWrapText(true);
            box.setSpacing(30);
            getChildren().addAll(text, field1, field2, box);
            for(MenuItemSmall item : items) {
                box.getChildren().addAll(item);
            }
        }

        SubMenu(Image image, TextField field, MenuItemSmall item){
            ImageView view = new ImageView(image);
            view.setFitWidth(900);
            view.setFitHeight(540);
            field.setFont(Font.font("Arial", FontWeight.BOLD, 40));
            field.setMinHeight(90);
            HBox hbox = new HBox();
            VBox vbox = new VBox();
            hbox.setSpacing(30);
            hbox.setTranslateX(-30);
            hbox.getChildren().addAll(field, item);
            vbox.setTranslateY(170);
            vbox.setTranslateX(510);
            vbox.setSpacing(60);
            vbox.getChildren().addAll(view, hbox);
            getChildren().addAll(vbox);
        }

    }

    private static class MenuItemSmall extends StackPane {
        MenuItemSmall(String name) {
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