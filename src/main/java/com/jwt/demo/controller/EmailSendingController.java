package com.jwt.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Optional;

import javax.mail.MessagingException;

import com.jwt.demo.base.model.BaseMessage;
import com.jwt.demo.base.model.BaseResponse;
import com.jwt.demo.exception.ServiceException;
import com.jwt.demo.model.MailSendingRequest;
import com.jwt.demo.model.MailSendingResponse;
import com.jwt.demo.service.EmailSendingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailSendingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSendingController.class);

    @Autowired
    private EmailSendingService emailSendingService;

    @PostMapping(value = "/sending-mail")
    public ResponseEntity<BaseResponse<MailSendingResponse>> sendEmail(
            @RequestBody MailSendingRequest mailSendingRequest)
            throws ServiceException, UnsupportedEncodingException, MessagingException {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("sending email request %s", mailSendingRequest));
        }
        MailSendingResponse mailSendingResponse = emailSendingService.sendEmail(mailSendingRequest);
        if (Optional.ofNullable(mailSendingResponse).isPresent()) {
            mailSendingResponse.setMailSendingRequest(mailSendingRequest);
            return new ResponseEntity<>(BaseResponse.<MailSendingResponse>builder().success(true)
                    .messages(Arrays.asList(
                            BaseMessage.builder().code("200").message("mail was sent successfully").type("S").build()))
                    .data(mailSendingResponse).build(), HttpStatus.OK);
        } else {
            BaseMessage error = BaseMessage.builder().code("SRVR501").message("mail was not sent").type("E").build();
            ServiceException serviceException = new ServiceException();
            serviceException.setBaseMessage(error);
            throw serviceException;
        }
    }
}