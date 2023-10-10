package alquiler.model;
import java.io.*;
import java.time.Duration;

import alquiler.archivos.ArchivoUtils;
import alquiler.exceptions.*;

import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import lombok.Getter;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class AlquilaFacil {

    @Getter
    private ArrayList<Cliente> clientes;
    @Getter
    private ArrayList<Vehiculo> vehiculos;
    @Getter
    private ArrayList<Alquiler> alquileres;

    //Variable que tendrá la instancia de esta clase
    private static AlquilaFacil alquilaFacil;

    private static final Logger LOGGER = Logger.getLogger(AlquilaFacil.class.getName());

    private AlquilaFacil() {
        try {
            FileHandler fh = new FileHandler("logs.log", true);
            fh.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log(Level.INFO, "Archivo no encontrado");
            LOGGER.log(Level.INFO, "Archivo no encontrado");
        }
        LOGGER.log(Level.INFO, "Se creó una nueva instancia");

        this.clientes =  new ArrayList<>();
        leerClientes();

        this.vehiculos = new ArrayList<>();
        leerVehiculos();

        this.alquileres = new ArrayList<>();
        leerAlquileres();

    }

    private void leerVehiculos() {
        try(Scanner scanner= new Scanner(new File("src/main/resources/persistencia/vehiculos.txt"))){
            while(scanner.hasNextLine()){
                String linea= scanner.nextLine();
                String [] valores= linea.split(";");
                this.vehiculos.add(new Vehiculo(valores[0],valores[1], valores[2],
                        Integer.parseInt((valores[3])), valores[4],
                        Integer.parseInt(valores[5]), Double.parseDouble(valores[6]),
                        Boolean.parseBoolean(valores[7]), Integer.parseInt(valores[8]),
                        Boolean.parseBoolean(valores[9])));
            }
        }catch(IOException e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    private void leerAlquileres() {
        try {
            this.alquileres = (ArrayList<Alquiler>) ArchivoUtils.deserializarObjeto("src/main/resources/persistencia/alq.data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void leerClientes() {
        try(Scanner scanner= new Scanner(new File("src/main/resources/persistencia/clientes.txt"))){
            while(scanner.hasNextLine()){
                String linea= scanner.nextLine();
                String [] valores= linea.split(";");
                this.clientes.add(new Cliente(valores[0],valores[1], valores[2], Integer.parseInt(valores[3]), valores[4], valores[5], valores[6]));
            }
        }catch(IOException e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }
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
        try  {
            FileWriter fw= new FileWriter(new File("src/main/resources/persistencia/clientes.txt"), true);
            Formatter ft= new Formatter(fw);
            ft.format(cliente.getCedula()+";"+cliente.getNombre()+";"+cliente.getApellidos()+";"+cliente.getTelefono()+";"
                    +cliente.getEmail()+";"+cliente.getCiudad()+";"+cliente.getDireccion()+"%n");
            ft.close();
        }
        catch (IOException e){
            LOGGER.log(Level.SEVERE, e.getMessage());
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

        try  {
            FileWriter fw= new FileWriter(new File("src/main/resources/persistencia/vehiculos.txt"), true);
            Formatter ft= new Formatter(fw);
            ft.format(vehiculo.getPlaca()+";"+vehiculo.getNombre()+";"+vehiculo.getMarca()+";"
                    +vehiculo.getModelo()+";"
                    +vehiculo.getFoto()+";"+vehiculo.getKilometraje()+";"+vehiculo.getPrecioPorDia()+";"
                    +vehiculo.isEsAutomatico()+";"+vehiculo.getNumSillas()+";"+vehiculo.isDisponible()+"%n");
            ft.close();
        }
        catch (IOException e){
            LOGGER.log(Level.SEVERE, e.getMessage());
        }

        vehiculos.add(vehiculo);
        LOGGER.log(Level.INFO, "Vehículo registrado exitosamente");



    }


    public void registrarAlquiler(Alquiler alquiler) throws NoDisponibleException, FechaInvalidaException, CampoVacioExcepcion, ObjetoNoExistenteException {
        if(alquiler.getFechaAlquiler()==null) {
            LOGGER.log(Level.WARNING, "La fecha está vacia");
            throw new CampoVacioExcepcion("Fecha dada de manera incorrecta");
        }if(alquiler.getFechaRegreso()==null){
            LOGGER.log(Level.WARNING, "La fecha está vacia");
            throw new CampoVacioExcepcion("Fecha dada de manera incorrecta");
        }if(!comprobarDisponibilidad(alquiler.getFechaAlquiler(), alquiler.getFechaRegreso(), alquiler.getVehiculo().getPlaca())){
            LOGGER.log(Level.WARNING, "Vehículo no disponible");
            throw new NoDisponibleException("Vehículo no disponible");
        }if(alquiler.getFechaAlquiler().isAfter(alquiler.getFechaRegreso())){
            LOGGER.log(Level.WARNING, "Fecha dada de manera incorrecta");
            throw new FechaInvalidaException("Fecha dada de manera incorrecta");
        }if(!existeCliente(alquiler.getCliente())){
            LOGGER.log(Level.WARNING, "No se ingresó la cédula del cliente");
            throw new ObjetoNoExistenteException("Cliente no existente. Debe registrarlo");
        }

        alquileres.add(alquiler);

        try {
            ArchivoUtils.serializarObjeto("src/main/resources/persistencia/alq.data", alquileres );
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        LOGGER.log(Level.INFO, "Alquiler registrado exitosamente");

    }

    public boolean comprobarDisponibilidad(LocalDateTime fechaInicio, LocalDateTime fechaFinal, String placa) {
        boolean disponible= true;
        for(Alquiler alquiler: alquileres){
            if (alquiler.getVehiculo().getPlaca().equals(placa)) {
                if((fechaInicio.isBefore(alquiler.getFechaRegreso()) && fechaFinal.isAfter(alquiler.getFechaAlquiler())) ||
                fechaInicio.isEqual(alquiler.getFechaRegreso()) || fechaInicio.isEqual(alquiler.getFechaAlquiler()) ||
                fechaFinal.isEqual(alquiler.getFechaRegreso()) || fechaFinal.isEqual(alquiler.getFechaAlquiler())){
                    disponible= false;
                }
            }
        }
        return disponible;
    }

    public boolean existeCliente(Cliente cliente) {
        boolean flag = false;
        for (int i = 0; i < clientes.size() && !flag; i++) {
            if (clientes.get(i).getCedula().equals(cliente.getCedula())) {
                flag = true;
            }
        }
        return flag;
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
        return flag;
    }

    public ArrayList<Vehiculo> encontrarVehiculosDisponibles(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        ArrayList<Vehiculo> vehDisponibles = new ArrayList<>();

        for(Vehiculo vehiculo: vehiculos){
            if( !estaAlquilado(vehiculo) ){
                vehDisponibles.add(vehiculo);
            }else{
                if(comprobarDisponibilidad(fechaInicio, fechaFin, vehiculo.getPlaca())){
                    vehDisponibles.add(vehiculo);
                }
            }

        }
        vehDisponibles.sort(Comparator.comparing(Vehiculo::getPrecioPorDia));
        return vehDisponibles;
    }

    public ArrayList<Vehiculo> encontrarVehiculosAlquilados(LocalDateTime fecha){
        ArrayList<Vehiculo> vehAlquilados = new ArrayList<>();
        for(Alquiler alquiler: alquileres){
            if((fecha.isAfter(alquiler.getFechaAlquiler()) && fecha.isBefore(alquiler.getFechaRegreso()))
                    || fecha.isEqual(alquiler.getFechaAlquiler()) || fecha.isEqual(alquiler.getFechaRegreso())){
                vehAlquilados.add(alquiler.getVehiculo());
            }
        }
        return vehAlquilados;
    }

    public boolean estaAlquilado(Vehiculo vehiculo){
        boolean flag=  false;
        for(Alquiler alquiler: alquileres){
            if(alquiler.getVehiculo().getPlaca().equals(vehiculo.getPlaca())){
                flag= true;
            }
        }
        return flag;

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
        double precio = 0;
        for (Alquiler alquiler : alquileres) {
            if (fechaInicio.isBefore(alquiler.getFechaAlquiler()) && fechaFin.isAfter(alquiler.getFechaRegreso())){
                precio += alquiler.getValorTotal();
            }
        }
        return precio;
    }


    public ArrayList<String> retornarMarcas() {
        Set<String> marcasSet = new HashSet<>();
        for (Alquiler alquiler : alquileres) {
            marcasSet.add(alquiler.getVehiculo().getMarca());
        }

        // Convertir el conjunto en un ArrayList y retornarlo
        ArrayList<String> marcasList = new ArrayList<>(marcasSet);
        return marcasList;
    }

    public int cuantasVecesSeRepite(String marca, ArrayList<String> marcas) {
        int cuantasVeces = 0;
        for (int i = 0; i < marcas.size(); i++) {
            if (marcas.get(i).equals(marca)) {
                cuantasVeces++;
            }
        }
        return cuantasVeces;
    }

    public String obtenerMarcaMasAlquilada() {
        ArrayList<String> marcas = retornarMarcas();

        if (marcas.isEmpty()) {
            return "";
        }

        String marcaMasAlquilada = marcas.get(0);
        int maxVeces = cuantasVecesSeRepite(marcaMasAlquilada, marcas);

        for (int i = 1; i < marcas.size(); i++) {
            String marcaActual = marcas.get(i);
            int veces = cuantasVecesSeRepite(marcaActual, marcas);
            if (veces > maxVeces) {
                maxVeces = veces;
                marcaMasAlquilada = marcaActual;
            }
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
