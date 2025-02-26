package com.wallaceartur.GerenciamentoConsulta.model.controller;

import com.wallaceartur.GerenciamentoConsulta.model.dtos.NovoPacienteDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.PacienteDTO;
import com.wallaceartur.GerenciamentoConsulta.services.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> criarPaciente(@RequestBody NovoPacienteDTO novoPacienteDTO) {
        PacienteDTO novoPaciente = pacienteService.novoPacienteDto(novoPacienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }
}
