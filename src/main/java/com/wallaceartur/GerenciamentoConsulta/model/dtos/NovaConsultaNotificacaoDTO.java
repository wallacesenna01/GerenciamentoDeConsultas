package com.wallaceartur.GerenciamentoConsulta.model.dtos;

public record NovaConsultaNotificacaoDTO(
        String emailPaciente,
        String nomePaciente,
        String nomeMedico,
        String datahora
) {}


