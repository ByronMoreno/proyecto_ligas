    package com.ligas.futbol.categorias;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ligas.futbol.campeonatos.Campeonato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nombre;
    private String descripcion;

    @Column(nullable = false)
    private Boolean estado = Boolean.TRUE;

    //Relacionar con Campeonato(muchas categorias pertenecen a un campeonato)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campeonato_id")
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Campeonato campeonato;

    // Este getter aparecerá en el JSON como "campeonatoId"
    @JsonProperty("campeonatoId")
    public Long getCampeonatoId() {
        return (campeonato != null) ? campeonato.getId() : null;
    }

    // Relacionar con Fases (una categoria puede tener varias fases)

    // Relacionar con grupos (una categoria puede tener varios grupos)
}
