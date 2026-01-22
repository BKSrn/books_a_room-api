package com.api.books_a_room_api.dto;

import jakarta.validation.constraints.NotNull;

public record CriarSalaDTO(@NotNull String nome,
                           @NotNull Integer capacidade) {
}
