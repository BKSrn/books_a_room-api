package com.api.books_a_room_api.controllers;

import com.api.books_a_room_api.dto.CriarReservaDTO;
import com.api.books_a_room_api.model.Reserva;
import com.api.books_a_room_api.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/criar")
    public ResponseEntity<Object> criarReserva(@RequestBody CriarReservaDTO dto){
        try {
            reservaService.criarReserva(dto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReserva(@PathVariable Long id){
        try {
            Reserva reserva = reservaService.buscarReservaPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(reserva);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/ativas")
    public ResponseEntity<List<Reserva>> listarReservasAtivas(){
        try {
            List<Reserva> reservasAtivas = reservaService.listarReservasAtivas();
            return ResponseEntity.status(HttpStatus.OK).body(reservasAtivas);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/canceladas")
    public ResponseEntity<List<Reserva>> listarReservasCanceladas(){
        try {
            List<Reserva> reservasCanceladas = reservaService.listarReservasCanceladas();
            return ResponseEntity.status(HttpStatus.OK).body(reservasCanceladas);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas(){
        try {
            List<Reserva> reservas = reservaService.listarReservas();
            return ResponseEntity.status(HttpStatus.OK).body(reservas);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/mudar_status/{id}")
    public ResponseEntity<Object> mudarStatusReserva(@PathVariable Long id){
        try {
            reservaService.mudarStatusDaReserva(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


}
