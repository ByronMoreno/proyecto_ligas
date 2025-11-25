package com.ligas.jugador.jugadores;

import com.ligas.jugador.campeonatoDTO.CampeonatoDTO;

import java.util.List;
import java.util.Optional;

public interface JugadorService {
    public List<Jugador> findAll();
    public Optional<Jugador> findById(Long id);
    public Jugador save(Jugador jugador);
    public Optional<Jugador> update(Long id, Jugador jugador);
    public void delete(Long id);
    //Metodos adicionales
    public List<Jugador> findByIdEquipo(Long idEquipo);

    //Buscar el campeonado con FeingClient
    public CampeonatoDTO findByIdCampeonato(Long id);
}
