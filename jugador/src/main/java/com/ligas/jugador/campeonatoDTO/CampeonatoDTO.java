package com.ligas.jugador.campeonatoDTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CampeonatoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean estado = Boolean.TRUE;
}
