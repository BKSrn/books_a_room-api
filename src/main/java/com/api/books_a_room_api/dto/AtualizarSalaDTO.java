package com.api.books_a_room_api.dto;

public record AtualizarSalaDTO(Long id,
                               String nome,
                               Integer capacidade,
                               Boolean ativo) {
}
