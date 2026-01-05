package com.api.books_a_room_api.dto;

import org.antlr.v4.runtime.misc.NotNull;

public record CriarUsuarioDTO(@NotNull String nome,
                              @NotNull String email,
                              @NotNull String senha ) {
}
