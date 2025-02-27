package com.wallaceartur.GerenciamentoConsulta.model.controller;

import com.wallaceartur.GerenciamentoConsulta.model.dtos.AtualizarPacienteDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.NovoPacienteDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.PacienteDTO;
import com.wallaceartur.GerenciamentoConsulta.services.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> update(@PathVariable Long id, @RequestBody AtualizarPacienteDTO dto) {
        if (!id.equals(dto.id())) {
            return ResponseEntity.badRequest().build();
        }
        PacienteDTO pacienteAtualizado = pacienteService.updatePacienteDTO(dto); {
            return ResponseEntity.ok(pacienteAtualizado);
        }
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> findAll() {
        List<PacienteDTO> pacientes = pacienteService.findAll();
        return ResponseEntity.ok(pacientes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
