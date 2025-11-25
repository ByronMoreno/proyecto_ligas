package com.ligas.futbol.categorias;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    //Buscar todos
    @Override
    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    //Buscar por ID
    @Override
    public Optional<Categoria> findById(Long id){
        return categoriaRepository.findById(id);
    }

    //Guardar un registro
    @Override
    public Categoria save(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    //Actualizar todos los registros de una categoria
    @Override
    public Optional<Categoria> update(Long id, Categoria categoria){
        return categoriaRepository.findById(id).map(existing -> {
            existing.setNombre(categoria.getNombre());
            existing.setDescripcion(categoria.getDescripcion());
            existing.setEstado(categoria.getEstado());
            existing.setCampeonato(categoria.getCampeonato());
            return categoriaRepository.save(existing);
        });
    }

    //Borrar un categoria
    @Override
    public void delete(Long id){
        categoriaRepository.deleteById(id);
    }

    //Buscar por el nombre de la categoria
    @Override
    public List<Categoria> findByNombre(String nombre){
        return categoriaRepository.findByNombre(nombre);
    }

    //Buscar por el campeonato_id
    @Override
    public List<Categoria> findByCampeonatoId(Long campeonatoId){
        return categoriaRepository.findByCampeonato_Id(campeonatoId);
    }
}
