package com.ligas.futbol.categorias;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> findAll();
    Optional<Categoria> findById(Long id);
    Categoria save(Categoria categoria);
    Optional<Categoria> update(Long id, Categoria categoria);
    void delete(Long id);

    //Metodos de negocio adicionales
    List<Categoria> findByNombre(String nombre);
    List<Categoria> findByCampeonatoId(Long campeonatoId);
}
