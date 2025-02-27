package com.wallaceartur.GerenciamentoConsulta.model.repositories;

import com.wallaceartur.GerenciamentoConsulta.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long > {
    boolean existsByCrm(String crm);
}
