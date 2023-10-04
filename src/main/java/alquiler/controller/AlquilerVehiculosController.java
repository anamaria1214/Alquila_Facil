package alquiler.controller;

import alquiler.exceptions.*;
import alquiler.model.AlquilaFacil;
import alquiler.model.Alquiler;
import alquiler.model.Vehiculo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlquilerVehiculosController implements Initializable {

    @FXML
    private TableView<Vehiculo> tablaVehiculos;
    private static final Logger LOGGER = Logger.getLogger(AlquilaFacil.class.getName());

    @FXML
    private TextField cedulaCliente;
    @FXML
    private TableColumn columnPlaca;
    @FXML
    private TableColumn columnModelo;
    @FXML
    private TableColumn columnPrecio;
    @FXML
    private TableColumn columnMarca;
    @FXML
    private DatePicker fechaInicio1;
    @FXML
    private DatePicker fechaRegreso;
    @FXML
    private Label alquilarVehiculoLabel;
    @FXML
    private Label escojaLabel;
    @FXML
    private Label cedulaLabel;
    @FXML
    private Label fInicioLabel;
    @FXML
    private Label fRegresoLabel;
    @FXML
    private Button btnContinuar;
    private String alqExit;
    private String sAlqCon;
    private String placaF;
    private String diaI;
    private String diaF;
    private String totalV;
    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();
    private Propiedades propiedades = Propiedades.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alquilarVehiculoLabel.setText(propiedades.getBundle().getString("alquilarVehiculoLabel"));
        escojaLabel.setText(propiedades.getBundle().getString("escojaLabel"));
        cedulaLabel.setText(propiedades.getBundle().getString("cedulaLabel"));
        fInicioLabel.setText(propiedades.getBundle().getString("fInicioLabel"));
        fRegresoLabel.setText(propiedades.getBundle().getString("fRegresoLabel"));
        btnContinuar.setText(propiedades.getBundle().getString("btnContinuar"));
        alqExit=propiedades.getBundle().getString("alqExit");
        sAlqCon=propiedades.getBundle().getString("sAlqCon");
        placaF=propiedades.getBundle().getString("placaF");
        diaI=propiedades.getBundle().getString("diaI");
        diaF=propiedades.getBundle().getString("diaF");
        totalV=propiedades.getBundle().getString("totalV");
        columnPlaca.setCellValueFactory( new PropertyValueFactory<>("Placa"));
        columnModelo.setCellValueFactory( new PropertyValueFactory<>("Modelo"));
        columnPrecio.setCellValueFactory( new PropertyValueFactory<>("Precio"));
        columnMarca.setCellValueFactory( new PropertyValueFactory<>("Marca"));

        tablaVehiculos.setItems( FXCollections.observableArrayList( alquilaFacil.getVehiculos() ) );
        
    }


    public void registrarAlquiler(){

        try {
            Vehiculo seleccionado = tablaVehiculos.getSelectionModel().getSelectedItem();
            Alquiler alquiler= new Alquiler(alquilaFacil.encontrarCliente(cedulaCliente.getText()),seleccionado,
                    fechaInicio1.getValue().atStartOfDay(), fechaRegreso.getValue().atStartOfDay(),
                    alquilaFacil.calcularTotalAlquiler(fechaInicio1.getValue().atStartOfDay(), fechaRegreso.getValue().atStartOfDay(), seleccionado));

            alquilaFacil.registrarAlquiler(alquiler);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(alqExit+"\n"+sAlqCon+"\n"
                            +placaF+alquiler.getVehiculo().getPlaca()+"\"+" +
                            diaI+alquiler.getFechaAlquiler().toString()+"\n"+
                            diaF+alquiler.getFechaRegreso()+"\n"+totalV
                            +alquiler.getValorTotal()+"$");
            alert.show();

        } catch (CampoVacioExcepcion e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();

        }catch (ObjetoNoExistenteException | NumberFormatException | NoDisponibleException | FechaInvalidaException ne){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ne.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }


}
