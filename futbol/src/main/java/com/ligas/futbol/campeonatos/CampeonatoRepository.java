package com.ligas.futbol.campeonatos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {
    //Buscar campoenato por nombre
    List<Campeonato> findByNombre(String nombre );
}
