package com.ligas.jugador.jugadores;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nombres;
    @NotNull
    private String apellidos;
    @Past
    private LocalDate fechaNacimiento;
    @NotNull
    private Boolean activo = Boolean.TRUE;

    //Aqui guardamos el id del equipo (no el objeto completo)
    private Long idEquipo;
}
