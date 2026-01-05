package com.api.books_a_room_api.service;

import com.api.books_a_room_api.dto.AtualizarUsuarioDTO;
import com.api.books_a_room_api.dto.CriarUsuarioDTO;
import com.api.books_a_room_api.dto.ResponseUsuarioDTO;
import com.api.books_a_room_api.model.Reserva;
import com.api.books_a_room_api.model.Usuario;
import com.api.books_a_room_api.repository.ReservaRepository;
import com.api.books_a_room_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    public List<ResponseUsuarioDTO> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()){
            throw new RuntimeException("Nenhum usuario cadastrado");
        }

        List<Long> idReservas = new ArrayList<>();

        for (Usuario usuario : usuarios){
            for (Reserva reserva : usuario.getReservas()){
                idReservas.add(reserva.getId());
            }
        }

        return usuarios.stream()
                .map(u -> new ResponseUsuarioDTO(u.getNome(), u.getEmail(), u.getSenha(), idReservas))
                .toList();
    }


    public void criarUsuario(CriarUsuarioDTO dto) {
        List<Usuario> usuariosCadastrados = usuarioRepository.findAll();
        List<Usuario> usuariosFiltrados = usuariosCadastrados.stream()
                .filter(u -> u.getNome().equalsIgnoreCase(dto.nome()))
                .toList();

        if (dto == null){
            throw  new RuntimeException("Informe os dados do usuario corretamente");
        }
        if (!usuariosFiltrados.isEmpty()){
            throw new RuntimeException("Já existe um usuario cadastrado com esse nome");
        }
        if (!validarEmail(dto.email())){
            throw new RuntimeException("Informe o email corretamente");
        }
        if (dto.senha().length() < 6){
            throw new RuntimeException("Senha deve ter no minimo 6 caracteres");
        }

        usuarioRepository.save(new Usuario(dto));
    }

    public void atualizarUsuario(AtualizarUsuarioDTO dto) {
        Usuario usuario = usuarioRepository.getReferenceById(dto.id());
        Reserva reserva = reservaRepository.getReferenceById(dto.idReserva());

        if (dto == null){
            throw  new RuntimeException("Informe os dados do usuario corretamente");
        }
        if (reserva == null){
            throw new RuntimeException("Nenhum Reserva com esse identificado");
        }
        if (!validarEmail(dto.email())){
            throw new RuntimeException("Informe o email corretamente");
        }
        if (dto.senha().length() < 6){
            throw new RuntimeException("Senha deve ter no minimo 6 caracteres");
        }

        List<Reserva> reservasDoUsuario = usuario.getReservas();
        reservasDoUsuario.add(reserva);
        usuario.setReservas(reservasDoUsuario);
        usuario.atualizarDadosBasicos(dto);

        usuarioRepository.save(usuario);
    }



    public boolean validarEmail(String email) {
        final Pattern EMAIL_PATTERN = Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
                Pattern.CASE_INSENSITIVE
        );

        if (email == null) return false;
        email = email.trim();
        if (email.isEmpty()) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
