package sample;

import DataBase.GameScore;
import DataBase.DataImages;
import DataBase.Sound;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public void start(Stage primaryStage)  {
        Pane root = new Pane();
        DataImages dataImages = DataImages.getDataImages();
        Sound sound = Sound.getSound();
        GameScore gameScore = GameScore.getGameScore();
        dataImages.randomize();
        ImageView img = new ImageView(dataImages.getImage(dataImages.getImagesLength() - 1));
        Image ico = dataImages.getImage(dataImages.getImagesLength() - 1);
        img.setTranslateX(60);
        img.setFitWidth(1800);
        img.setFitHeight(1080);

        ImageView imgItis = new ImageView(dataImages.getImage(dataImages.getImagesLength() - 2));
        imgItis.setTranslateX(1740);
        imgItis.setTranslateY(960);
        imgItis.setFitWidth(120);
        imgItis.setFitHeight(120);

        ImageView img11702 = new ImageView(dataImages.getImage(dataImages.getImagesLength() - 3));
        img11702.setTranslateX(1620);
        img11702.setTranslateY(960);
        img11702.setFitWidth(120);
        img11702.setFitHeight(120);
        root.getChildren().addAll(img, imgItis, img11702);

        ///////////////////////////////////////////////////////////////////////////////////////////////////

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
        MenuItem switchMusic = new MenuItem("Включить\\выключить музыку");
        MenuItem goMenu = new MenuItem("Выход в главное меню");

        SubMenu mainMenu = new SubMenu(startGame, settings, help, credits, exitGame);
        Label info = new Label("В данной игре вам нужно отгадывать ребусы, чтобы зарабатывать очки.\nМаксимальная длина имени - 15 символов");
        SubMenu helpPage = new SubMenu(info, backHelp);

        Label authors = new Label("В разработке игры участвовали:\nБулат Каюмов\nЭмиль Пашаев\nДиляра Мухамедшина");
        SubMenu creditsPage = new SubMenu(authors, backCredits);

        SubMenu settingsPage = new SubMenu(new Label("Настройки игры"), switchMusic, backSettings);

        MenuItem exitYes = new MenuItem("Да");
        MenuItem exitNo = new MenuItem("Нет");
        SubMenu exitPage = new SubMenu(new Label("Вы уверены, что хотите выйти?"), exitYes, exitNo);

        TextField player = new TextField();
        player.setPromptText("Player");
        SubMenu enterName = new SubMenu(new Label("Введите имя игрока:"), player, backSingle, enterSingle);

        TextField player1 = new TextField();
        TextField player2 = new TextField();
        player1.setPromptText("Player1");
        player2.setPromptText("Player2");
        SubMenu enterNames = new SubMenu(new Label("Введите имена игроков:"), player1, player2, backMulty, enterMulty);

        ImageView question = new ImageView();
        TextField input = new TextField();
        MenuItemSmall enter = new MenuItemSmall("Ввод ответа");
        Game game = new Game(question, input, enter);

        Label resultLabel = new Label();
        SubMenu resultPage = new SubMenu(resultLabel, goMenu);

        /////////////////////////////////////////////////////////////////////////////////////////////////

        MenuBox menubox = new MenuBox(mainMenu);
        SubMenu numberOfPlayers = new SubMenu(singleplay, multiplay, backToMenu);
        startGame.setOnMouseClicked(event -> menubox.setSubMenu(numberOfPlayers));
        backStartgame.setOnMouseClicked(event -> menubox.setSubMenu(numberOfPlayers));
        singleplay.setOnMouseClicked(event -> {
            menubox.setSubMenu(enterName);
            player.setFocusTraversable(false);
            player.clear();
        });
        enterSingle.setOnMouseClicked(event -> {
            gameScore.newGame(player.getText());
            dataImages.randomize();
            question.setImage(dataImages.getImage(dataImages.getQuestions(gameScore.getQuestionIndex())));
            menubox.beginGame(game);
        });
        backSingle.setOnMouseClicked(event -> menubox.setSubMenu(numberOfPlayers));
        multiplay.setOnMouseClicked(event -> {
            menubox.setSubMenu(enterNames);
            player1.setFocusTraversable(false);
            player1.clear();
            player2.setFocusTraversable(false);
            player2.clear();
        });
        enterMulty.setOnMouseClicked(event -> {
            gameScore.newGame(player1.getText(), player2.getText());
            dataImages.randomize();
            question.setImage(dataImages.getImage(dataImages.getQuestions(gameScore.getQuestionIndex())));
            menubox.beginGame(game);
        });
        backMulty.setOnMouseClicked(event -> menubox.setSubMenu(numberOfPlayers));
        settings.setOnMouseClicked(event -> menubox.setSubMenu(settingsPage));
        switchMusic.setOnMouseClicked(event -> {
            if (!sound.isPlaying()) {
                sound.play();
            } else {
                sound.stop();
            }
        });
        enter.setOnMouseClicked(event -> {
            gameScore.checkAnswer(input.getText(), dataImages.getQuestions(gameScore.getQuestionIndex()));
            game.clearField();
            if(gameScore.getQuestionIndex() != 10) {
                question.setImage(dataImages.getImage(dataImages.getQuestions(gameScore.getQuestionIndex())));
                game.changeImage(question, gameScore.getLastName() + ", ваш счёт : " + gameScore.getLastScore());
            } else {
                resultLabel.setText(gameScore.getWinner());
                menubox.endGame(resultPage);
                game.clearScore();
            }
        });
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
                    ft.setOnFinished(evt -> menubox.setVisible(false));
                    ft.play();
                }
            }
        });

        primaryStage.getIcons().add(ico);
        primaryStage.setTitle("DEB");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static class MenuItem extends StackPane{

        MenuItem(String name){
            Rectangle bq = new Rectangle(900, 90, Color.WHITE);
            bq.setOpacity(0.5);
            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Arial", FontWeight.BOLD, 50));
            setAlignment(Pos.CENTER);
            getChildren().addAll(bq, text);
            FillTransition st = new FillTransition(Duration.seconds(0.5), bq);
            setOnMouseEntered(event -> {
                st.setFromValue(Color.DARKGREY);
                st.setToValue(Color.DARKGOLDENROD);
                st.setCycleCount(Animation.INDEFINITE);
                st.setAutoReverse(true);
                st.play();
            });
            setOnMouseExited(event -> {
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
        Label score = new Label("Ваш счёт : 0");

        Game(ImageView img, TextField field, MenuItemSmall item){
            view = img;
            view.setFitWidth(900);
            view.setFitHeight(540);
            answer = field;
            answer.setPromptText("Введите ваш ответ");
            answer.setFont(Font.font("Arial", FontWeight.BOLD, 40));
            answer.setMinHeight(90);
            score.setFont(Font.font("Arial", FontWeight.BOLD, 60));
            score.setTextFill(Color.WHITE);
            score.setTranslateX(-175);
            score.setTranslateY(25);
            score.centerShapeProperty();
            hBox.setSpacing(30);
            hBox.setTranslateX(-30);
            hBox.getChildren().addAll(field, item);
            vBox.setTranslateY(0);
            vBox.setTranslateX(510);
            vBox.setSpacing(60);
            vBox.getChildren().addAll(score, view, hBox);
            getChildren().addAll(vBox);
        }

        void changeImage(ImageView image, String text){
            this.vBox.getChildren().removeAll(score, this.view, hBox);
            this.view = image;
            this.score.setText(text);
            this.vBox.getChildren().addAll(score, this.view, hBox);
        }

        void clearField(){
            this.answer.clear();
        }

        void clearScore(){
            this.score.setText("Ваш счёт : 0");
        }
    }

    static class MenuBox extends Pane{
        static SubMenu submenu;
        static Game game;

        MenuBox(SubMenu submenu) {
            MenuBox.submenu = submenu;
            setVisible(false);
            Rectangle bq = new Rectangle(1920, 1080, Color.LIGHTBLUE);
            bq.setOpacity(0.4);
            getChildren().addAll(bq, submenu);
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
            setTranslateX(500);
            setTranslateY(400);
            for(MenuItem item : items){
                getChildren().addAll(item);
            }
        }

        SubMenu(Label text, MenuItem... items){
            setSpacing(30);
            setTranslateX(500);
            setTranslateY(400);
            text.setFont(Font.font("Arial", FontPosture.ITALIC, 50));
            text.setTextFill(Color.LIGHTYELLOW);
            text.setMaxWidth(1000);
            text.setWrapText(true);
            text.setAlignment(Pos.CENTER);
            getChildren().addAll(text);
            for (MenuItem item : items) {
                getChildren().addAll(item);
            }
        }

        SubMenu(Label text, TextField field, MenuItemSmall... items){
            HBox box = new HBox();
            setSpacing(30);
            setTranslateX(500);
            setTranslateY(400);
            field.maxWidth(300);
            field.setFont(Font.font("Arial", FontWeight.BOLD, 40));
            field.setMinHeight(90);
            text.setFont(Font.font("Arial", FontPosture.ITALIC, 50));
            text.setTextFill(Color.LIGHTYELLOW);
            text.setAlignment(Pos.CENTER);
            text.setMaxWidth(1000);
            text.setWrapText(true);
            box.setSpacing(30);
            getChildren().addAll(text, field, box);
            for(MenuItemSmall item : items) {
                box.getChildren().addAll(item);
            }
        }

        SubMenu(Label text, TextField field1, TextField field2, MenuItemSmall... items){
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
            text.setAlignment(Pos.CENTER);
            text.setWrapText(true);
            box.setSpacing(30);
            getChildren().addAll(text, field1, field2, box);
            for(MenuItemSmall item : items) {
                box.getChildren().addAll(item);
            }
        }
    }

    private static class MenuItemSmall extends StackPane {
        MenuItemSmall(String name) {
            Rectangle bq = new Rectangle(435, 90, Color.WHITE);
            bq.setOpacity(0.5);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Arial", FontWeight.BOLD, 50));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bq, text);
            FillTransition st = new FillTransition(Duration.seconds(0.5), bq);
            setOnMouseEntered(event -> {
                st.setFromValue(Color.DARKGREY);
                st.setToValue(Color.DARKGOLDENROD);
                st.setCycleCount(Animation.INDEFINITE);
                st.setAutoReverse(true);
                st.play();
            });
            setOnMouseExited(event -> {
                st.stop();
                bq.setFill(Color.WHITE);
            });
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
