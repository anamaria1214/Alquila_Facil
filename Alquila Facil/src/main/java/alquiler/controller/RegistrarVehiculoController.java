package alquiler.controller;

import alquiler.model.AlquilaFacil;
import alquiler.model.Vehiculo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegistrarVehiculoController {

    @FXML
    private AlquilaFacil alquilaFacil;
    @FXML
    private TextField placa;
    @FXML
    private TextField nombreVehiculo;
    @FXML
    private TextField marca;
    @FXML
    private TextField modelo;
    @FXML
    private TextField kilometraje;
    @FXML
    private TextField precioDia;
    // Como usar el es automatico? private String
    @FXML
    private TextField numSillas;

    public void registrarVehiculo(){
        Vehiculo vehiculo= new Vehiculo(placa.getText(), nombreVehiculo.getText(),marca.getText(),
                Integer.valueOf(modelo.getText()),null,Integer.valueOf(kilometraje.getText()),
                Double.parseDouble(precioDia.getText()), false,Integer.valueOf(numSillas.getText()), true);
        alquilaFacil.registrarVehiculo(vehiculo);
    }

}
