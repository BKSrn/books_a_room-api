package com.api.books_a_room_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AtualizarUsuarioDTO(@NotNull Long id,
                                  @NotNull String nome,
                                  @Email @NotNull String email,
                                  @NotNull String senha,
                                  @NotNull Long idReserva) {
}
