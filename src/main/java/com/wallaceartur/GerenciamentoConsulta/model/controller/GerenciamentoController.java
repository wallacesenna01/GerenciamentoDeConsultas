package com.wallaceartur.GerenciamentoConsulta.model.controller;

import com.wallaceartur.GerenciamentoConsulta.model.dtos.*;
import com.wallaceartur.GerenciamentoConsulta.services.ConsultaService;
import com.wallaceartur.GerenciamentoConsulta.services.MedicoService;
import com.wallaceartur.GerenciamentoConsulta.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    public String agendarConsulta(
            @RequestParam String datahora,
            @RequestParam Long medicoId,
            @RequestParam Long pacienteId,
            @RequestParam String email,
            RedirectAttributes redirectAttributes
    ) {
        try {

            System.out.println("Recebendo requisição -> " + datahora + " | " + medicoId + " | " + pacienteId + " | " + email);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime dataHoraFormatada = LocalDateTime.parse(datahora, formatter);

            System.out.println("Data formatada: " + dataHoraFormatada);
            
            // Agora estamos passando diretamente para o service sem a necessidade de DTO
            NovaConsultaDTO novaConsulta = new NovaConsultaDTO(dataHoraFormatada.format(formatter) , medicoId, pacienteId, email);
            System.out.println("DTO criado: " + novaConsulta);

            System.out.println("Medico ID recebido :" + medicoId);
            consultaService.agendarConsulta(novaConsulta);

            redirectAttributes.addFlashAttribute("sucess", "Consulta agendada com sucesso");
            return "redirect:/gerenciamento";
        } catch (DateTimeParseException  e) {
            redirectAttributes.addFlashAttribute("Error", "Erro ao agendar a consulta");
        }
        return "redirect:/gerenciamento";
    }


}
