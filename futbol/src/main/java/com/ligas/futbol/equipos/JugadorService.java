package com.ligas.futbol.equipos;

import java.util.List;
import java.util.Optional;

public interface JugadorService {
    List<Jugador> findAll();
    Optional<Jugador> findById(Long id);
    Jugador save(Jugador jugador);
    Optional<Jugador> update(Long id, Jugador jugador);
    void deleteById(Long id);

    // Métodos de negocio adicionales
    List<Jugador> findByEquipoId(Long equipoId);
}
