package com.wallaceartur.GerenciamentoConsulta.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.NovaConsultaNotificacaoDTO;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "fila_notificacao_paciente")
    public void enviarEmail(String email) {
        try {

            System.out.println("📩 Mensagem recebida do RabbitMQ: " + email);
            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true);
            helper.setTo(email);
            helper.setSubject("Cadastro realizado com sucesso");


            String conteudoEmail = "<h1>Olá, " + " " + "! 👋</h1>"
                    + "<p>É um prazer tê-lo(a) conosco. Seu cadastro foi realizado com sucesso!</p>"
                    + "<p>Agora você pode agendar consultas de forma rápida e prática.</p>"
                    + "<p>Se precisar de algo, estamos à disposição.</p>"
                    + "<br><p>Atenciosamente,</p><p><strong>Equipe de Atendimento</strong></p>";



            helper.setText(conteudoEmail, true);
            helper.setFrom("arthursenna442@gmail.com");

            mailSender.send(mensagem);
            System.out.println("E-mail enviado para: " + email);
        }catch (MessagingException e) {
            System.out.println("erro ao enviar e-mail" + e.getMessage());
        }

    }

    @RabbitListener(queues = "fila_notificacao_consulta")
    public void enviarEmailConsulta(String email) {
        try {
            System.out.println("📩 Mensagem recebida do RabbitMQ: " + email);

            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true);
            helper.setTo(email);
            helper.setSubject("📅 Sua consulta foi confirmada!");

            String conteudoEmail = "<h1>🩺 Olá, seja bem-vindo(a)! 👋</h1>"
                    + "<p>É com grande satisfação que informamos que sua consulta foi agendada com sucesso! 🎉</p>"
                    + "<p>Fique tranquilo(a), nossa equipe está preparada para oferecer o melhor atendimento.</p>"
                    + "<p>📅 <strong>Data e horário:</strong> Conforme combinado</p>"
                    + "<p>🏥 <strong>Local:</strong> Nossa unidade mais próxima</p>"
                    + "<p>Se precisar de algo antes do seu atendimento, não hesite em nos procurar!</p>"
                    + "<br><p>Atenciosamente,</p><p><strong>Equipe de Atendimento</strong></p>";

            helper.setText(conteudoEmail, true);
            helper.setFrom("arthursenna442@gmail.com");

            mailSender.send(mensagem);
            System.out.println("✅ E-mail de confirmação enviado para: " + email);

        } catch (MessagingException e) {
            System.out.println("❌ Erro ao enviar e-mail: " + e.getMessage());
        }
    }


}
