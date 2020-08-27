package com.jwt.demo.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.jwt.demo.exception.ServiceException;
import com.jwt.demo.model.MailSendingRequest;
import com.jwt.demo.model.MailSendingResponse;

public interface EmailSendingService {

    public MailSendingResponse sendEmail(MailSendingRequest mailSendingRequest)
            throws ServiceException, MessagingException, UnsupportedEncodingException;

}