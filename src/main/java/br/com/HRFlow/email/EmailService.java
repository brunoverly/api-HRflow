package br.com.HRFlow.email;

import br.com.HRFlow.colaborador.Colaborador;
import br.com.HRFlow.colaborador.ColaboradorRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private ColaboradorRepository repository;

    public void enviarEmailBoasVindas(Colaborador colaborador) throws MessagingException {
        Context context = new Context();
        context.setVariable("nomeColaborador", colaborador.getNome());
        context.setVariable("nomeEmpresa", "HRFlow");
        context.setVariable("anoAtual", LocalDate.now().getYear());

        String html = templateEngine.process("email/boas-vindas", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(colaborador.getEmail());
        helper.setSubject("Bem-vindo ao time " + colaborador.getNome());
        helper.setText(html, true);

        mailSender.send(message);
    }

    public void enviarEmailDesativado(Colaborador colaborador, Colaborador gestor) throws MessagingException {
        Context context = new Context();

        context.setVariable("nomeGestor", gestor.getNome());
        context.setVariable("nomeColaborador", colaborador.getNome());
        context.setVariable("nomeEmpresa", "HRFlow");
        context.setVariable("anoAtual", LocalDate.now().getYear());


        String html = templateEngine.process("email/conta-encerrada", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(gestor.getEmail());
        helper.setSubject("Conta desativada");
        helper.setText(html, true);

        mailSender.send(message);
    }

    public void enviarEmailAtualizado(Colaborador colaborador) throws MessagingException {
        Context context = new Context();

        context.setVariable("nomeColaborador", colaborador.getNome());
        context.setVariable("linkConfirmacao", "");
        context.setVariable("nomeEmpresa", "HRFlow");
        context.setVariable("anoAtual", LocalDate.now().getYear());


        String html = templateEngine.process("email/confirmar-atualizacao", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(colaborador.getEmail());
        helper.setSubject("Atualização de conta");
        helper.setText(html, true);

        mailSender.send(message);
    }
}
