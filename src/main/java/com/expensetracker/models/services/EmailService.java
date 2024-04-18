package com.expensetracker.models.services;

import com.expensetracker.models.EmailModel;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class EmailService {

    public void sendMail(EmailModel emailModel) {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("email.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(properties.getProperty("spring.mail.username"), properties.getProperty("spring.mail.password"));
                    }
                });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty("spring.mail.username")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailModel.getTo()));
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getBody());

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
