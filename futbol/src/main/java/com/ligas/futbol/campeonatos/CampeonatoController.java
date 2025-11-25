package com.ligas.futbol.campeonatos;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/campeonato")
@RequiredArgsConstructor
public class CampeonatoController {
    private final CampeonatoService campeonatoService;

    //Obtener todos los campaonatos
    @GetMapping()
    public ResponseEntity<List<Campeonato>> findAll(){
        return ResponseEntity.ok(campeonatoService.findAll());
    }

    //Obtener campoanto por id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return campeonatoService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("Campeonato: ", id+" no encontrado")));
    }

    //Crear un nuevo campeonato
    @PostMapping()
    public ResponseEntity<Campeonato> update(@RequestBody Campeonato campeonato){
        Campeonato creado = campeonatoService.save(campeonato);
        return ResponseEntity.created(URI.create("/api/campeonato"+creado.getId())).body(creado);
    }

    //Actualizar un campeonato put
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Campeonato campeonato) {
        return campeonatoService.update(id, campeonato)   // devuelve Optional<Campeonato>
                .map(actualizado -> ResponseEntity.ok((Object) actualizado))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "No se puede actualizar, campeonato " + id + " no encontrado")));
    }

    // ✅ Actualización parcial de campos (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<Object> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return campeonatoService.findById(id)
                .map(existing -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "nombre" -> existing.setNombre((String) value);
                            case "descripcion" -> existing.setDescripcion((String) value);
                            case "fechaInicio" -> existing.setFechaInicio(LocalDate.parse((String) value));
                            case "fechaFin" -> existing.setFechaFin(LocalDate.parse((String) value));
                            case "estado" -> existing.setEstado((Boolean) value);
                            default -> {
                            } // Ignorar campos desconocidos
                        }
                    });
                    Campeonato actualizado = campeonatoService.save(existing);
                    return ResponseEntity.ok((Object) actualizado);
                })
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "No se puede aplicar PATCH, campeonato " + id + " no encontrado")));
    }

    //Eliminar un campeonato
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        return campeonatoService.findById(id)
                .map(existing ->{
                    campeonatoService.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(()-> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "No se puedo eliminar campeonato " + id + " no encontrado")));
    }
}
