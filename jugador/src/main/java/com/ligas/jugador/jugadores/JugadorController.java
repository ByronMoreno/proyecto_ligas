package com.ligas.jugador.jugadores;

import com.ligas.jugador.campeonatoDTO.CampeonatoDTO;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.commons.config.DefaultsBindHandlerAdvisor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/jugadores")
@RequiredArgsConstructor
public class JugadorController {
    private final JugadorService jugadorService;
    private final DefaultsBindHandlerAdvisor.MappingsProvider mappingsProvider;

    //Obtener todos los jugadores
    @GetMapping
    public ResponseEntity<List<Jugador>> findAll() {
        return ResponseEntity.ok(jugadorService.findAll());
    }

    //Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return jugadorService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje",id+" no encontrador")));
    }

    //Para buscar por campeonado
    @GetMapping("/campeonato/{id}")
    public CampeonatoDTO findByIdCampeonato(@PathVariable Long id) {
        return jugadorService.findByIdCampeonato(id);
    }

    //Guardar
    @PostMapping
    public ResponseEntity<Jugador> save(@RequestBody Jugador jugador) {
        Jugador creado = jugadorService.save(jugador);
        return ResponseEntity.created(URI.create("/api/jugadores/"+creado.getId())).body(creado);
    }

    //Borrar un jugador
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id, ServletRequest servletRequest) {
        return jugadorService.findById(id)
                .map(existing ->{
                    jugadorService.delete(id);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje","No se puede eliminar, jugador "+id+" no encontrado")));
    }


}
