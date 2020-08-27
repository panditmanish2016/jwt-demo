package com.jwt.demo.exception;

import java.util.Arrays;

import com.jwt.demo.base.model.BaseMessage;
import com.jwt.demo.base.model.BaseModel;
import com.jwt.demo.base.model.BaseResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ServiceExceptionHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionHandler.class);

    @ExceptionHandler({ ServiceException.class })
    private ResponseEntity<BaseResponse<BaseModel>> handleServiceException(ServiceException e) {
        LOGGER.trace("Exception Handler ServiceException ", e);
        return new ResponseEntity<>(
                BaseResponse.<BaseModel>builder().success(false).messages(Arrays.asList(e.getBaseMessage())).build(),
                HttpStatus.OK);
    }

    @ExceptionHandler({ Throwable.class })
    private ResponseEntity<BaseResponse<BaseModel>> handleException(Exception exceptino, WebRequest reqeust) {
        LOGGER.trace("Exception Handler Throwable ", exceptino);
        return new ResponseEntity<>(BaseResponse.<BaseModel>builder().success(false)
                .messages(
                        Arrays.asList(BaseMessage.builder().code("SRVR500").message("Server Error").type("E").build()))
                .build(), HttpStatus.OK);
    }

}
