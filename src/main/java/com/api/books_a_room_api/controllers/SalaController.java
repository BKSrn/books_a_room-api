package com.api.books_a_room_api.controllers;

import com.api.books_a_room_api.dto.AtualizarSalaDTO;
import com.api.books_a_room_api.dto.CriarSalaDTO;
import com.api.books_a_room_api.dto.ResponseSalaDTO;
import com.api.books_a_room_api.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @PostMapping
    public ResponseEntity<String> criarSala(@RequestBody CriarSalaDTO dto){
        try {
            salaService.criarSala(dto);
            return ResponseEntity.ok("Sala Criada com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/todas")
    public ResponseEntity<List<ResponseSalaDTO>> listarSalas(){
        try {
            List<ResponseSalaDTO> salas = salaService.listarSalas();
            return ResponseEntity.ok(salas);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/ativas")
    public ResponseEntity<List<ResponseSalaDTO>> listarSalasAtivas(){
        try {
            List<ResponseSalaDTO> salasAtivas = salaService.listarSalasAtivas();
            return ResponseEntity.ok(salasAtivas);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSalaDTO> buscarSalaPorId(@PathVariable Long id){
        try {
            ResponseSalaDTO sala = salaService.buscarSalaPorId(id);
            return ResponseEntity.ok(sala);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping
    public ResponseEntity<ResponseSalaDTO> atualizarSala(@RequestBody AtualizarSalaDTO dto){
        try {
            ResponseSalaDTO sala = salaService.atualizarSala(dto);
            return ResponseEntity.ok(sala);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarSalaPorId(@PathVariable Long id){
        try {
            String resposta = salaService.deletarSalaPorId(id);
            return ResponseEntity.ok(resposta);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
