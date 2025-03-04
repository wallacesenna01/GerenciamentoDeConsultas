package com.wallaceartur.GerenciamentoConsulta.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NovaConsultaNotificacaoDTO(
        String emailPaciente,
        String nomePaciente,
        String nomeMedico,
        String datahora
) {}


