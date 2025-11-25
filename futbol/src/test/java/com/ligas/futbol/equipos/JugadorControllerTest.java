package com.ligas.futbol.equipos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JugadorController.class)
public class JugadorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JugadorService jugadorService;

    @Test
    void getJugador_notFound_returnsCustomMessage() throws Exception {
        when(jugadorService.findById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/jugadores/2"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jugador").value("no encontrado"));
    }

    @Test
    void getJugador_found_returnsJugador() throws Exception {
        Jugador j = Jugador.builder().id(1L).nombre("Test").apellido("A").build();
        when(jugadorService.findById(1L)).thenReturn(Optional.of(j));

        mockMvc.perform(get("/api/jugadores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Test"));
    }
}
