package com.wallaceartur.GerenciamentoConsulta.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    @Autowired
    private JavaMailSender mailSender;

    @RabbitListener(queues = "fila_notificacao_paciente")
    public void enviarEmail(String email) {
        try {

            System.out.println("📩 Mensagem recebida do RabbitMQ: " + email);
            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true);
            helper.setTo(email);
            helper.setSubject("Cadastro realizado com sucesso");
            helper.setText("<h1>Bem-vindo ao sistema de consultas!</h1><p>Seu cadastro foi realizado com sucesso.</p>", true);
            helper.setFrom("arthursenna442@gmail.com");

            mailSender.send(mensagem);
            System.out.println("E-mail enviado para: " + email);
        }catch (MessagingException e) {
            System.out.println("erro ao enviar e-mail" + e.getMessage());
        }
    }
}
