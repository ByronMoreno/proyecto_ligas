package com.ligas.jugador.jugadores;

import com.ligas.jugador.campeonatoDTO.CampeonatoDTO;
import com.ligas.jugador.campeonatoDTO.CampeonatoFeignApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JugadorServiceImpl implements JugadorService {
    private final JugadorRepository jugadorRepository;
    //Para buscar los campeonatos
    private final CampeonatoFeignApi campeonatoFeignApi;

    //Buscar todos
    @Override
    public List<Jugador> findAll() {
        return jugadorRepository.findAll();
    }

    //Buscar por id
    @Override
    public Optional<Jugador> findById(Long id) {
        return jugadorRepository.findById(id);
    }

    //Guardar un registro
    @Override
    public Jugador save(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    //Actualizar todos las propiedades de un jugador
    @Override
    public Optional<Jugador> update(Long id, Jugador jugador) {
        return jugadorRepository.findById(id).map(existing -> {
            existing.setNombres(jugador.getNombres());
            existing.setApellidos(jugador.getApellidos());
            existing.setFechaNacimiento(jugador.getFechaNacimiento());
            existing.setActivo(jugador.getActivo());
            return jugadorRepository.save(existing);
        });
    }

    //Borrar un jugador
    @Override
    public void delete(Long id) {
        jugadorRepository.deleteById(id);
    }

    //Buscar por id de equipo
    @Override
    public List<Jugador> findByIdEquipo(Long idEquipo) {
        return jugadorRepository.findByIdEquipo(idEquipo);
    }

    //Para buscar el campeonato
    @Override
    public CampeonatoDTO findByIdCampeonato(Long idCampeonato) {
        return campeonatoFeignApi.getCampeonatoById(idCampeonato);
    }
}
