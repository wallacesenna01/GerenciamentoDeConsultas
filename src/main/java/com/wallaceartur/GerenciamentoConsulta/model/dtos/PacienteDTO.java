package com.wallaceartur.GerenciamentoConsulta.model.dtos;

import com.wallaceartur.GerenciamentoConsulta.model.Paciente;

public record PacienteDTO(Long id, String nome, String email, String telefone) {
    public PacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone());
    }
}

