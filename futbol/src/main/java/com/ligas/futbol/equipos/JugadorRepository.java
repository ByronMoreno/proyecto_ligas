package com.ligas.futbol.equipos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    //Consultas adicionales utiles
    List<Jugador> findByEquipoId(Long equipoId);
}
