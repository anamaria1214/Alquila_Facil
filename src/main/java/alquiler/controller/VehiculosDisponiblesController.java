package alquiler.controller;

import alquiler.app.Main;
import alquiler.exceptions.CampoVacioExcepcion;
import alquiler.model.AlquilaFacil;
import alquiler.model.Vehiculo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VehiculosDisponiblesController implements Initializable {

    @FXML
    private DatePicker fInicial;
    @FXML
    private DatePicker fRegreso;
    @FXML
    private Button btnContinuar;
    @FXML
    private TableView<Vehiculo> vehDisponibles;
    @FXML
    private TableColumn placaD;
    @FXML
    private TableColumn marcaD;
    @FXML
    private TableColumn modeloD;
    @FXML
    private TableColumn precioDiaD;

    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        placaD.setCellValueFactory( new PropertyValueFactory<>("placa"));
        marcaD.setCellValueFactory( new PropertyValueFactory<>("marca"));
        modeloD.setCellValueFactory( new PropertyValueFactory<>("modelo"));
        precioDiaD.setCellValueFactory( new PropertyValueFactory<>("precioPorDia"));

    }

    public void abrirTablaVehiculosDisponibles(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/TablaVehiculoDisponibles.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            TablaVehiculoDisponiblesController controller1 =loader.getController();
            TablaVehiculoDisponiblesController controller2 =loader.getController();
            //controller1.recibirFecha(fInicio.getValue().atStartOfDay());
            //controller2.recibirFecha(fRegreso.getValue().atStartOfDay());
            stage.setTitle("Alquila fácil");
            stage.show();

        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public void consultarVehiculos() throws CampoVacioExcepcion {

        if(! (fInicial.getValue()==null || fRegreso.getValue()==null) ) {

            ArrayList<Vehiculo> filtrados = alquilaFacil.encontrarVehiculosDisponibles(
                    fInicial.getValue().atStartOfDay(),
                    fRegreso.getValue().atStartOfDay()
            );
            vehDisponibles.setItems( FXCollections.observableArrayList(filtrados) );
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Las fechas son obligatorias");
            alert.show();
        }



    }

}
