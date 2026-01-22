package com.api.books_a_room_api.dto;

import jakarta.validation.constraints.NotNull;

public record CriarReservaDTO(@NotNull Long idSala,
                              @NotNull Long idUsuario,
                              @NotNull String dataInicial,
                              @NotNull String dataFinal) {
}
