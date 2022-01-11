package com.ebidding.util;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;

import javax.inject.Inject;
import javax.ws.rs.GET;


public class Utility {

    @Inject
    Mailer mailer = new Mailer() {
        @Override
        public void send(Mail... mails) {

        }
    };

    @GET
    @Blocking
    public void sendEmail(String toAddress, String subject, String emailBody) {

        Mailer mailer = new Mailer() {
            @Override
            public void send(Mail... mails) {

            }
        };
        mailer.send(Mail.withText(toAddress,
                subject,
                emailBody
        )
        );

    }
}
