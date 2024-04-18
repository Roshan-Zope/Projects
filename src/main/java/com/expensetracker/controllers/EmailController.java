package com.expensetracker.controllers;

import com.expensetracker.models.EmailModel;
import com.expensetracker.models.services.EmailService;

public class EmailController {
    private final EmailService emailService = new EmailService();

    public void sendEmail(String to, String subject, String body) {
        EmailModel emailModel = new EmailModel();
        emailModel.setTo(to);
        emailModel.setSubject(subject);
        emailModel.setBody(body);
        emailService.sendMail(emailModel);
    }
}
