package alquiler.model;

import alquiler.exceptions.CampoVacioExcepcion;
import alquiler.exceptions.ObjetoRepetidoException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
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
            LOGGER.log(Level.SEVERE, "La persona que ingreso ya se encuestra registrada");
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
           if(alquiler.getFechaAlquiler().equals(fechaInicio) && alquiler.getFechaAlquiler().equals(fechaFin)
                   && alquiler.getFechaAlquiler().isAfter(fechaInicio) && alquiler.getFechaRegreso().isBefore(fechaFin)){

               vehDisponibles.add(alquiler.getVehiculo());
           }
        }
        vehDisponibles.sort(Comparator.comparing(Vehiculo::getPrecioPorDia));
        return vehDisponibles;
    }

    public ArrayList<Vehiculo> alquiladosDiaEspec(LocalDateTime fecha){
        ArrayList<Vehiculo> alquiladosDia = new ArrayList<>();
        for(Alquiler alquiler: alquileres){
            if(alquiler.getFechaAlquiler().isEqual(fecha)){
                alquiladosDia.add(alquiler.getVehiculo());
            }
        }
        return alquiladosDia;
    }

    public double ganadoRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        double presio =0;
        for(Alquiler alquiler: alquileres){
            if((alquiler.getFechaAlquiler().isEqual(fechaInicio) || alquiler.getFechaAlquiler().isBefore(fechaInicio)) && (alquiler.getFechaRegreso().isEqual(fechaFin) || alquiler.getFechaRegreso().isBefore(fechaFin))){
                presio += alquiler.getValorTotal();
            }
        }
        return presio;
    }



}
