package com.wallaceartur.GerenciamentoConsulta.model.dtos;

import com.wallaceartur.GerenciamentoConsulta.model.Consulta;
import com.wallaceartur.GerenciamentoConsulta.model.Medico;
import com.wallaceartur.GerenciamentoConsulta.model.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ConsultaDTO(Long id, LocalDateTime dateHora, String status, MedicoDTO medicoDTO, PacienteDTO pacienteDTO) {

   public ConsultaDTO(Consulta consulta) {
       this(consulta.getId(), consulta.getDataHora(),consulta.getStatus(),
               new MedicoDTO(consulta.getMedico()),
                new PacienteDTO(consulta.getPaciente())
       );
   }
}
