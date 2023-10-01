package alquiler.controller;

import alquiler.exceptions.CampoVacioExcepcion;
import alquiler.exceptions.ObjetoRepetidoException;
import alquiler.model.AlquilaFacil;
import alquiler.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegistroUsuarioController {

    private AlquilaFacil alquilaFacil;

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

        Cliente cliente = new Cliente(fieldCedula.getText(), fieldNombre.getText(), fieldApellidos.getText()
        ,Integer.parseInt(fieldTelefono.getText()),fieldEmail.getText(),
                fieldCiudad.getText(), fieldDireccion.getText());
        cliente.setCedula( fieldCedula.getText());

        try {
            alquilaFacil.registrarUsuario(cliente);
        } catch (CampoVacioExcepcion | ObjetoRepetidoException e) {
            throw new RuntimeException(e);
        }
    }

}
