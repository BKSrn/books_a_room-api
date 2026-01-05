package com.api.books_a_room_api.dto;

public record ResponseSalaDTO(String nome,
                              Integer capacidade,
                              Boolean ativo,
                              Long reservaId) {
}
