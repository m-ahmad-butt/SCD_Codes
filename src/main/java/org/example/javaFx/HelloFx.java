package org.example.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//javafx without fxml
public class HelloFx extends Application {

    @Override
    public void start(Stage st) {
        Label l = new Label("Count: 0");
        Button b = new Button("Click Me");

        final int[] count = {0};
        b.setOnAction(e -> {
            count[0]++;
            l.setText("Count: " + count[0]);
        });

        BorderPane p = new BorderPane();
        p.setTop(l);
        p.setCenter(b);

        Scene sc = new Scene(p, 400, 300);
        st.setScene(sc);
        st.setTitle("Demo");
        st.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
