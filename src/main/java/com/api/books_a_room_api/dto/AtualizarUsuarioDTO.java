package com.api.books_a_room_api.dto;

public record AtualizarUsuarioDTO(Long id,
                                  String nome,
                                  String email,
                                  String senha,
                                  Long idReserva) {
}
