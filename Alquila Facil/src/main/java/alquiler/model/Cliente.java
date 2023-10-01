package alquiler.model;

import lombok.*;

@ToString
@Getter
@Setter
public class Cliente {

    private String cedula;
    private String nombre;
    private String apellidos;
    private int telefono;
    private  String email;
    private String ciudad;
    private String direccion;

    @Builder
    public Cliente(String cedula, String nombre, String apellidos, int telefono, String email, String ciudad, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }
}
