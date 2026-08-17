package org.example.javafx.panes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Simple JavaFX Panes Demo
 * 
 * JavaFX Panes vs Swing Layouts:
 * - BorderPane = BorderLayout (North, South, East, West, Center)
 * - FlowPane = FlowLayout (left to right, wraps to next row)
 * - HBox/VBox = BoxLayout (horizontal/vertical arrangement)
 * - GridPane = GridLayout (rows and columns grid)
 * - StackPane = No direct equivalent (stacks components on top)
 */
public class Panes extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Open all panes in separate windows
        showBorderPane();
        showFlowPane();
        showHBox();
        showVBox();
        showGridPane();
        showStackPane();
        
        primaryStage.setTitle("All Panes Demo");
        primaryStage.setScene(new Scene(new Label("All pane windows opened!"), 300, 100));
        primaryStage.show();
    }

    // 1. BorderPane = BorderLayout
    private void showBorderPane() {
        BorderPane pane = new BorderPane();
        pane.setTop(new Button("Top"));
        pane.setBottom(new Button("Bottom"));
        pane.setLeft(new Button("Left"));
        pane.setRight(new Button("Right"));
        pane.setCenter(new Button("Center"));
        
        Stage stage = new Stage();
        stage.setTitle("1. BorderPane (= BorderLayout)");
        stage.setScene(new Scene(pane, 400, 300));
        stage.show();
    }

    // 2. FlowPane = FlowLayout
    private void showFlowPane() {
        FlowPane pane = new FlowPane();
        for (int i = 1; i <= 10; i++) {
            pane.getChildren().add(new Button("Btn " + i));
        }
        
        Stage stage = new Stage();
        stage.setTitle("2. FlowPane (= FlowLayout)");
        stage.setScene(new Scene(pane, 400, 300));
        stage.show();
    }

    // 3. HBox = Horizontal BoxLayout
    private void showHBox() {
        HBox pane = new HBox();
        pane.getChildren().add(new Button("Button 1"));
        pane.getChildren().add(new Button("Button 2"));
        pane.getChildren().add(new Button("Button 3"));
        
        Stage stage = new Stage();
        stage.setTitle("3. HBox (Horizontal)");
        stage.setScene(new Scene(pane, 400, 300));
        stage.show();
    }

    // 4. VBox = Vertical BoxLayout
    private void showVBox() {
        VBox pane = new VBox();
        pane.getChildren().add(new Button("Button 1"));
        pane.getChildren().add(new Button("Button 2"));
        pane.getChildren().add(new Button("Button 3"));
        
        Stage stage = new Stage();
        stage.setTitle("4. VBox (Vertical)");
        stage.setScene(new Scene(pane, 400, 300));
        stage.show();
    }

    // 5. GridPane = GridLayout
    private void showGridPane() {
        GridPane pane = new GridPane();
        pane.add(new Label("Name:"), 0, 0);
        pane.add(new Button("Submit"), 1, 0);
        pane.add(new Label("Email:"), 0, 1);
        pane.add(new Button("Send"), 1, 1);
        
        Stage stage = new Stage();
        stage.setTitle("5. GridPane (= GridLayout)");
        stage.setScene(new Scene(pane, 400, 300));
        stage.show();
    }

    // 6. StackPane - stacks on top
    private void showStackPane() {
        StackPane pane = new StackPane();
        pane.getChildren().add(new Button("Bottom Button"));
        pane.getChildren().add(new Label("Top Label"));
        
        Stage stage = new Stage();
        stage.setTitle("6. StackPane (Stacks)");
        stage.setScene(new Scene(pane, 400, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
