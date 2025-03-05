package com.wallaceartur.GerenciamentoConsulta.services;

import com.wallaceartur.GerenciamentoConsulta.model.Medico;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.AtualizarMedicoDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.MedicoDTO;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.NovoMedicoDTO;
import com.wallaceartur.GerenciamentoConsulta.model.repositories.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public MedicoDTO cadastrar(NovoMedicoDTO dto) {
        if(medicoRepository.existsByCrm(dto.crm())){
            throw new IllegalArgumentException("CRM já cadastrado");
        }

        Medico medico = new Medico();
        medico.setNome(dto.nome());
        medico.setCrm(dto.crm());
        medico.setEspecialidade(dto.especialidade());

        Medico salvo = medicoRepository.save(medico);
        return new MedicoDTO(salvo);
    }

    public MedicoDTO findById(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new  EntityNotFoundException("Medico não encontrado"));
        return new MedicoDTO(medico);
    }

    public MedicoDTO atualizar(AtualizarMedicoDTO dto) {
        Medico medico = medicoRepository.findById(dto.id())
                .orElseThrow(() -> new IllegalArgumentException("Medico não encontrado"));
        medico.setNome(dto.nome());
        medico.setEspecialidade(dto.especialidade());

        Medico atualizado = medicoRepository.save(medico);
        return new MedicoDTO(atualizado);
    }

    public List<MedicoDTO> listar() {
        return medicoRepository.findAll().stream()
                .map(MedicoDTO::new).toList();
    }

    public void deletar(Long id) {
        if(!medicoRepository.existsById(id)){
            throw new IllegalArgumentException("Medico nao encontrado");
        }
        medicoRepository.deleteById(id);
    }

}
