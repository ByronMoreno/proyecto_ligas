package com.ligas.futbol.categorias;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNombre(String nombre);
    List<Categoria> findByCampeonato_Id(Long campeonatoId);
}
