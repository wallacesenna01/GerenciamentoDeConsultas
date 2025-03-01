package com.wallaceartur.GerenciamentoConsulta.model.repositories;

import com.wallaceartur.GerenciamentoConsulta.enums.StatusConsulta;
import com.wallaceartur.GerenciamentoConsulta.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByStatusAndDataHoraBefore(StatusConsulta status, LocalDateTime dataHora);

}
