package com.ligas.futbol.campeonatos;

import java.util.List;
import java.util.Optional;

public interface CampeonatoService {
    public List<Campeonato> findAll();
    public Optional<Campeonato> findById(Long id);
    public Campeonato save(Campeonato campeonato);
    public Optional<Campeonato> update(Long id, Campeonato campeonato);
    public void delete(Long id);
    //Metodos adicionales
    public List<Campeonato> findByName(String nome);
}
