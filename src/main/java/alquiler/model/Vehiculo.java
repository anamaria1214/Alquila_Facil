package alquiler.model;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Vehiculo {

    private String placa;
    private String nombre;
    private String marca;
    private int modelo;
    private String foto;
    private int kilometraje;
    private double precioPorDia;
    private boolean esAutomatico;
    private int numSillas;
    private boolean disponible;
    public Vehiculo(String placa,String referencia, String marca, int modelo, String foto
    ,int kilometraje,double precioPorDia, boolean esAutomatico, int numSillas, boolean disponible ) {
        this.placa=placa;
        this.nombre= referencia;
        this.marca= marca;
        this.modelo= modelo;
        this.foto= foto;
        this.kilometraje= kilometraje;
        this.precioPorDia= precioPorDia;
        this.esAutomatico= esAutomatico;
        this.numSillas= numSillas;
        this.disponible= disponible;
    }


}
