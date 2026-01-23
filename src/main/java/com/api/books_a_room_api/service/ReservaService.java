package com.api.books_a_room_api.service;

import com.api.books_a_room_api.dto.CriarReservaDTO;
import com.api.books_a_room_api.model.Reserva;
import com.api.books_a_room_api.model.Sala;
import com.api.books_a_room_api.model.Usuario;
import com.api.books_a_room_api.model.enums.Status;
import com.api.books_a_room_api.repository.ReservaRepository;
import com.api.books_a_room_api.repository.SalaRepository;
import com.api.books_a_room_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void criarReserva(CriarReservaDTO dto) {
        if (dto == null){
            throw new InputMismatchException("Informe todos os dados corretamente");
        }

        Sala sala = salaRepository.getReferenceById(dto.idSala());
        Usuario usuario = usuarioRepository.getReferenceById(dto.idUsuario());

        if (sala.getReserva() != null){
            Reserva reserva = sala.getReserva();
            if (reserva.getDataFinal().isAfter(LocalDate.now())){
                sala.setReserva(null);
                salaRepository.save(sala);
            }else {
                throw new RuntimeException("Ja existe outra reserva anexada com essa sala, está locada até: " + reserva.getDataFinal());
            }

        }

        if (sala == null){
            throw new NullPointerException("O id da sala informado não existe ");
        }
        if (usuario == null){
            throw new NullPointerException("O id do usuario informado não existe ");
        }

        try {
            Reserva reserva = new Reserva(dto, sala, usuario);
            reservaRepository.save(reserva);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }

    }


    public Reserva buscarReservaPorId(Long id) {
        Reserva reserva = reservaRepository.getReferenceById(id);

        if (reserva == null){
            throw new NullPointerException("Nenhum Reserva encontrada com esse id");
        }

        return reserva;
    }

    public List<Reserva> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();

        if (reservas.isEmpty()){
            throw new NullPointerException("Nenhum Reserva encontrada");
        }

        return reservas;
    }

    public List<Reserva> listarReservasAtivas() {
        List<Reserva> reservas = listarReservas();

        List<Reserva> reservasAtivas = reservas.stream()
                .filter(r -> r.getStatus() == Status.ATIVA)
                .toList();

        return reservasAtivas;
    }

    public List<Reserva> listarReservasCanceladas() {
        List<Reserva> reservas = listarReservas();

        List<Reserva> reservasCanceladas = reservas.stream()
                .filter(r -> r.getStatus() == Status.CANCELADA)
                .toList();

        return reservasCanceladas;
    }

    public void mudarStatusDaReserva(Long id) {
        Reserva reserva = reservaRepository.getReferenceById(id);
        if (reserva == null){
            throw new NullPointerException("Nenhum Reserva encontrada com  esse id");
        }

        if (reserva.getStatus() == Status.ATIVA){
            reserva.setStatus(Status.CANCELADA);
        }else {
            reserva.setStatus(Status.ATIVA);
        }

        reservaRepository.save(reserva);
    }
}
