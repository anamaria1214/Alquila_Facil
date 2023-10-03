package alquiler.controller;

import alquiler.app.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuInicioController {

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
    public void abrirMarcaMasAlquilada(){

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


}
