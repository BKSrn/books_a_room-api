package com.api.books_a_room_api.dto;

import java.util.List;

public record ResponseUsuarioDTO(String nome,
                                 String email,
                                 String senha,
                                 List<Long> idReservas) {
}
