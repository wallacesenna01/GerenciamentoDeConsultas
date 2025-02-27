package com.wallaceartur.GerenciamentoConsulta.model.dtos;

import java.time.LocalDateTime;

public record NovaConsultaDTO(LocalDateTime datahora, Long pacienteId, Long medicoId) {
}
