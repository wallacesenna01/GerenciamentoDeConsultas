package com.wallaceartur.GerenciamentoConsulta.model.repositories;

import com.wallaceartur.GerenciamentoConsulta.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
