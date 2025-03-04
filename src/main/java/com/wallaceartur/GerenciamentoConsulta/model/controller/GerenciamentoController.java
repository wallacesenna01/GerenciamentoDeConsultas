package com.wallaceartur.GerenciamentoConsulta.model.controller;

import com.wallaceartur.GerenciamentoConsulta.model.dtos.*;
import com.wallaceartur.GerenciamentoConsulta.services.ConsultaService;
import com.wallaceartur.GerenciamentoConsulta.services.MedicoService;
import com.wallaceartur.GerenciamentoConsulta.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class GerenciamentoController {



    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/gerenciamento")
    public String mostrarFormulario(Model model) {
        List<MedicoDTO>  medicos = medicoService.listar();
        model.addAttribute("medicos", medicos);
        return "gerenciamento-form";
    }

    @PostMapping("/marcar/pacientes")
    public String criarPaciente(
            @RequestParam String nome,
            @RequestParam String cpf,
            @RequestParam String dataNascimento,
            @RequestParam String telefone,
            @RequestParam String email,
            RedirectAttributes redirectAttributes
    ) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate nascimento = LocalDate.parse(dataNascimento,formatter);
            NovoPacienteDTO pacienteDTO = new NovoPacienteDTO(nome, cpf, nascimento, telefone, email);
            pacienteService.novoPacienteDto(pacienteDTO);
            redirectAttributes.addFlashAttribute("sucess", "Paciente cadastrado com sucesso");
            return "redirect:/gerenciamento";
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("Error", "erro ao cadastrar o paciente");
        }
        return "redirect:/gerenciamento";
    }


    @PostMapping("/marcar/consulta")
    public String criarConsulta(@ModelAttribute NovaConsultaDTO consultaDTO, RedirectAttributes redirectAttributes) {
        consultaService.agendarConsulta(consultaDTO);
        redirectAttributes.addFlashAttribute("sucess", "Consulta Agendada com sucesso");
        return "redirect:/gerenciamento";
    }
}
