package com.wallaceartur.GerenciamentoConsulta.model.dtos;

import java.time.LocalDateTime;

public record AtualizarConsultaDTO(Long id, LocalDateTime datahora, Long medicoId) {
}
