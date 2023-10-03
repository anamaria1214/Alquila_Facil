package alquiler.controller;

import alquiler.exceptions.CampoVacioExcepcion;
import alquiler.exceptions.ObjetoRepetidoException;
import alquiler.model.AlquilaFacil;
import alquiler.model.Vehiculo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrarVehiculoController {
    private static final Logger LOGGER = Logger.getLogger(AlquilaFacil.class.getName());

    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();
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
    @FXML
    private CheckBox opSi;
    @FXML
    private CheckBox opNo;
    @FXML
    private TextField numSillas;
    @FXML
    private TextField foto;

    public void registrarVehiculo(){
        Vehiculo vehiculo;

        try {
            if(opSi.isSelected()){
                 vehiculo= new Vehiculo(placa.getText(), nombreVehiculo.getText(),marca.getText(),
                        Integer.valueOf(modelo.getText()),foto.getText(),Integer.valueOf(kilometraje.getText()),
                        Double.parseDouble(precioDia.getText()), true,Integer.valueOf(numSillas.getText()), true);
            }else{
                 vehiculo= new Vehiculo(placa.getText(), nombreVehiculo.getText(),marca.getText(),
                        Integer.valueOf(modelo.getText()),foto.getText(),Integer.valueOf(kilometraje.getText()),
                        Double.parseDouble(precioDia.getText()), false,Integer.valueOf(numSillas.getText()), true);
            }

            alquilaFacil.registrarVehiculo(vehiculo);

        } catch (ObjetoRepetidoException | CampoVacioExcepcion e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();

        }catch (NumberFormatException ne){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Los campos son obligatorios y el modelo, kilometraje, precio por día y némero de sillas debe ser numérico");
            alert.setHeaderText(null);
            alert.show();
        }


    }

}
