package com.api.books_a_room_api.model;

import com.api.books_a_room_api.dto.AtualizarSalaDTO;
import com.api.books_a_room_api.dto.CriarSalaDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "salas")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Integer capacidade;
    private Boolean ativo;
    @OneToOne
    private Reserva reserva;

    public Sala(Integer capacidade, String nome) {
        setCapacidade(capacidade);
        this.nome = nome;
        this.ativo = true;
    }

    public Sala(CriarSalaDTO dto) {
        setCapacidade(dto.capacidade());
        this.nome = dto.nome();
        this.ativo = true;
    }

    public Sala(){

    }

    public Boolean isAtivo() {
        return ativo;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        if (capacidade < 0){
            throw new RuntimeException("Capacidade deve ser positiva");
        }else {
            this.capacidade = capacidade;
        }

    }

    public void atualizar(AtualizarSalaDTO dto){
        this.capacidade = dto.capacidade();
        this.nome = dto.nome();
    }

    public void ativar(){
        this.ativo = true;
    }

    public void desativar(){
        this.ativo = false;
    }

    public Long getReservaId(){
        if(this.reserva != null){
            return this.reserva.getId();
        }

        return null;
    }
}
