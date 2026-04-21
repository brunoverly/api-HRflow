package br.com.HRFlow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/teste")
public class EmailTester {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("")
    public String enviarEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("teste@email.com"); // qualquer email
        message.setSubject("Teste Spring Mail");
        message.setText("Funcionou 🚀");

        mailSender.send(message);

        return "Email enviado!";
    }
}
