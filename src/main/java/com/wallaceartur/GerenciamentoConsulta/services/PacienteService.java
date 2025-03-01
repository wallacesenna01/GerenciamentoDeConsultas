package com.wallaceartur.GerenciamentoConsulta.services;

import com.wallaceartur.GerenciamentoConsulta.model.Paciente;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.AtualizarPacienteDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.NovoPacienteDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.PacienteDTO;
import com.wallaceartur.GerenciamentoConsulta.model.repositories.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public PacienteDTO novoPacienteDto(NovoPacienteDTO dto ) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.nome());
        paciente.setCpf(dto.cpf());
        paciente.setDataNascimento(dto.dataNascimento());
        paciente.setTelefone(dto.telefone());
        paciente.setEmail(dto.email());

        Paciente salvo = pacienteRepository.save(paciente);

        return new PacienteDTO(salvo);
    }

    public PacienteDTO updatePacienteDTO(AtualizarPacienteDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.setEmail(dto.email());
        paciente.setTelefone(dto.telefone());

        Paciente salvo = pacienteRepository.save(paciente);

        rabbitTemplate.convertAndSend("fila_notificacao_paciente", salvo.getEmail());

        return new PacienteDTO(salvo);

    }

    public List<PacienteDTO> findAll() {
        return pacienteRepository.findAll().stream()
                .map(PacienteDTO::new)
                .toList();
    }

    public void deletarPaciente(Long id) {
        if(!pacienteRepository.existsById(id)){
            throw new EntityNotFoundException("Paciente nao encontrado");
        }
        pacienteRepository.deleteById(id);
    }

}
