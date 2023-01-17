package com.example.battlecrawler2;

import com.example.battlecrawler2.gamecontents.Game;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application
{
    private final int width = 800, height = 650; // width is v and height is v1
    private static Game game;

    public static void main(String[] args)
    {
        if (args.length > 1)
            System.out.println("Wrong number of arguments, game only accepts one boolean (0/1) argument.\nPlease try again and thank you for playing!");
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
        // basic font
        Font font = new Font("Arial", 20);

        // canvases
        Canvas mainCanvas = new Canvas(width, height);
        mainCanvas.getGraphicsContext2D().setFill(Color.ORANGE);
        mainCanvas.getGraphicsContext2D().fillRect(1, -1, width, height); // v and v1 in this case are the starting points

        // the grid pane and its settings
        GridPane mainGridPane = new GridPane(), startGridPane = new GridPane();
        mainGridPane.setVgap(50); // minimum vertical gap between anything put in the grid
        mainGridPane.setHgap(20); // minimum horizontal gap between anything put in the grid
        mainGridPane.setAlignment(Pos.TOP_CENTER); // align the whole grid on the center

        startGridPane.setVgap(50); // minimum vertical gap between anything put in the grid
        startGridPane.setHgap(20); // minimum horizontal gap between anything put in the grid
        startGridPane.setAlignment(Pos.TOP_CENTER); // align the whole grid on the center

        // the stack panes, which goes on the scene
        StackPane mainStackPane = new StackPane(mainCanvas, mainGridPane);
        StackPane startStackPane = new StackPane(mainCanvas, startGridPane);

        // scenes
        Scene mainScene = new Scene(mainStackPane, width, height);
        Scene loginScene = new Scene(startStackPane, width, height);

        // stages and main window settings
        mainStage.setTitle("Battle Crawler 2"); // title of the stage/window
        mainStage.setScene(mainScene);
        mainStage.setResizable(false); // not resizable, the size will be that of the scene
        mainStage.centerOnScreen();

        // "main" part of the program
        // labels
        Label mainLabel = new Label("Battle Crawler 2");
        mainLabel.setFont(font);
        mainLabel.setMinSize(0, 75);

        // buttons
        Button startB = new Button("Start"), backB = new Button("Back");
        startB.setOnMouseClicked(e -> mainStage.setScene(loginScene)); // takes you to the login screen
        backB.setOnMouseClicked(e -> mainStage.setScene(mainScene)); // takes you to the main page

        // setting the GridPanes by adding the stuff I want in it: i = column, i1 = row
        mainGridPane.add(mainLabel, 0, 0);
        mainGridPane.add(startB, 0, 1);

        startGridPane.add(mainLabel, 0, 0);
        startGridPane.add(backB, 0, 1);

        // showing and all that
        mainStage.show();
    }
}