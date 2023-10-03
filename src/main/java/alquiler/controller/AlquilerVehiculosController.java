package alquiler.controller;

import alquiler.exceptions.FechaInvalidaException;
import alquiler.exceptions.NoDisponibleException;
import alquiler.model.AlquilaFacil;
import alquiler.model.Alquiler;
import alquiler.model.Vehiculo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AlquilerVehiculosController implements Initializable {

    @FXML
    private TableView<Vehiculo> tablaVehiculos;

    @FXML
    private TextField cedulaCliente;
    @FXML
    private TableColumn columnPlaca;
    @FXML
    private TableColumn columnModelo;
    @FXML
    private TableColumn columnPrecio;
    @FXML
    private TableColumn columnMarca;
    @FXML
    private DatePicker fechaInicio1;
    @FXML
    private DatePicker fechaRegreso;

    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        columnPlaca.setCellValueFactory( new PropertyValueFactory<>("placa"));
        columnModelo.setCellValueFactory( new PropertyValueFactory<>("marca"));
        columnPrecio.setCellValueFactory( new PropertyValueFactory<>("modelo"));
        columnMarca.setCellValueFactory( new PropertyValueFactory<>("precioPorDia"));

        tablaVehiculos.setItems( FXCollections.observableArrayList( alquilaFacil.getVehiculos() ) );
    }


    public void registrarAlquiler(){

        Vehiculo seleccionado = tablaVehiculos.getSelectionModel().getSelectedItem();
        Alquiler alquiler= new Alquiler(alquilaFacil.encontrarCliente(cedulaCliente.getText()),seleccionado,
                fechaInicio1.getValue().atStartOfDay(), fechaRegreso.getValue().atStartOfDay(),
                alquilaFacil.calcularTotalAlquiler(fechaInicio1.getValue().atStartOfDay(), fechaRegreso.getValue().atStartOfDay(), seleccionado));

            alquilaFacil.registrarAlquiler(alquiler);
    }


}
