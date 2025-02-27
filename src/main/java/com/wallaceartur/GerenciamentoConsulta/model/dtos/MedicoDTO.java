package com.wallaceartur.GerenciamentoConsulta.model.dtos;

import com.wallaceartur.GerenciamentoConsulta.model.Medico;

public record MedicoDTO(Long id, String nome, String crm, String especialidade) {
    public MedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEspecialidade());
    }
}
