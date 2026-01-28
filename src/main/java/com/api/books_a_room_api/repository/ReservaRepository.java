package com.api.books_a_room_api.repository;

import com.api.books_a_room_api.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {

}
