package alquiler.controller;

import alquiler.app.Main;
import alquiler.model.AlquilaFacil;
import alquiler.model.Vehiculo;
import alquiler.servicios.Traducible;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioController implements Traducible {
    @FXML
    public Button btnRegistrarA;
    @FXML
    private Button btnRegistrarV;
    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();
    @FXML
    void initialize() {
        Propiedades.getInstance().agregarTraducible(this);
    }



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


    @Override
    public void actualizarIdioma(ResourceBundle bundle) {
        btnRegistrarA.setText(bundle.getString("btnRegistrar"));
    }
}
