package com.ligas.futbol.campeonatos;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class CampeonatoServideImpl implements CampeonatoService {

    private final CampeonatoRepository campeonatoRepository;

    @Override
    public List<Campeonato> findAll() {
        return campeonatoRepository.findAll();
    }

    @Override
    public Optional<Campeonato> findById(Long id) {
        return campeonatoRepository.findById(id);
    }

    @Override
    public Campeonato save(Campeonato campeonato) {
        return campeonatoRepository.save(campeonato);
    }

    @Override
    public Optional<Campeonato> update(Long id, Campeonato campeonato) {
        return campeonatoRepository.findById(id).map(existing ->  {
            existing.setNombre(campeonato.getNombre());
            existing.setDescripcion(campeonato.getDescripcion());
            existing.setFechaInicio(campeonato.getFechaInicio());
            existing.setFechaFin(campeonato.getFechaFin());
            existing.setEstado(campeonato.getEstado());
            return campeonatoRepository.save(existing);
        });
    }

    @Override
    public void delete(Long id) {
        campeonatoRepository.deleteById(id);
    }

    @Override
    public List<Campeonato> findByName(String name) {
        return campeonatoRepository.findByNombre(name);
    }

}
