package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{


        JSONCaller caller = new JSONCaller();

        String url = "https://api.tfl.gov.uk/";
        String query = "Line/jubilee/Arrivals/";
        String token = "app_id=212ba0c5&app_key=4a01c4c660512891246416d28da92511";

        query = "line/mode/tube/status";
        token = "";

        String json = caller.data(url,query,token);

        GridPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Label textBox = new Label();
        textBox.setText(json);
        textBox.wrapTextProperty().set(true);

        root.getChildren().add(textBox);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
