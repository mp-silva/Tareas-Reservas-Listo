package com.clubdeportivo2.servicioreservas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubdeportivo2.servicioreservas.model.Reserva;
import com.clubdeportivo2.servicioreservas.repository.ReservaRepository;
import java.util.List;
import com.clubdeportivo2.servicioreservas.model.dto.Cancha; 
import com.clubdeportivo2.servicioreservas.client.CanchaClient; 

@Service
public class ReservaServicesImpl implements ReservaServices {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private CanchaClient canchaClient;

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva crearReserva(Reserva reserva) {
        
        Cancha canchaExistente;
        
        try {
            canchaExistente = canchaClient.obtenerCanchaPorId(reserva.getCanchaId());
        } catch (Exception e) {
            throw new RuntimeException("Error: El Id es incorrecto");
        }

        if (canchaExistente != null) {
            return reservaRepository.save(reserva);
        } else {
            throw new RuntimeException("Error: El Id es incorrecto");
        }
    }

    @Override
    public List<Reserva> buscarReservasPorCancha(Long canchaId) {
        return reservaRepository.findByCanchaId(canchaId);
    }
}