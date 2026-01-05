package com.api.books_a_room_api.dto;

import org.antlr.v4.runtime.misc.NotNull;

public record CriarSalaDTO(@NotNull String nome,
                           @NotNull Integer capacidade) {
}
