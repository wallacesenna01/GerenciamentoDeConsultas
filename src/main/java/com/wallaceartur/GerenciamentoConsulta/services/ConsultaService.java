package com.wallaceartur.GerenciamentoConsulta.services;

import com.wallaceartur.GerenciamentoConsulta.enums.StatusConsulta;
import com.wallaceartur.GerenciamentoConsulta.model.Consulta;
import com.wallaceartur.GerenciamentoConsulta.model.Medico;
import com.wallaceartur.GerenciamentoConsulta.model.Paciente;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.AtualizarConsultaDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.ConsultaDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.NovaConsultaDTO;
import com.wallaceartur.GerenciamentoConsulta.model.repositories.ConsultaRepository;
import com.wallaceartur.GerenciamentoConsulta.model.repositories.MedicoRepository;
import com.wallaceartur.GerenciamentoConsulta.model.repositories.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;


    public ConsultaDTO agendarConsulta(NovaConsultaDTO dto) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dataHora = LocalDateTime.parse(dto.datahora(), formatter);


        Medico medico = medicoRepository.findById(dto.medicoId())
                .orElseThrow(() -> new RuntimeException("Medico não encontrado"));

        Paciente paciente  = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Consulta consulta = new Consulta(null, dataHora, StatusConsulta.AGENDADA, paciente, medico);

        consulta = consultaRepository.save(consulta);

        return new ConsultaDTO(consulta);
    }

    public ConsultaDTO atualizar(AtualizarConsultaDTO dto) {
        Consulta consulta = consultaRepository.findById(dto.id())
                .orElseThrow(() -> new IllegalArgumentException("Consulta nao encontrada"));

        if (dto.datahora() != null) {
            consulta.setDataHora(dto.datahora());
        }

        if (dto.medicoId() != null) {
            Medico medico = medicoRepository.findById(dto.medicoId())
                    .orElseThrow(() -> new IllegalArgumentException("Medico nao encontrado"));
        }

        Consulta consultAtualizada = consultaRepository.save(consulta);
        return  new ConsultaDTO(consultAtualizada);
    }

    public ConsultaDTO buscarPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta nao encontrada"));
        return new ConsultaDTO(consulta);

    }

    public List<ConsultaDTO> buscarTodos() {
        return consultaRepository.findAll()
                .stream().map(ConsultaDTO::new).toList();
    }

    public void cancelarConsulta(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("consulta nao encontrada"));
        consultaRepository.delete(consulta);
     }
}
