package alquiler.controller;

import alquiler.app.Main;
import alquiler.model.AlquilaFacil;
import alquiler.model.Vehiculo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioController {


    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();


    public void mostrarVentanaRV(){

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/RegistrarVehiculo.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Mi agencia");
            stage.show();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public void  mostrarVentanaA(){

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AlquilerVehiculos.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Mi agencia");
            stage.show();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

}
