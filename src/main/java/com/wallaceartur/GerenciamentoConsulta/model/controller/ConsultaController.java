package com.wallaceartur.GerenciamentoConsulta.model.controller;

import com.wallaceartur.GerenciamentoConsulta.model.dtos.AtualizarConsultaDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.ConsultaDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.NovaConsultaDTO;
import com.wallaceartur.GerenciamentoConsulta.services.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> findAll(){
        List<ConsultaDTO> consultaDTOS = consultaService.buscarTodos();
        return ResponseEntity.ok(consultaDTOS);
    }

    @PostMapping
    public ResponseEntity<ConsultaDTO> criarConsulta(@RequestBody @Valid NovaConsultaDTO novaConsultaDTO) {
        ConsultaDTO consultaDTO = consultaService.agendarConsulta(novaConsultaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> acharPorId(@PathVariable Long id) {
        ConsultaDTO consultaDTO = consultaService.buscarPorId(id);
        return ResponseEntity.ok(consultaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> atualizar( @RequestBody @Valid AtualizarConsultaDTO dto) {
        ConsultaDTO consultaDTO = consultaService.atualizar( dto);
        return ResponseEntity.ok(consultaDTO);

    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        consultaService.cancelarConsulta(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/atualizar-status")
    public ResponseEntity<Void> atualizaConsultaStatus() {
        consultaService.atualizarConsultaVencidas();
        return ResponseEntity.noContent().build();
    }
}
