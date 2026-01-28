package com.api.books_a_room_api.model;

import com.api.books_a_room_api.dto.AtualizarUsuarioDTO;
import com.api.books_a_room_api.dto.CriarUsuarioDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Reserva> reservas;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(CriarUsuarioDTO dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.senha = dto.senha();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void atualizarDadosBasicos(AtualizarUsuarioDTO dto) {
        setNome(dto.nome());
        setEmail(dto.email());
        setSenha(dto.senha());
    }
}
