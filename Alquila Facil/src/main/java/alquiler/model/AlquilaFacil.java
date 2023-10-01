package alquiler.model;

import alquiler.exceptions.CampoVacioExcepcion;
import alquiler.exceptions.ObjetoRepetidoException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import lombok.Getter;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;

public class AlquilaFacil {

    @Getter
    private ArrayList<Cliente> clientes = new ArrayList<>();
    @Getter
    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    @Getter
    private ArrayList<Alquiler> alquileres = new ArrayList<>();

    private static final Logger LOGGER = Logger.getLogger(AlquilaFacil.class.getName());

    private AlquilaFacil() {
        try {
            LOGGER.addHandler(new FileHandler("logs.xml", true));
        } catch (IOException e) {
            LOGGER.log(Level.INFO, "Archivo no encontrado");
            LOGGER.log(Level.INFO, "Archivo no encontrado");
        }

        LOGGER.log(Level.INFO, "Se creó una nueva instancia");
        this.alquileres = new ArrayList<>();
        this.clientes = clientes;
        this.vehiculos = vehiculos;
    }

    public void registrarUsuario(Cliente cliente) throws CampoVacioExcepcion, ObjetoRepetidoException {

        if (cliente.getCedula() == null || cliente.getCedula().isBlank()) {
            LOGGER.log(Level.WARNING, "La cédula es obligatoria");
            throw new CampoVacioExcepcion("Campo obligatorio");
        }
        if (existeCliente(cliente)) {
            LOGGER.log(Level.SEVERE, "La");
            throw new ObjetoRepetidoException("Valor ya registrado");
        }


        LOGGER.log(Level.INFO, "Cliente registrado exitosamente");
        clientes.add(cliente);
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    public void registrarAlquiler(Alquiler alquiler) {

    }

    public boolean comprobarDisponibilidad() {
        return false;
    }

    public boolean existeCliente(Cliente cliente) {
        return false;
    }

    public ArrayList<Vehiculo> encontrarVehiculosDisponibles(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        ArrayList<Vehiculo> vehDisponibles= new ArrayList<>();
        for(Alquiler alquiler: alquileres){
           if(alquiler.getFechaRegistro().equals(fechaInicio) && alquiler.getFechaRegistro().equals(fechaFin)
                   && alquiler.getFechaRegistro().isAfter(fechaInicio) && alquiler.getFechaRegreso().isBefore(fechaFin)){

               vehDisponibles.add(alquiler.getVehiculo());
           }


        }


        return vehDisponibles;
    }

    public ArrayList<Vehiculo> ordernarEconomicoCostoso(ArrayList<Vehiculo> vehiculos) {
        ArrayList<Vehiculo> vehiculosEcoToExp= new ArrayList<>();
        Vehiculo aux= vehiculos.get(0);
        for(int i=0;i<vehiculos.size()-1; i++){
            if(vehiculos.get(i).getPrecioPorDia()<vehiculos.get(i+1).getPrecioPorDia()){

            }
        }
        return vehiculosEcoToExp;
    }


}
