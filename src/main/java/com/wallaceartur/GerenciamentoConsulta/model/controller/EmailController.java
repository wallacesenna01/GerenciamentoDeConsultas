package com.wallaceartur.GerenciamentoConsulta.model.controller;

import com.wallaceartur.GerenciamentoConsulta.services.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping("/test")
    public ResponseEntity<String> testarEnvio(){
        notificacaoService.enviarEmail("arthursenna442@gmail.com");
        return ResponseEntity.ok("Email de teste enviado !");
    }
}
