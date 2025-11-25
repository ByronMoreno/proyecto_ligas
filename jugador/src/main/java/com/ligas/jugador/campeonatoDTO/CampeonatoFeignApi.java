package com.ligas.jugador.campeonatoDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="FUTBOL")
public interface CampeonatoFeignApi {
    @GetMapping("/api/campeonato/{id}")
    CampeonatoDTO getCampeonatoById(@PathVariable("id") Long id);
}
