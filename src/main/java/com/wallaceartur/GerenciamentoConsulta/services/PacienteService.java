package com.wallaceartur.GerenciamentoConsulta.services;

import com.wallaceartur.GerenciamentoConsulta.model.Paciente;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.AtualizarPacienteDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.NovoPacienteDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.PacienteDTO;
import com.wallaceartur.GerenciamentoConsulta.model.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

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

        return new PacienteDTO(salvo);

    }


}
