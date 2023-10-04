package alquiler.controller;

import alquiler.exceptions.CampoVacioExcepcion;
import alquiler.exceptions.ObjetoRepetidoException;
import alquiler.model.AlquilaFacil;
import alquiler.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroUsuarioController {

    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();
    private static final Logger LOGGER = Logger.getLogger(AlquilaFacil.class.getName());

    @FXML
    private TextField fieldCedula;
    @FXML
    private TextField fieldNombre;
    @FXML
    private TextField fieldApellidos;
    @FXML
    private TextField fieldTelefono;
    @FXML
    private TextField fieldEmail;
    @FXML
    private  TextField fieldCiudad;
    @FXML
    private TextField fieldDireccion;


    public void registrarUsuario(){

        try {
            Cliente cliente = new Cliente(fieldCedula.getText(), fieldNombre.getText(), fieldApellidos.getText()
                    ,Integer.parseInt(fieldTelefono.getText()),fieldEmail.getText(),
                    fieldCiudad.getText(), fieldDireccion.getText());
            cliente.setCedula( fieldCedula.getText());

            alquilaFacil.registrarUsuario(cliente);
        } catch (CampoVacioExcepcion | ObjetoRepetidoException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }

}
