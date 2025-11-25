package com.ligas.jugador.jugadores;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    List<Jugador> findByIdEquipo(Long idEquipo);
}
