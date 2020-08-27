package com.jwt.demo.service;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import com.jwt.demo.exception.ServiceException;
import com.jwt.demo.model.MailSendingRequest;
import com.jwt.demo.model.MailSendingResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSendingServiceImpl implements EmailSendingService {


    /* 
      you have to make sure to on this feature to send mail like 
      Allow less scure App: ON
      below is link to get that option onto your browser(google chrome) if you are signed in to mail
      https://myaccount.google.com/lesssecureapps?pli=1
    */

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSendingServiceImpl.class);
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public MailSendingResponse sendEmail(MailSendingRequest mailSendingRequest)
            throws ServiceException, MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MailSendingResponse mailSendingResponse = new MailSendingResponse();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(mailSendingRequest.getMailTo());
            mimeMessageHelper.setSubject(mailSendingRequest.getMailSubject());
            mimeMessageHelper.setText(mailSendingRequest.getMailContent(), true);
            javaMailSender.send(mimeMessage);
            mailSendingResponse.setMailSendingRequest(mailSendingRequest);
            return mailSendingResponse;
        } catch (MessagingException e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(String.format("error in sending mail EmailSendingServiceImpl %s", e.getMessage()));
            }
            e.printStackTrace();
        }
        return null;
    }
}