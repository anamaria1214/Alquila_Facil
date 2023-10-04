package alquiler.controller;

import alquiler.model.AlquilaFacil;
import alquiler.model.Vehiculo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class VehiculosAlquiladosController implements Initializable {

    @FXML
    private TableView<Vehiculo> vehAlquilados;
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

}
