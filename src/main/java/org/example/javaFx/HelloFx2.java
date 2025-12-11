package org.example.javaFx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//javafx using fxml
public class HelloFx2 extends Application {

    @Override
    public void start(Stage st) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/main.fxml"));
        Scene sc = new Scene(root);
        st.setScene(sc);
        st.setTitle("FXML Demo");
        st.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
