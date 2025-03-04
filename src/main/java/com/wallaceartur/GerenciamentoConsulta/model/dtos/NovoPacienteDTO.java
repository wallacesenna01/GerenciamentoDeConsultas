package com.wallaceartur.GerenciamentoConsulta.model.dtos;

import java.time.LocalDate;

public record NovoPacienteDTO(String nome, String cpf, LocalDate dataNascimento, String telefone, String email) {
}



