package com.api.books_a_room_api.dto;

import jakarta.validation.constraints.NotNull;

public record CriarUsuarioDTO(
        @NotNull(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Email é obrigatório")
        String email,

        @NotNull(message = "Senha é obrigatória")
        String senha
) {}