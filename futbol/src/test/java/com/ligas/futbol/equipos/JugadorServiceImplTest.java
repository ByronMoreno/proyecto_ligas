package com.ligas.futbol.equipos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JugadorServiceImplTest {
    @Mock
    private JugadorRepository repo;

    @InjectMocks
    private JugadorServiceImpl service;

    private Jugador jugador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jugador = Jugador.builder()
                .id(1L)
                .nombre("Test")
                .apellido("Jugador")
                .numero(9)
                .posicion("Delantero")
                .fechaNacimiento(java.time.LocalDate.of(1990,1,1))
                .equipoId(1L)
                .build();
    }

    @Test
    void findAll_delegatesToRepository() {
        when(repo.findAll()).thenReturn(List.of(jugador));

        List<Jugador> result = service.findAll();

        assertEquals(1, result.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void findById_found() {
        when(repo.findById(1L)).thenReturn(Optional.of(jugador));

        Optional<Jugador> opt = service.findById(1L);

        assertTrue(opt.isPresent());
        assertEquals("Test", opt.get().getNombre());
    }

    @Test
    void update_existing_returnsUpdated() {
        Jugador updated = Jugador.builder()
                .nombre("Nuevo")
                .apellido("Apellido")
                .numero(10)
                .posicion("Medio")
                .fechaNacimiento(java.time.LocalDate.of(1991,1,1))
                .equipoId(2L)
                .build();

        when(repo.findById(1L)).thenReturn(Optional.of(jugador));
        when(repo.save(any(Jugador.class))).thenAnswer(inv -> inv.getArgument(0));

        Optional<Jugador> res = service.update(1L, updated);

        assertTrue(res.isPresent());
        assertEquals("Nuevo", res.get().getNombre());
        verify(repo).save(any(Jugador.class));
    }
}
