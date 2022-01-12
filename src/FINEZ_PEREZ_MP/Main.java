package FINEZ_PEREZ_MP;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stage;
    public static Board gameBoard;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent menuRoot = loader.load();
        Scene menu = new Scene(menuRoot);

        primaryStage.setTitle("That's Life");
        primaryStage.setScene(menu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
