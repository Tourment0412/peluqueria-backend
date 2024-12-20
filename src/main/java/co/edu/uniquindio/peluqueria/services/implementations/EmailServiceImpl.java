package co.edu.uniquindio.peluqueria.services.implementations;

import co.edu.uniquindio.peluqueria.dtos.accountdto.EmailDTO;
import co.edu.uniquindio.peluqueria.services.interfaces.EmailService;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


@Service
public class EmailServiceImpl implements EmailService {


    @Value("${simplejavamail.smtp.host}")
    private String SMTP_HOST;

    @Value("${simplejavamail.smtp.port}")
    private int SMTP_PORT;

    @Value("${simplejavamail.smtp.username}")
    private String SMTP_USERNAME;

    @Value("${simplejavamail.smtp.password}")
    private String SMTP_PASSWORD;

    @Override
    public void sendEmail(EmailDTO emailDTO) throws Exception {
        Email email = EmailBuilder.startingBlank()
                .from(SMTP_USERNAME)
                .to(emailDTO.receiver())
                .withSubject(emailDTO.subject())
                //This plain text could be replaced with "withHTMLText"
                .withPlainText(emailDTO.body())
                .buildEmail();


        try (Mailer mailer = MailerBuilder
                .withSMTPServer(SMTP_HOST, SMTP_PORT, SMTP_USERNAME, SMTP_PASSWORD)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {
            mailer.sendMail(email);
        }
    }

}
