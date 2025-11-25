package com.ligas.futbol.equipos;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private Integer numero; // dorsal

    private String posicion; // ej. "Delantero", "Defensa", etc.

    private LocalDate fechaNacimiento;

    // Relación simplificada: solo guardamos el id del equipo.
    private Long equipoId;
}
