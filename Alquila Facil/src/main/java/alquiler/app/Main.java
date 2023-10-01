package alquiler.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader( Main.class.getResource("/RegistrarVehiculo.fxml") );
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Mi agencia");
        stage.show();

    }

    public static void main(String[] args) {
        launch( Main.class, args );
    }

}
