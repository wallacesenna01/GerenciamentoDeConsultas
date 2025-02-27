package com.wallaceartur.GerenciamentoConsulta.model.dtos;

import java.time.LocalDateTime;

public record NovaConsultaDTO(String datahora , Long pacienteId, Long medicoId) {
}
