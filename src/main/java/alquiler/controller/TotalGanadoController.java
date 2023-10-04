package alquiler.controller;

import alquiler.model.AlquilaFacil;
import alquiler.model.Alquiler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class TotalGanadoController {
    @FXML
    private Button btnContinuar;
    @FXML
    private DatePicker fInicial;
    @FXML
    private DatePicker fFinal;
    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();
    public void mostrarTotal(){
        double presio = alquilaFacil.ganadoRangoFechas(fInicial.getValue().atStartOfDay(), fFinal.getValue().atStartOfDay());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("El valor recaudado entre estas fechas es: "+presio);
        alert.show();
    }
}
