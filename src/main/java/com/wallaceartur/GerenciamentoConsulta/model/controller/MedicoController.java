package com.wallaceartur.GerenciamentoConsulta.model.controller;

import com.wallaceartur.GerenciamentoConsulta.model.dtos.AtualizarMedicoDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.MedicoDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.NovoMedicoDTO;
import com.wallaceartur.GerenciamentoConsulta.services.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listar() {
        return ResponseEntity.ok(medicoService.listar());
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> create(@RequestBody @Valid NovoMedicoDTO dto) {
        MedicoDTO medicoDTO = medicoService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> findById(@PathVariable Long id) {
        MedicoDTO medicoDTO = medicoService.findById(id);
        return ResponseEntity.ok(medicoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> atualizar(@RequestBody @Valid AtualizarMedicoDTO dto) {
        MedicoDTO medicoDTO = medicoService.atualizar(dto);
        return ResponseEntity.ok(medicoDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
