package sample;

import DataBase.DataImages.Data;
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

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Data data = Data.getData();
        Score result = Score.getScore();
        ImageView img = new ImageView(data.getImage(data.getImagesLength() - 1));
        Image ico = data.getImage(data.getImagesLength() - 1);
        img.setTranslateX(60);
        img.setFitWidth(1800);
        img.setFitHeight(1080);

        ImageView imgItis = new ImageView(data.getImage(data.getImagesLength() - 2));
        imgItis.setTranslateX(1740);
        imgItis.setTranslateY(960);
        imgItis.setFitWidth(120);
        imgItis.setFitHeight(120);

        ImageView img11702 = new ImageView(data.getImage(data.getImagesLength() - 3));
        img11702.setTranslateX(1620);
        img11702.setTranslateY(960);
        img11702.setFitWidth(120);
        img11702.setFitHeight(120);
        root.getChildren().addAll(img, imgItis, img11702);

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

        TextField player = new TextField();
        player.setPromptText("Player");
        SubMenu enterName = new SubMenu(new Label("Введите имя игрока:"), player, backSingle, enterSingle);

        TextField player1 = new TextField();
        player1.setPromptText("Player1");
        TextField player2 = new TextField();
        player2.setPromptText("Player2");
        SubMenu enterNames = new SubMenu(new Label("Введите имена игроков:"), player1, player2, backMulty, enterMulty);

        MenuItem goMenu = new MenuItem("Выход в главное меню");

        final Random random = new Random();
        final int finalIndex = random.nextInt(1000000) % data.getAnswerLength();

        //////////////////////////////////////////////////

        MenuBox menubox = new MenuBox(mainMenu);
        SubMenu numberOfPlayers = new SubMenu(singleplay, multiplay, backToMenu);
        startGame.setOnMouseClicked(event -> menubox.setSubMenu(numberOfPlayers));
        backStartgame.setOnMouseClicked(event -> menubox.setSubMenu(numberOfPlayers));
        singleplay.setOnMouseClicked(event -> {
            menubox.setSubMenu(enterName);
            player.setPromptText("Player");//текст поверх текстфилда
            player.setFocusTraversable(false);//чтобы не было начальной фокусировкм
        });
        ImageView view = new ImageView();
        TextField input = new TextField();
        MenuItemSmall enter = new MenuItemSmall("Ввод");
        Game game = new Game(view, input, enter);
        final int count = 10;
        Image images[] = new Image[count];
        String answers[] = new String[count];

        Label resultLabel = new Label();
        SubMenu resultPage = new SubMenu(resultLabel, goMenu);

        enterSingle.setOnMouseClicked(event -> {
            result.newGame(player.getText());
            int countAll = Data.getCOUNT(), r = 0;
            Image imagesCopy[] = new Image[countAll];
            System.arraycopy(Data.getImages(), 0, imagesCopy, 0, countAll);
            String answersCopy[] = new String[countAll];
            System.arraycopy(Data.getAnswers(), 0, answersCopy, 0, countAll);
            for(int i = 0; i < count; i++){
                r = random.nextInt(countAll);
                images[i] = imagesCopy[r];
                answers[i] = answersCopy[r];
                countAll--;
                for(int j = r; j < countAll; j++){
                    imagesCopy[j] = imagesCopy[j+1];
                }
            }
            view.setImage(images[0]);
            menubox.beginGame(game);
        });
        enter.setOnMouseClicked(event -> {
            result.checkAnswer(input.getText(), answers[NUMBER.getNumber()], NUMBER.getNumber());
            NUMBER.increase();
            game.clearField();
            if(NUMBER.getNumber() != 10) {
                view.setImage(images[NUMBER.getNumber()]);
                game.changeImage(view);
            } else {
                resultLabel.setText(result.getWinner());
                menubox.endGame(resultPage);
            }
        });

        /*
            MenuItemSmall enterInput1 = new MenuItemSmall("Ввод ответа");
            TextField input1 = new TextField("Введите ваш ответ");
            int nextIndex1 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage1 = new SubMenu(data.getImage(nextIndex1), input1, enterInput1);
            menubox.setSubMenu(gamePage1);

            MenuItemSmall enterInput2 = new MenuItemSmall("Ввод ответа");
            TextField input2 = new TextField();
            int nextIndex2 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage2 = new SubMenu(data.getImage(nextIndex2), input2, enterInput2);
            enterInput1.setOnMouseClicked(event1 -> {
                result.checkAnswer(input1.getText(), data.getImage(nextIndex1));
                menubox.setSubMenu(gamePage2);
            });

            MenuItemSmall enterInput3 = new MenuItemSmall("Ввод ответа");
            TextField input3 = new TextField();
            int nextIndex3 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage3 = new SubMenu(data.getImage(nextIndex3), input3, enterInput3);
            enterInput2.setOnMouseClicked(event1 -> {
                result.checkAnswer(input2.getText(), data.getImage(nextIndex2));
                menubox.setSubMenu(gamePage3);
            });

            MenuItemSmall enterInput4 = new MenuItemSmall("Ввод ответа");
            TextField input4 = new TextField();
            int nextIndex4 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage4 = new SubMenu(data.getImage(nextIndex4), input4, enterInput4);
            enterInput3.setOnMouseClicked(event1 -> {
                result.checkAnswer(input3.getText(), data.getImage(nextIndex3));
                menubox.setSubMenu(gamePage4);
            });

            MenuItemSmall enterInput5 = new MenuItemSmall("Ввод ответа");
            TextField input5 = new TextField();
            int nextIndex5 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage5 = new SubMenu(data.getImage(nextIndex5), input5, enterInput5);
            enterInput4.setOnMouseClicked(event1 -> {
                result.checkAnswer(input4.getText(), data.getImage(nextIndex4));
                menubox.setSubMenu(gamePage5);
            });

            MenuItemSmall enterInput6 = new MenuItemSmall("Ввод ответа");
            TextField input6 = new TextField();
            int nextIndex6 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage6 = new SubMenu(data.getImage(nextIndex6), input6, enterInput6);
            enterInput5.setOnMouseClicked(event1 -> {
                result.checkAnswer(input5.getText(), data.getImage(nextIndex5));
                menubox.setSubMenu(gamePage6);
            });

            MenuItemSmall enterInput7 = new MenuItemSmall("Ввод ответа");
            TextField input7 = new TextField();
            int nextIndex7 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage7 = new SubMenu(data.getImage(nextIndex7), input7, enterInput7);
            enterInput6.setOnMouseClicked(event1 -> {
                result.checkAnswer(input6.getText(), data.getImage(nextIndex6));
                menubox.setSubMenu(gamePage7);
            });

            MenuItemSmall enterInput8 = new MenuItemSmall("Ввод ответа");
            TextField input8 = new TextField();
            int nextIndex8 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage8 = new SubMenu(data.getImage(nextIndex8), input8, enterInput8);
            enterInput7.setOnMouseClicked(event1 -> {
                result.checkAnswer(input7.getText(), data.getImage(nextIndex7));
                menubox.setSubMenu(gamePage8);
            });

            MenuItemSmall enterInput9 = new MenuItemSmall("Ввод ответа");
            TextField input9 = new TextField();
            int nextIndex9 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage9 = new SubMenu(data.getImage(nextIndex9), input9, enterInput9);
            enterInput8.setOnMouseClicked(event1 -> {
                result.checkAnswer(input8.getText(), data.getImage(nextIndex8));
                menubox.setSubMenu(gamePage9);
            });

            MenuItemSmall enterInput10 = new MenuItemSmall("Ввод ответа");
            TextField input10 = new TextField();
            int nextIndex10 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage10 = new SubMenu(data.getImage(nextIndex10), input10, enterInput10);
            enterInput9.setOnMouseClicked(event1 -> {
                result.checkAnswer(input9.getText(), data.getImage(nextIndex9));
                menubox.setSubMenu(gamePage10);
            });

            MenuItemSmall enterInput11 = new MenuItemSmall("Ввод ответа");
            TextField input11 = new TextField();
            int nextIndex11 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage11 = new SubMenu(data.getImage(nextIndex11), input11, enterInput11);
            enterInput10.setOnMouseClicked(event1 -> {
                result.checkAnswer(input10.getText(), data.getImage(nextIndex10));
                menubox.setSubMenu(gamePage11);
            });

            MenuItemSmall finalEnter = new MenuItemSmall("Ввод ответа");
            TextField finalInput = new TextField();
            SubMenu finalGamePage = new SubMenu(data.getImage(finalIndex), finalInput, finalEnter);
            enterInput11.setOnMouseClicked(event1 -> {
                result.checkAnswer(input11.getText(), data.getImage(nextIndex11));
                menubox.setSubMenu(finalGamePage);
            });

            finalEnter.setOnMouseClicked(event1 -> {
                result.checkAnswer(finalInput.getText(), data.getImage(finalIndex));
                Label resultLabel = new Label(result.getWinner());
                SubMenu resultPage = new SubMenu(resultLabel, goMenu);
                menubox.setSubMenu(resultPage);
            });*/

        backSingle.setOnMouseClicked(event -> menubox.setSubMenu(numberOfPlayers));
        multiplay.setOnMouseClicked(event -> {
            menubox.setSubMenu(enterNames);
            player1.setPromptText("Player1");
            player1.setFocusTraversable(false);
            player2.setPromptText("Player2");
            player2.setFocusTraversable(false);
        });
        /*enterMulty.setOnMouseClicked(event -> {
            result.newGame(player1.getText(), player2.getText() );

            MenuItemSmall enterInput1 = new MenuItemSmall("Ввод ответа");
            TextField input1 = new TextField("Введите ваш ответ");
            int nextIndex1 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage1 = new SubMenu(data.getImage(nextIndex1), input1, enterInput1);
            menubox.setSubMenu(gamePage1);

            MenuItemSmall enterInput2 = new MenuItemSmall("Ввод ответа");
            TextField input2 = new TextField();
            int nextIndex2 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage2 = new SubMenu(data.getImage(nextIndex2), input2, enterInput2);
            enterInput1.setOnMouseClicked(event1 -> {
                result.checkAnswer(input1.getText(), data.getImage(nextIndex1));
                menubox.setSubMenu(gamePage2);
            });

            MenuItemSmall enterInput3 = new MenuItemSmall("Ввод ответа");
            TextField input3 = new TextField();
            int nextIndex3 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage3 = new SubMenu(data.getImage(nextIndex3), input3, enterInput3);
            enterInput2.setOnMouseClicked(event1 -> {
                result.checkAnswer(input2.getText(), data.getImage(nextIndex2));
                menubox.setSubMenu(gamePage3);
            });

            MenuItemSmall enterInput4 = new MenuItemSmall("Ввод ответа");
            TextField input4 = new TextField();
            int nextIndex4 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage4 = new SubMenu(data.getImage(nextIndex4), input4, enterInput4);
            enterInput3.setOnMouseClicked(event1 -> {
                result.checkAnswer(input3.getText(), data.getImage(nextIndex3));
                menubox.setSubMenu(gamePage4);
            });

            MenuItemSmall enterInput5 = new MenuItemSmall("Ввод ответа");
            TextField input5 = new TextField();
            int nextIndex5 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage5 = new SubMenu(data.getImage(nextIndex5), input5, enterInput5);
            enterInput4.setOnMouseClicked(event1 -> {
                result.checkAnswer(input4.getText(), data.getImage(nextIndex4));
                menubox.setSubMenu(gamePage5);
            });

            MenuItemSmall enterInput6 = new MenuItemSmall("Ввод ответа");
            TextField input6 = new TextField();
            int nextIndex6 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage6 = new SubMenu(data.getImage(nextIndex6), input6, enterInput6);
            enterInput5.setOnMouseClicked(event1 -> {
                result.checkAnswer(input5.getText(), data.getImage(nextIndex5));
                menubox.setSubMenu(gamePage6);
            });

            MenuItemSmall enterInput7 = new MenuItemSmall("Ввод ответа");
            TextField input7 = new TextField();
            int nextIndex7 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage7 = new SubMenu(data.getImage(nextIndex7), input7, enterInput7);
            enterInput6.setOnMouseClicked(event1 -> {
                result.checkAnswer(input6.getText(), data.getImage(nextIndex6));
                menubox.setSubMenu(gamePage7);
            });

            MenuItemSmall enterInput8 = new MenuItemSmall("Ввод ответа");
            TextField input8 = new TextField();
            int nextIndex8 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage8 = new SubMenu(data.getImage(nextIndex8), input8, enterInput8);
            enterInput7.setOnMouseClicked(event1 -> {
                result.checkAnswer(input7.getText(), data.getImage(nextIndex7));
                menubox.setSubMenu(gamePage8);
            });

            MenuItemSmall enterInput9 = new MenuItemSmall("Ввод ответа");
            TextField input9 = new TextField();
            int nextIndex9 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage9 = new SubMenu(data.getImage(nextIndex9), input9, enterInput9);
            enterInput8.setOnMouseClicked(event1 -> {
                result.checkAnswer(input8.getText(), data.getImage(nextIndex8));
                menubox.setSubMenu(gamePage9);
            });

            MenuItemSmall enterInput10 = new MenuItemSmall("Ввод ответа");
            TextField input10 = new TextField();
            int nextIndex10 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage10 = new SubMenu(data.getImage(nextIndex10), input10, enterInput10);
            enterInput9.setOnMouseClicked(event1 -> {
                result.checkAnswer(input9.getText(), data.getImage(nextIndex9));
                menubox.setSubMenu(gamePage10);
            });

            MenuItemSmall enterInput11 = new MenuItemSmall("Ввод ответа");
            TextField input11 = new TextField();
            int nextIndex11 = random.nextInt(1000000) % data.getAnswerLength();
            SubMenu gamePage11 = new SubMenu(data.getImage(nextIndex11), input11, enterInput11);
            enterInput10.setOnMouseClicked(event1 -> {
                result.checkAnswer(input10.getText(), data.getImage(nextIndex10));
                menubox.setSubMenu(gamePage11);
            });

            MenuItemSmall finalEnter = new MenuItemSmall("Ввод ответа");
            TextField finalInput = new TextField();
            SubMenu finalGamePage = new SubMenu(data.getImage(finalIndex), finalInput, finalEnter);
            enterInput11.setOnMouseClicked(event1 -> {
                result.checkAnswer(input11.getText(), data.getImage(nextIndex11));
                menubox.setSubMenu(finalGamePage);
            });

            finalEnter.setOnMouseClicked(event1 -> {
                Label resultLabel = new Label(result.getWinner());
                result.checkAnswer(finalInput.getText(), data.getImage(finalIndex));
                SubMenu resultPage = new SubMenu(resultLabel, goMenu);
                menubox.setSubMenu(resultPage);
            });
        });*/
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

        primaryStage.getIcons().add(ico);
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
        static Game game;
        MenuBox(SubMenu submenu) {
            MenuBox.submenu = submenu;

            setVisible(false);//изначально не видно
            Rectangle bq = new Rectangle(1920, 1080, Color.LIGHTBLUE);// размер и цвет
            bq.setOpacity(0.4);// прозрачность
            getChildren().addAll(bq, submenu);
        }
        MenuBox(Game game){
            MenuBox.game = game;

            Rectangle bq = new Rectangle(1920, 1080, Color.LIGHTBLUE);// размер и цвет
            bq.setOpacity(0.4);// прозрачность
            getChildren().addAll(bq, game);
        }

        void setSubMenu(SubMenu submenu){
            getChildren().remove(MenuBox.submenu);
            MenuBox.submenu = submenu;
            getChildren().add(MenuBox.submenu);
        }
        void beginGame(Game game){
            getChildren().remove(MenuBox.submenu);
            MenuBox.game = game;
            getChildren().add(MenuBox.game);
        }

        void endGame(SubMenu submenu){
            getChildren().remove(MenuBox.game);
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
            text.setTextFill(Color.LIGHTYELLOW);
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
            text.setTextFill(Color.LIGHTYELLOW);
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
            text.setTextFill(Color.LIGHTYELLOW);
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

    private static class Game extends Pane{
        ImageView view = new ImageView();
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        TextField answer = new TextField();
        int score = 0;

        Game(){
        }

        Game(ImageView img, TextField field, MenuItemSmall item){
            view = img;
            view.setFitWidth(900);
            view.setFitHeight(540);
            answer = field;
            answer.setPromptText("Введите ваш ответ");
            answer.setFont(Font.font("Arial", FontWeight.BOLD, 40));
            answer.setMinHeight(90);
            hBox.setSpacing(30);
            hBox.setTranslateX(-30);
            hBox.getChildren().addAll(field, item);
            vBox.setTranslateY(170);
            vBox.setTranslateX(510);
            vBox.setSpacing(60);
            vBox.getChildren().addAll(view, hBox);
            getChildren().addAll(vBox);
        }
        public void changeImage(ImageView image){
            this.vBox.getChildren().removeAll(this.view, hBox);
            this.view = image;
            this.vBox.getChildren().addAll(this.view, hBox);
        }

        public void clearField(){
            this.answer.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}