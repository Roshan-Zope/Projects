package com.expensetracker.models.services;

import com.expensetracker.models.EmailModel;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;
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
        String smtpServer = properties.getProperty("spring.mail.host");
        int smtpPort = Integer.valueOf(properties.getProperty("spring.mail.port"));
        String username = properties.getProperty("spring.mail.username");
        String password = properties.getProperty("spring.mail.password");

        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName(smtpServer);
        htmlEmail.setSmtpPort(smtpPort);
        htmlEmail.setAuthenticator(new DefaultAuthenticator(username, password));
        htmlEmail.setStartTLSEnabled(true);
        try {
            htmlEmail.setFrom(username);
            htmlEmail.addTo(emailModel.getTo());
            htmlEmail.setSubject(emailModel.getSubject());
            htmlEmail.setHtmlMsg("<html><body>"+emailModel.getBody()+"</body></html>");
            htmlEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
