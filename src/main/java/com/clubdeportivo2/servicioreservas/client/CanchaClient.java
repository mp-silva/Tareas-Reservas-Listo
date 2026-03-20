package com.clubdeportivo2.servicioreservas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.clubdeportivo2.servicioreservas.model.dto.Cancha; 

@FeignClient(name = "canchas-client", url = "http://localhost:8081")
public interface CanchaClient {

    @GetMapping("/api/canchas/{id}")
    Cancha obtenerCanchaPorId(@PathVariable("id") Long id);

}
