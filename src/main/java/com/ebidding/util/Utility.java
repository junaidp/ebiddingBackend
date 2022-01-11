package com.ebidding.util;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;

@ApplicationScoped
public class Utility {

    @Inject
    Mailer mailer;

    @Blocking
    public String sendEmail(String toAddress, String subject, String emailBody) {


        Mail mail = Mail.withText(toAddress,subject,emailBody);
        mailer.send(mail);
        return "mail sent";


    }
}
