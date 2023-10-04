package alquiler.controller;

import alquiler.app.Main;
import alquiler.model.AlquilaFacil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuInicioController implements Initializable {

    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();
    private Propiedades propiedades = Propiedades.getInstance();

    @FXML
    private Label opcionesMenu;
    @FXML
    private Label tituloMenu;
    @FXML
    private Button registrarVehiculo;
    @FXML
    private Button registrarCliente;
    @FXML
    private Button alquilarAuto;
    @FXML
    private Button vehiculosDisponibles;
    @FXML
    private Button vehiculosAlquilados;
    @FXML
    private Button totalGanado;
    @FXML
    private Button marcaMasAlquilada;
    @FXML
    private Button cambiarL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String valor = propiedades.getBundle().getString("btnRegistrar");
        registrarVehiculo.setText(propiedades.getBundle().getString("registrarVehiculo"));
        alquilarAuto.setText(propiedades.getBundle().getString("alquilarAuto"));
        vehiculosDisponibles.setText(propiedades.getBundle().getString("vehiculosDisponibles"));
        vehiculosAlquilados.setText(propiedades.getBundle().getString("vehiculosAlquilados"));
        marcaMasAlquilada.setText(propiedades.getBundle().getString("marcaMasAlquilada"));
        totalGanado.setText(propiedades.getBundle().getString("totalGanado"));
        tituloMenu.setText(propiedades.getBundle().getString("tituloMenu"));
        opcionesMenu.setText(propiedades.getBundle().getString("opcionesMenu"));
        cambiarL.setText(propiedades.getBundle().getString("cambiarL"));


        registrarCliente.setText(valor);
    }

    public void abrirRegistrarVehiculo(){


        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/RegistrarVehiculo.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Alquila Fácil");
            stage.show();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public void abrirRegistrarCliente(){

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/RegistrarUsuario.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Alquila Fácil");
            stage.show();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public void abrirAlquilarAuto(){

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AlquilerVehiculos.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Alquila Fácil");
            stage.show();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public void abrirVehiculosDisponibles(){

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/VehiculosDisponibles.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Alquila Fácil");
            stage.show();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    public void abrirVehiculosAlquilados(){

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/VehiculosAlquilados.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Alquila Fácil");
            stage.show();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    public void abrirTotalGanado(){

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/TotalGanado.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Alquila Fácil");
            stage.show();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    public void lanzarMensaje(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("La marca más alquilada es "+alquilaFacil.obtenerMarcaMasAlquilada());
        alert.setHeaderText(null);
        alert.show();

    }

    public void cambiarIdioma(){

        if(propiedades.getIdioma().equals("es")){
            propiedades.escribirIdioma("en");
        }else{
            propiedades.escribirIdioma("es");
        }

        try {
            FXMLLoader loader = new FXMLLoader( Main.class.getResource("/MenuInicio.fxml") );

            Parent parent = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Mi agencia");
            stage.show();

            ((Stage) registrarCliente.getScene().getWindow()).close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void abrirMarcaMasAlquilada(){

    }

}
