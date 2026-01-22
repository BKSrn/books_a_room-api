package com.api.books_a_room_api.model;

import com.api.books_a_room_api.dto.CriarReservaDTO;
import com.api.books_a_room_api.model.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne
    private Sala sala;
    @ManyToOne
    private Usuario usuario;

    public Reserva() {
    }

    public Reserva(Sala sala, Usuario usuario, LocalDate dataInicial, LocalDate dataFinal) {
        this.sala = sala;
        this.usuario = usuario;
        setDataInicial(dataInicial);
        setDataFinal(dataFinal);
        this.status = Status.ATIVA;
    }

    public Reserva(CriarReservaDTO dto, Sala sala, Usuario usuario) {
        this.sala = sala;
        this.usuario = usuario;
        this.status = Status.ATIVA;
        setDataInicial(LocalDate.parse(dto.dataInicial()));
        setDataFinal(LocalDate.parse(dto.dataFinal()));
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        if (dataInicial.isAfter(this.dataFinal)){
            throw new RuntimeException("Data inicial maior que data final");
        }
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        if (dataFinal.isBefore(this.dataInicial)){
            throw new RuntimeException("Data inicial menor que data final");
        }
        this.dataFinal = dataFinal;
    }

    public void cancelarStatus(){
        this.status = Status.CANCELADA;
    }
}
