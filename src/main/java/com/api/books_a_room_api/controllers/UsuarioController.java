package com.api.books_a_room_api.controllers;

import com.api.books_a_room_api.dto.AtualizarUsuarioDTO;
import com.api.books_a_room_api.dto.CriarUsuarioDTO;
import com.api.books_a_room_api.dto.ResponseUsuarioDTO;
import com.api.books_a_room_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<ResponseUsuarioDTO>> listarUsuarios(){
        try {
            List<ResponseUsuarioDTO> usuarioDTOS = usuarioService.listarTodos();
            return ResponseEntity.ok(usuarioDTOS);

        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastroUsuario(@RequestBody CriarUsuarioDTO dto){
        try {
            usuarioService.criarUsuario(dto);
            return ResponseEntity.status(200).build();

        }catch (Exception e){
            return ResponseEntity.status(400).build();
        }

    }

    @PutMapping("/atualizar")
    public ResponseEntity<Object> atualizarUsuario(@RequestBody AtualizarUsuarioDTO dto){
        try {
            usuarioService.atualizarUsuario(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }catch (Exception e){
            return ResponseEntity.status(400).build();
        }

    }

    @DeleteMapping("/deletar/{idUsuario}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable Long idUsuario){
        try {
            usuarioService.deletarUsuario(idUsuario);
            return ResponseEntity.status(200).build();

        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }
}
