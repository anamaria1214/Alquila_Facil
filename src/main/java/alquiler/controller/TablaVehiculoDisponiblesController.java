package alquiler.controller;

import alquiler.model.AlquilaFacil;
import alquiler.model.Vehiculo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class TablaVehiculoDisponiblesController implements Initializable {

    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        placaD.setCellValueFactory( new PropertyValueFactory<>("placa"));
        marcaD.setCellValueFactory( new PropertyValueFactory<>("marca"));
        modeloD.setCellValueFactory( new PropertyValueFactory<>("modelo"));
        precioDiaD.setCellValueFactory( new PropertyValueFactory<>("precioPorDia"));

        vehDisponibles.setItems( FXCollections.observableArrayList(alquilaFacil.getVehiculos()) );
    }


}
