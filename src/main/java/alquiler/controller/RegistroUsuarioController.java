package alquiler.controller;

import alquiler.exceptions.CampoVacioExcepcion;
import alquiler.exceptions.ObjetoRepetidoException;
import alquiler.model.AlquilaFacil;
import alquiler.model.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroUsuarioController implements Initializable {

    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();
    private static final Logger LOGGER = Logger.getLogger(AlquilaFacil.class.getName());

    private Propiedades propiedades = Propiedades.getInstance();
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
    @FXML
    private Button btnRegistrar1;
    @FXML
    private Label cedulaLbl;
    @FXML
    private Label ingDatosLbl;
    @FXML
    private Label nombreLbl;
    @FXML
    private Label apellidosLbl;
    @FXML
    private Label telefonoLbl;
    @FXML
    private Label correoLbl;
    @FXML
    private Label ciudadLbl;
    @FXML
    private Label direccionLbl;
    @FXML
    private Label regiClienteLbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        regiClienteLbl.setText(propiedades.getBundle().getString("regiClienteLbl"));
        ingDatosLbl.setText(propiedades.getBundle().getString("ingDatosLbl"));
        cedulaLbl.setText(propiedades.getBundle().getString("cedulaLbl"));
        nombreLbl.setText(propiedades.getBundle().getString("nombreLbl"));
        apellidosLbl.setText(propiedades.getBundle().getString("apellidosLbl"));
        telefonoLbl.setText(propiedades.getBundle().getString("telefonoLbl"));
        correoLbl.setText(propiedades.getBundle().getString("correoLbl"));
        ciudadLbl.setText(propiedades.getBundle().getString("ciudadLbl"));
        direccionLbl.setText(propiedades.getBundle().getString("direccionLbl"));
        btnRegistrar1.setText(propiedades.getBundle().getString("btnRegistrar1"));

    }
    public void registrarUsuario(){

        try {
            Cliente cliente = new Cliente(fieldCedula.getText(), fieldNombre.getText(), fieldApellidos.getText()
                    , Integer.parseInt(fieldTelefono.getText()),fieldEmail.getText(),
                    fieldCiudad.getText(), fieldDireccion.getText());
            cliente.setCedula( fieldCedula.getText());

            alquilaFacil.registrarUsuario(cliente);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Usuario registrado exitosamente");
            alert.show();

        } catch (CampoVacioExcepcion | ObjetoRepetidoException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }

}
