package com.ligas.futbol.equipos;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.lang.*;

@Service
@Transactional
public class JugadorServiceImpl implements JugadorService {

    private final JugadorRepository repo;

    // Constructor injection (no necesita @Autowired explícito en Spring moderno)
    public JugadorServiceImpl(JugadorRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Jugador> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Jugador> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Jugador save(Jugador jugador) {
        // Validaciones o reglas de negocio aquí si se desean
        return repo.save(jugador);
    }

    @Override
    public Optional<Jugador> update(Long id, Jugador jugador) {
        return repo.findById(id).map(existing -> {
            existing.setNombre(jugador.getNombre());
            existing.setApellido(jugador.getApellido());
            existing.setNumero(jugador.getNumero());
            existing.setPosicion(jugador.getPosicion());
            existing.setFechaNacimiento(jugador.getFechaNacimiento());
            existing.setEquipoId(jugador.getEquipoId());
            return repo.save(existing);
        });
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Jugador> findByEquipoId(Long equipoId) {
        return repo.findByEquipoId(equipoId);
    }
}
