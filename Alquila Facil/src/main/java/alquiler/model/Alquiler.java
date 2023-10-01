package alquiler.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@ToString
@Getter
@Setter
@AllArgsConstructor
public class Alquiler {

    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDateTime fechaAlquiler;
    private LocalDateTime fechaRegreso;
    private float valorTotal;

}
