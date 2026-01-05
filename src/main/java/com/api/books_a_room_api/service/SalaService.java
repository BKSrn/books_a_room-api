package com.api.books_a_room_api.service;

import com.api.books_a_room_api.dto.AtualizarSalaDTO;
import com.api.books_a_room_api.dto.CriarSalaDTO;
import com.api.books_a_room_api.dto.ResponseSalaDTO;
import com.api.books_a_room_api.model.Sala;
import com.api.books_a_room_api.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public void criarSala(CriarSalaDTO dto) {
        if (dto == null){
            throw new RuntimeException("Input do usuario incorreto");
        }

        Sala sala = new Sala(dto);
        salaRepository.save(sala);
    }

    public List<ResponseSalaDTO> listarSalas() {
        List<Sala> salas = salaRepository.findAll();

        if (salas.isEmpty()){
            throw new RuntimeException("Nenhum sala encontrada");
        }

        return salas.stream()
                .map(s -> new ResponseSalaDTO(s.getNome(), s.getCapacidade(), s.isAtivo(), s.getReservaId()))
                .collect(Collectors.toList());
    }

    public List<ResponseSalaDTO> listarSalasAtivas() {
        List<Sala> salas = salaRepository.findAll();

        if (salas.isEmpty()){
            throw new RuntimeException("Nenhum sala encontrada");
        }

        return salas.stream()
                .filter(s -> s.isAtivo())
                .map(s -> new ResponseSalaDTO(s.getNome(), s.getCapacidade(), s.isAtivo(), s.getReservaId()))
                .toList();

    }

    public ResponseSalaDTO buscarSalaPorId(Long id) {
        Sala sala = salaRepository.getReferenceById(id);

        if (sala == null){
            throw new RuntimeException("Sala não encontrada");
        }

        return new ResponseSalaDTO(
                sala.getNome(),
                sala.getCapacidade(),
                sala.isAtivo(),
                sala.getReservaId()
        );

    }

    public ResponseSalaDTO atualizarSala(AtualizarSalaDTO dto) {
        Sala sala = salaRepository.getReferenceById(dto.id());

        if (sala == null){
            throw new RuntimeException("Sala não encontrada");
        }

        sala.atualizar(dto);
        if (dto.ativo() == false && sala.isAtivo()){
            sala.desativar();
        }else if(!sala.isAtivo()){
            sala.ativar();
        }

        Sala salaSalva = salaRepository.save(sala);
        return new ResponseSalaDTO(
                salaSalva.getNome(),
                salaSalva.getCapacidade(),
                salaSalva.isAtivo(),
                salaSalva.getReservaId()
        );

    }

    public String deletarSalaPorId(Long id) {
        try {
            salaRepository.deleteById(id);
            return "Sala " + id + " Deletado com sucesso";
        }catch (Exception e){
            throw new RuntimeException("Erro ao deletar sala por id " + id);
        }

    }
}
