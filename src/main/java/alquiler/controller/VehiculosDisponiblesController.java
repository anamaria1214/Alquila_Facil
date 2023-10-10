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
    private TableView<Vehiculo> vehDisponibles;
    @FXML
    private TableColumn placaD;
    @FXML
    private TableColumn marcaD;
    @FXML
    private TableColumn modeloD;
    @FXML
    private TableColumn precioDiaD;
    @FXML
    private Label vehDisponiblesLbl;
    @FXML
    private Label rangoComprobarLbl;
    @FXML
    private Label fechaInicialLbl;
    @FXML
    private Label fechaFinalLbl;
    @FXML
    private Button btnConsultar;

    private Propiedades propiedades = Propiedades.getInstance();
    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehDisponiblesLbl.setText(propiedades.getBundle().getString("vehDisponiblesLbl"));
        rangoComprobarLbl.setText(propiedades.getBundle().getString("rangoComprobarLbl"));
        fechaInicialLbl.setText(propiedades.getBundle().getString("fechaInicialLbl"));
        fechaFinalLbl.setText(propiedades.getBundle().getString("fechaFinalLbl"));
        btnConsultar.setText(propiedades.getBundle().getString("btnConsultar"));
        placaD.setText( propiedades.getBundle().getString("placaD"));
        marcaD.setText( propiedades.getBundle().getString("marcaD"));
        modeloD.setText( propiedades.getBundle().getString("modeloD"));
        precioDiaD.setText( propiedades.getBundle().getString("precioDiaD"));
        placaD.setCellValueFactory( new PropertyValueFactory<>("placa"));
        marcaD.setCellValueFactory( new PropertyValueFactory<>("marca"));
        modeloD.setCellValueFactory( new PropertyValueFactory<>("modelo"));
        precioDiaD.setCellValueFactory( new PropertyValueFactory<>("precioPorDia"));
        /*this.placaD.setCellValueFactory( new PropertyValueFactory<>(propiedades.getBundle().getString("placaD")));
        this.marcaD.setCellValueFactory( new PropertyValueFactory<>(propiedades.getBundle().getString("marcaD")));
        this.modeloD.setCellValueFactory( new PropertyValueFactory<>(propiedades.getBundle().getString("modeloD")));
        this.precioDiaD.setCellValueFactory( new PropertyValueFactory<>(propiedades.getBundle().getString("precioDiaD")));*/

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

    public void consultarVehiculos(){

        if(! (fInicial.getValue()==null || fRegreso.getValue()==null) ) {

            ArrayList<Vehiculo> filtrados = alquilaFacil.encontrarVehiculosDisponibles(
                    fInicial.getValue().atStartOfDay(),
                    fRegreso.getValue().atStartOfDay()
            );
            vehDisponibles.setItems( FXCollections.observableArrayList(filtrados));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Las fechas son obligatorias");
            alert.show();
        }



    }

}
