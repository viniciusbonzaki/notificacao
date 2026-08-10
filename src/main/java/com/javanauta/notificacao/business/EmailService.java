package com.javanauta.notificacao.business;

import com.javanauta.notificacao.business.dtos.TarefaDTO;
import com.javanauta.notificacao.infrastructure.exceptions.EmailException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {
    // Injeçõa de Dependências
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    // Definir o Remetente (Quem irá enviar o Email)
    @Value("${envio.email.remetente}")
    private String remetente;
    // Definir a Identificação do Remetente
    @Value("${envio.email.nomeRemetente}")
    private String nomeRemetente;

    // Método de Envio de Email
    public void enviarEmail(TarefaDTO tarefaDTO) {
        try {
            // Criar uma Variável que será a Mensagem à ser Enviada
            MimeMessage mensagem = javaMailSender.createMimeMessage();

            // Criar um Objeto que irá construir a Mensagem
            // multipart: true -> Permite a utilização de Templates para a Estrutura da Mensagem
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mensagem, true, StandardCharsets.UTF_8.name());

            // Definir o Remetente do Email
            mimeMessageHelper.setFrom(new InternetAddress(remetente, nomeRemetente));
            // Definir o Destinatário do Email
            mimeMessageHelper.setTo(InternetAddress.parse(tarefaDTO.getEmailUsuario()));
            // Definir o Assunto do Email
            mimeMessageHelper.setSubject("Notificação de Tarefa");

            // Criar um Objeto Context para definir o Corpo do Email
            Context context = new Context();

            // Definir as Variáveis no Template "notificacao.html"
            context.setVariable("nomeTarefa", tarefaDTO.getNomeTarefa());
            context.setVariable("dataEvento", tarefaDTO.getDataEvento());
            context.setVariable("descricao", tarefaDTO.getDescricao());

            // Processar o Template setando no Corpo do Email
            String template = templateEngine.process("notificacao", context);

            // Definir o Corpo do Email com o Template
            // html:true -> Habilitar a utilizando de HTML
            mimeMessageHelper.setText(template, true);

            // Enviar a Mensagem
            javaMailSender.send(mensagem);
        }
        catch (MessagingException | UnsupportedEncodingException e) {
            throw new EmailException("Erro ao enviar o Email: ", e.getCause());
        }
    }
}
