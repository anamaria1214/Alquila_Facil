package alquiler.controller;

import alquiler.exceptions.CampoVacioExcepcion;
import alquiler.exceptions.ObjetoRepetidoException;
import alquiler.model.AlquilaFacil;
import alquiler.model.Vehiculo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrarVehiculoController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(AlquilaFacil.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();
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
    @FXML
    private Label registrarVehiculoLbl;
    @FXML
    private Label ingDatosVehLbl;
    @FXML
    private Label placaLbl;
    @FXML
    private Label nombreVLbl;
    @FXML
    private Label marcaLbl;
    @FXML
    private Label modeloLbl;
    @FXML
    private Label kilomLbl;
    @FXML
    private Label alquilerDiaLbl;
    @FXML
    private Label automLbl;
    @FXML
    private Label sillasLbl;
    @FXML
    private Label fotoLbl;

    @FXML
    private Button btnRegistrarVehiculo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        registrarVehiculoLbl.setText(propiedades.getBundle().getString("registrarVehiculoLbl"));
        ingDatosVehLbl.setText(propiedades.getBundle().getString("ingDatosVehLbl"));
        placaLbl.setText(propiedades.getBundle().getString("placaLbl"));
        nombreVLbl.setText(propiedades.getBundle().getString("nombreVLbl"));
        marcaLbl.setText(propiedades.getBundle().getString("marcaLbl"));
        modeloLbl.setText(propiedades.getBundle().getString("modeloLbl"));
        kilomLbl.setText(propiedades.getBundle().getString("kilomLbl"));
        alquilerDiaLbl.setText(propiedades.getBundle().getString("alquilerDiaLbl"));
        automLbl.setText(propiedades.getBundle().getString("automLbl"));
        opSi.setText(propiedades.getBundle().getString("opSi"));
        opNo.setText(propiedades.getBundle().getString("opNo"));
        sillasLbl.setText(propiedades.getBundle().getString("sillasLbl"));
        fotoLbl.setText(propiedades.getBundle().getString("fotoLbl"));
        btnRegistrarVehiculo.setText(propiedades.getBundle().getString("btnRegistrarVehiculo"));

    }
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
