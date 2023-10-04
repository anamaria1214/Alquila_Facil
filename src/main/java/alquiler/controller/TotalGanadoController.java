package alquiler.controller;

import alquiler.model.AlquilaFacil;
import alquiler.model.Alquiler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TotalGanadoController  {
    @FXML
    private Button btnCalcular;
    @FXML
    private DatePicker fInicial;
    @FXML
    private DatePicker fFinal;
    @FXML
    private Label gananciasLbl;
    @FXML
    private Label ingRangoGananciasLbl;
    @FXML
    private Label fInicialG;
    @FXML
    private Label fFinalG;

    private String mensajeG;

    private Propiedades propiedades= Propiedades.getInstance();
    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();
    /*public void initialize(URL url, ResourceBundle resourceBundle){
        btnCalcular.setText(propiedades.getBundle().getString("btnCalcular"));
        gananciasLbl.setText(propiedades.getBundle().getString("gananciasLbl"));
        fInicialG.setText(propiedades.getBundle().getString("fInicialG"));
        fFinalG.setText(propiedades.getBundle().getString("fFinalG"));
        ingRangoGananciasLbl.setText(propiedades.getBundle().getString("registrarVehiculo"));
        mensajeG=propiedades.getBundle().getString("mensajeG");

    }*/
    public void mostrarTotal(){
        double presio = alquilaFacil.ganadoRangoFechas(fInicial.getValue().atStartOfDay(), fFinal.getValue().atStartOfDay());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Total "+presio);
        alert.show();
    }
}
