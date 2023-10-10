package alquiler.controller;

import alquiler.model.AlquilaFacil;
import alquiler.model.Vehiculo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VehiculosAlquiladosController implements Initializable {

    @FXML
    private TableView<Vehiculo> vehAlquilados;
    @FXML
    private TableColumn placaD;
    @FXML
    private TableColumn marca;
    @FXML
    private TableColumn modelo;
    @FXML
    private TableColumn precioPorDia;
    @FXML
    private DatePicker fecha;
    @FXML
    private Button btnContinuar;
    @FXML
    private Label lblAlquilados;
    @FXML
    private Label lblDescripcion;
    @FXML
    private Label lblFecha;

    private Propiedades propiedades = Propiedades.getInstance();
    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        placaD.setText(propiedades.getBundle().getString("placaD"));
        marca.setText( propiedades.getBundle().getString("marcaD"));
        modelo.setText( propiedades.getBundle().getString("modeloD"));
        precioPorDia.setText( propiedades.getBundle().getString("precioDiaD"));
        btnContinuar.setText(propiedades.getBundle().getString("btnContinuar"));
        lblAlquilados.setText(propiedades.getBundle().getString("lblAlquilados"));
        lblDescripcion.setText(propiedades.getBundle().getString("lblDescripcion"));
        lblFecha.setText(propiedades.getBundle().getString("lblFecha"));

        placaD.setCellValueFactory( new PropertyValueFactory<>("placa"));
        marca.setCellValueFactory( new PropertyValueFactory<>("marca"));
        modelo.setCellValueFactory( new PropertyValueFactory<>("modelo"));
        precioPorDia.setCellValueFactory( new PropertyValueFactory<>("precioPorDia"));



    }

    public void consultarVehiculos() {
        if (fecha.getValue() != null) {
            ArrayList<Vehiculo> filtrados = alquilaFacil.encontrarVehiculosAlquilados(
                    fecha.getValue().atStartOfDay());
        vehAlquilados.setItems(FXCollections.observableArrayList(filtrados));
            //System.out.println(filtrados.toString());
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Las fechas son obligatorias");
            alert.show();
        }
    }

}
