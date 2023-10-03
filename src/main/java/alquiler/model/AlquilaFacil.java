package alquiler.model;
import java.time.Duration;

import alquiler.exceptions.CampoVacioExcepcion;
import alquiler.exceptions.FechaInvalidaException;
import alquiler.exceptions.NoDisponibleException;
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

    //Variable que tendrá la instancia de esta clase
    private static AlquilaFacil alquilaFacil;

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
    public static AlquilaFacil getInstance(){
        if(alquilaFacil == null){
            alquilaFacil = new AlquilaFacil();
        }

        return alquilaFacil;
    }

    public void registrarUsuario(Cliente cliente) throws CampoVacioExcepcion, ObjetoRepetidoException {

        if (cliente.getCedula() == null || cliente.getCedula().isBlank()) {
            LOGGER.log(Level.WARNING, "La cédula es obligatoria");
            throw new CampoVacioExcepcion("La cédula es obligatoria");
        }
        if (existeCliente(cliente)) {
            LOGGER.log(Level.SEVERE, "La persona que ingreso ya se encuestra registrada");
            throw new ObjetoRepetidoException("La persona que ingreso ya se encuestra registrada");
        }
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            LOGGER.log(Level.WARNING, "El nombre es obligatorio");
            throw new CampoVacioExcepcion("El nombre es obligatorio");
        }
        if (cliente.getApellidos() == null || cliente.getApellidos().isBlank()) {
            LOGGER.log(Level.WARNING, "El apellido es obligatorio");
            throw new CampoVacioExcepcion("El apellido es obligatorio");
        }
        if (cliente.getTelefono()<0) {
            LOGGER.log(Level.WARNING, "El teléfono es obligatorio");
            throw new CampoVacioExcepcion("El teléfono es obligatorio");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            LOGGER.log(Level.WARNING, "El correo eletrónico es obligatorio");
            throw new CampoVacioExcepcion("El correo eletrónico es obligatorio");
        }
        if (cliente.getCiudad() == null || cliente.getCiudad().isBlank()) {
            LOGGER.log(Level.WARNING, "La ciudad es obligatoria");
            throw new CampoVacioExcepcion("La ciudad es obligatoria");
        }
        if (cliente.getDireccion() == null || cliente.getDireccion().isBlank()) {
            LOGGER.log(Level.WARNING, "El teléfono es obligatorio");
            throw new CampoVacioExcepcion("El teléfono es obligatorio");
        }

        clientes.add(cliente);
        LOGGER.log(Level.INFO, "Cliente registrado exitosamente");
    }

    public void registrarVehiculo(Vehiculo vehiculo) throws CampoVacioExcepcion, ObjetoRepetidoException {
        if (vehiculo.getPlaca() == null || vehiculo.getPlaca().isBlank()) {
            LOGGER.log(Level.WARNING, "La placa es obligatoria");
            throw new CampoVacioExcepcion("La placa es obligatoria");
        }
        if (vehiculo.getMarca() == null || vehiculo.getMarca().isBlank()) {
            LOGGER.log(Level.WARNING, "La marca es obligatoria");
            throw new CampoVacioExcepcion("La marca es obligatoria");
        }
        if (vehiculo.getNombre() == null || vehiculo.getNombre().isBlank()) {
            LOGGER.log(Level.WARNING, "El nombre es obligatorio");
            throw new CampoVacioExcepcion("El nombre es obligatorio");
        }
        if (existeVehiculo(vehiculo)) {
            LOGGER.log(Level.SEVERE, "El vehiculo que ingresó ya se encuentra registrado");
            throw new ObjetoRepetidoException("Valor ya registrado");
        }
        if (vehiculo.getModelo()<1800) {
            LOGGER.log(Level.WARNING, "El modelo es obligatoria");
            throw new CampoVacioExcepcion("Campo obligatorio");
        }
        if (vehiculo.getKilometraje()<0) {
            LOGGER.log(Level.WARNING, "El kilometraje es obligatoria");
            throw new CampoVacioExcepcion("Campo obligatorio");
        }
        if (vehiculo.getPrecioPorDia()<10000) {
            LOGGER.log(Level.WARNING, "El precio por día es obligatoria");
            throw new CampoVacioExcepcion("Campo obligatorio");
        }
        if (vehiculo.getNumSillas()<=0) {
            LOGGER.log(Level.WARNING, "El número de sillas es obligatorio");
            throw new CampoVacioExcepcion("Campo obligatorio");
        }
        if (vehiculo.getFoto()==null || vehiculo.getFoto().isBlank()) {
            LOGGER.log(Level.WARNING, "El URL de la foto es obligatorio");
            throw new CampoVacioExcepcion("El URL de la foto es obligatorio");
        }

        vehiculos.add(vehiculo);
        LOGGER.log(Level.INFO, "Vehículo registrado exitosamente");
    }

    public void registrarAlquiler(Alquiler alquiler) throws NoDisponibleException, FechaInvalidaException, CampoVacioExcepcion {
        if(comprobarDisponibilidad(alquiler.getFechaAlquiler(), alquiler.getFechaRegreso(), alquiler.getVehiculo().getPlaca())){
            LOGGER.log(Level.WARNING, "Vehículo no disponible");
            throw new NoDisponibleException("Vehículo no disponible");
        }if(alquiler.getFechaAlquiler().isAfter(alquiler.getFechaRegreso())){
            LOGGER.log(Level.WARNING, "Fecha dada de manera incorrecta");
            throw new FechaInvalidaException("Fecha dada de manera incorrecta");
        }if(alquiler.getFechaAlquiler()==null) {
            LOGGER.log(Level.WARNING, "La fecha está vacia");
            throw new CampoVacioExcepcion("Fecha dada de manera incorrecta");
        }if(alquiler.getFechaRegreso()==null){
            LOGGER.log(Level.WARNING, "La fecha está vacia");
            throw new CampoVacioExcepcion("Fecha dada de manera incorrecta");
        }if(alquiler.getCliente()==null){
            LOGGER.log(Level.WARNING, "No se ingresó la cédula del cliente");
            throw new CampoVacioExcepcion("Fecha dada de manera incorrecta");
        }

        alquileres.add(alquiler);
        LOGGER.log(Level.INFO, "Alquiler registrado exitosamente");

    }

    public boolean comprobarDisponibilidad(LocalDateTime fechaInicio, LocalDateTime fechaFinal, String placa) {
        boolean disponible= true;
        for(Alquiler alquiler: alquileres){
            if((fechaInicio.isEqual(alquiler.getFechaAlquiler()) || fechaInicio.isAfter(alquiler.getFechaAlquiler())) && (fechaFinal.isEqual(alquiler.getFechaRegreso())|| fechaFinal.isBefore(alquiler.getFechaRegreso())) ){
                disponible= false;
            }
        }
        return disponible;
    }

    public boolean existeCliente(Cliente cliente) {
        boolean flag = false;
        for (int i = 0; i < clientes.size() && !flag; i++) {
            if (clientes.get(i).equals(cliente)) {
                flag = true;
            }
        }
        return false;
    }

    public Cliente encontrarCliente(String cedula) {
        Cliente cliente = new Cliente();
        boolean flag= false;
        for (int i = 0; i < clientes.size() && !flag; i++) {
            if (clientes.get(i).getCedula().equals(cedula)) {
                cliente=clientes.get(i);
                flag=true;
            }
        }
        return cliente;
    }

    public boolean existeVehiculo(Vehiculo vehiculo) {
        boolean flag = false;
        for (int i = 0; i < vehiculos.size() && !flag; i++) {
            if (vehiculos.get(i).equals(vehiculo)) {
                flag = true;
            }
        }
        return false;
    }

    public ArrayList<Vehiculo> encontrarVehiculosDisponibles(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        ArrayList<Vehiculo> vehDisponibles = new ArrayList<>();
        for (Alquiler alquiler : alquileres) {
            if (alquiler.getFechaAlquiler().equals(fechaInicio) && alquiler.getFechaAlquiler().equals(fechaFin)
                    && alquiler.getFechaAlquiler().isAfter(fechaInicio) && alquiler.getFechaRegreso().isBefore(fechaFin)) {

                vehDisponibles.add(alquiler.getVehiculo());
            }
        }
        vehDisponibles.sort(Comparator.comparing(Vehiculo::getPrecioPorDia));
        return vehDisponibles;
    }

    public ArrayList<Vehiculo> alquiladosDiaEspec(LocalDateTime fecha) {
        ArrayList<Vehiculo> alquiladosDia = new ArrayList<>();
        for (Alquiler alquiler : alquileres) {
            if (alquiler.getFechaAlquiler().isEqual(fecha)) {
                alquiladosDia.add(alquiler.getVehiculo());
            }
        }
        return alquiladosDia;
    }

    public double ganadoRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        double presio = 0;
        for (Alquiler alquiler : alquileres) {
            if ((alquiler.getFechaAlquiler().isEqual(fechaInicio) || alquiler.getFechaAlquiler().isBefore(fechaInicio)) && (alquiler.getFechaRegreso().isEqual(fechaFin) || alquiler.getFechaRegreso().isBefore(fechaFin))) {
                presio += alquiler.getValorTotal();
            }
        }
        return presio;
    }

    public int cuantasVecesSeRepite(String marca) {
        int cuantasVeces = 0;
        for (Alquiler alquiler : alquileres) {
            if (alquiler.getVehiculo().getMarca().equals(marca)) {
                cuantasVeces++;
            }
        }
        return cuantasVeces;
    }

    public String obtenerMarcaMasAlquilada() {
        String marcaMasAlquilada = "";
        for (int i = 0; i < alquileres.size(); i++) {

        }
        return marcaMasAlquilada;
    }

    public double calcularTotalAlquiler(LocalDateTime fInicio, LocalDateTime fFin, Vehiculo v){
        double total=0;
        Duration duracion = Duration.between(fInicio, fFin);
        long dias= duracion.toDays();
        total= dias*v.getPrecioPorDia();
        return total;
    }


}
