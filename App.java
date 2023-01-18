/*
TODO:
View diagram on a package

    In the Project tool window, right-click a package for which you want to create a diagram and select Diagrams | Show Diagram Ctrl+Alt+Shift+U).

    In the list that opens, select Java Class Diagram. IntelliJ IDEA generates a UML diagram for classes and their dependencies.
*/

package com.example.battlecrawler2;

import com.example.battlecrawler2.gamecontents.Game;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application
{
    private final int width = 800, height = 650; // width is v and height is v1
    private static Game game;

    public static void main(String[] args) throws Exception
    {
        if (args.length > 1)
        {
            System.out.println("Please try again and thank you for playing!");
            throw new Exception("Wrong number of arguments, game only accepts one boolean (0/1) argument.");
        }
        else
        {
            if (args.length == 0)
                game = new Game("0");
            else
                game = new Game(args[0]);
            launch();
        }
    }

    @Override
    public void start(Stage mainStage)
    {
        // all the basics of the program
        // basic fonts
        Font bigFont = Font.font("Arial", FontWeight.BOLD, 30);
        Font font = new Font("Arial", 20); // TODO: use
        // image locations
        String directory = System.getProperty("user.dir") + "\\";


        // canvases
        Canvas mainCanvas = new Canvas(width, height);
        mainCanvas.getGraphicsContext2D().drawImage(new Image(directory + "forest1.jpg"), 0, 0, width, height);

        Canvas startCanvas = new Canvas(width, height);
        startCanvas.getGraphicsContext2D().drawImage(new Image(directory + "forest2.jpg"), 0, 0, width, height);

        Canvas buttonHolderCanvas = new Canvas(width, 175);
        buttonHolderCanvas.getGraphicsContext2D().setFill(Color.DIMGRAY);
        buttonHolderCanvas.getGraphicsContext2D().fillRect(0, 0, buttonHolderCanvas.getWidth(), buttonHolderCanvas.getHeight());


        // images
        ImageView heroImage = new ImageView(new Image(directory + "hero.png", 200, 200, false, false));
        ImageView goblinImage = new ImageView(new Image(directory + "goblin.png", 200, 200, false, false));


        // panes
        // the grid pane and its settings
        GridPane mainGridPane = new GridPane();
        mainGridPane.setVgap(50); // minimum vertical gap between anything put in the grid
        mainGridPane.setHgap(20); // minimum horizontal gap between anything put in the grid
        mainGridPane.setAlignment(Pos.TOP_CENTER); // align the whole grid on the center

        GridPane buttonGridPane = new GridPane();
        buttonGridPane.setVgap(5); // minimum vertical gap between anything put in the grid
        buttonGridPane.setHgap(25); // minimum horizontal gap between anything put in the grid
        buttonGridPane.setAlignment(Pos.BOTTOM_CENTER); // align the whole grid on the center

        // the border pane
        BorderPane startBorderPane = new BorderPane();

        // the stack panes, which goes on the scene
        StackPane mainStackPane = new StackPane(mainCanvas, mainGridPane);
        mainStackPane.setAlignment(Pos.TOP_CENTER);

        StackPane buttonStackPane = new StackPane(buttonHolderCanvas, buttonGridPane);
//        buttonStackPane.setAlignment(Pos.BOTTOM_CENTER); // doesn't work

        StackPane startStackPane = new StackPane(startCanvas, startBorderPane, buttonStackPane);


        // scenes
        Scene mainScene = new Scene(mainStackPane, width, height);
        Scene startScene = new Scene(startStackPane, width, height);


        // stages and main window settings
        mainStage.setTitle("Battle Crawler 2"); // title of the stage/window
        mainStage.setScene(mainScene);
        mainStage.setResizable(false); // not resizable, the size will be that of the scene
        mainStage.centerOnScreen();


        // labels
        Label mainLabel = new Label("Battle Crawler 2");
        mainLabel.setFont(bigFont);
        mainLabel.setTextFill(Color.RED);
        mainLabel.setMinSize(0, 75);

        Label winLabel = new Label();
        winLabel.setAlignment(Pos.CENTER);
        winLabel.setFont(bigFont);
        winLabel.setVisible(false);


        // buttons
        Button exitB = new Button("Exit");
        exitB.setMinSize(60, 35);
        exitB.setOnMouseClicked(e -> mainStage.close());

        Button surrenderBStart = new Button("Surrender");
        surrenderBStart.setMinSize(width - 20, 84);
        surrenderBStart.setOnMouseClicked(e -> {
            if (surrenderBStart.getText().equals("Surrender"))
            {
                game.lose();
            }
            mainStage.setScene(mainScene);
        }); // surrenders

        Button attackBStart = new Button("Attack");
        attackBStart.setMinSize(width - 20, 84);
        attackBStart.setOnMouseClicked(e -> {
            byte ok = game.nextTurn();
            if (ok == -1)
            {
                winLabel.setText("LOST\nPress\nback");
                winLabel.setTextFill(Color.RED);
                winLabel.setVisible(true);
                surrenderBStart.setText("BACK");
            }
            else if (ok == 1)
            {
                winLabel.setText("WON\nPress\nback");
                winLabel.setTextFill(Color.GREEN);
                winLabel.setVisible(true);
                surrenderBStart.setText("BACK");
            }
        }); // attacks the enemy and updates text with health

        Button startB = new Button("Start");
        startB.setMinSize(60, 35);
        startB.setOnMouseClicked(e -> {
            mainStage.setScene(startScene);
            winLabel.setVisible(false);
            game.start();
            game.play();
            surrenderBStart.setText("Surrender");
        }); // takes you to the start scene


        // setting the panes by adding the stuff I want in it; i = column, i1 = row
        mainGridPane.add(mainLabel, 0, 0);
        GridPane.setHalignment(mainLabel, HPos.CENTER);
        mainGridPane.add(startB, 0, 1);
        GridPane.setHalignment(startB, HPos.CENTER);
        mainGridPane.add(exitB, 0, 2);
        GridPane.setHalignment(exitB, HPos.CENTER);

        buttonGridPane.add(attackBStart, 0, 0);
        GridPane.setHalignment(attackBStart, HPos.CENTER);
        buttonGridPane.add(surrenderBStart, 0, 1);
        GridPane.setHalignment(surrenderBStart, HPos.CENTER);
//        GridPane.setColumnSpan(surrenderBStart, 2);

        startBorderPane.setBottom(buttonHolderCanvas);
        startBorderPane.setLeft(heroImage);
        BorderPane.setAlignment(heroImage, Pos.BOTTOM_CENTER);
        startBorderPane.setRight(goblinImage);
        BorderPane.setAlignment(goblinImage, Pos.TOP_CENTER);
        startBorderPane.setCenter(winLabel);
        BorderPane.setAlignment(winLabel, Pos.CENTER);


        // showing after everything is ready
        mainStage.show();
    }
}