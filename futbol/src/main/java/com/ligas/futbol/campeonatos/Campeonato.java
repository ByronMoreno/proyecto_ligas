package com.ligas.futbol.campeonatos;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ligas.futbol.categorias.Categoria;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(length = 200)
    private String nombre;
    private String descripcion;
    @FutureOrPresent
    private LocalDate fechaInicio;
    @FutureOrPresent
    private LocalDate fechaFin;
    @NotNull
    //@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean estado = Boolean.TRUE;

    //Relacion uno a muchos con Categoria
    @OneToMany(mappedBy = "campeonato", orphanRemoval = true)
    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Categoria> categorias;
}
