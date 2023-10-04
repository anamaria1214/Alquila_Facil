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

    private AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();
    private Propiedades propiedades = Propiedades.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        columnPlaca.setCellValueFactory( new PropertyValueFactory<>("placa"));
        columnModelo.setCellValueFactory( new PropertyValueFactory<>("marca"));
        columnPrecio.setCellValueFactory( new PropertyValueFactory<>("modelo"));
        columnMarca.setCellValueFactory( new PropertyValueFactory<>("precioPorDia"));

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
            alert.setContentText("Alquiler Exitoso"+"\n"+"Se alquiló el vehiculo con:"+"\n"
                            +"Placa: "+alquiler.getVehiculo().getPlaca()+"\"+" +
                            "Desde el día: "+alquiler.getFechaAlquiler().toString()+"\n"+
                            "Hasta el día: "+alquiler.getFechaRegreso()+"\n"+"Por un valor total de:"
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
