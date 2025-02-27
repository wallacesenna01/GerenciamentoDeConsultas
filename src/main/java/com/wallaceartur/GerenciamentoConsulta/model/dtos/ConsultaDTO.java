package com.wallaceartur.GerenciamentoConsulta.model.dtos;

import com.wallaceartur.GerenciamentoConsulta.enums.StatusConsulta;
import com.wallaceartur.GerenciamentoConsulta.model.Consulta;

import java.time.LocalDateTime;

public record ConsultaDTO(Long id, LocalDateTime dateHora, StatusConsulta status, MedicoDTO medicoDTO, PacienteDTO pacienteDTO) {

   public ConsultaDTO(Consulta consulta) {
       this(consulta.getId(), consulta.getDataHora(), consulta.getStatus(),
               new MedicoDTO(consulta.getMedico()),
                new PacienteDTO(consulta.getPaciente())
       );
   }
}
