package com.ligas.futbol.categorias;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    //Obtener todas las categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    //Obtener las categorias por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return categoriaService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("Categoria", id+ " no encontrada")));
    }

    //Obtener las categorias por id de campeonato
    @GetMapping("/campeonato/{campeonatoId}")
    public ResponseEntity<List<Categoria>> findByCampeonato(@PathVariable Long campeonatoId) {
        return ResponseEntity.ok(categoriaService.findByCampeonatoId(campeonatoId));
    }

    //Guardar una categoria
    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
        Categoria creada = categoriaService.save(categoria);
        return ResponseEntity.created(URI.create("/api/categorias/" + creada.getId())).body(creada);
    }

    //Actualizar una categoria PUT
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Categoria categoria) {
        return categoriaService.update(id,categoria)
                .map(actualizado -> ResponseEntity.ok((Object) actualizado))
                .orElseGet(()-> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje","No se puede actualizar, categoria " +id+", no encontrado")));
    }

    //Actualizar una categoria PATHC
    @PatchMapping("/{id}")
    public ResponseEntity<Object> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> categoria) {
        return categoriaService.findById(id)
                .map(existing -> {
                    categoria.forEach((key, value) -> {
                        switch (key){
                            case "nombre" -> existing.setNombre((String) value);
                            case "descripcion" -> existing.setDescripcion((String) value);
                            case "estado" -> existing.setEstado((Boolean) value);
                            default -> {
                                //Ignorar lo desconocido
                            }

                        }
                    });
                    Categoria actualizada = categoriaService.save(existing);
                    return ResponseEntity.ok((Object) actualizada);
                })
                .orElseGet(()-> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje","No se puede aplicar PATCH, categoria "+id+", no encontrado")));
    }
    //Borrar un categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return categoriaService.findById(id)
                .map(existing -> {
                    categoriaService.delete(id);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje","No se puede eliminar, categoria "+id+", no encontrado")));
    }
}
