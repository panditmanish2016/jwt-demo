package com.jwt.demo.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.jwt.demo.constant.ErrorType;
import com.jwt.demo.model.BaseValidationResponse;
import com.jwt.demo.model.ErrorValidationBO;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalValidatorExceptionHandler<T> extends ResponseEntityExceptionHandler {

    @Autowired
    private BaseValidationResponse<T> response;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info("handleMethodArgumentNotValid........." + ex.getClass().getName());
        logger.debug(request);
        final List<ErrorValidationBO> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            ErrorValidationBO errorBO = constructErrorObject(ErrorType.ERROR.toString(), null,
                    error.getDefaultMessage(), error.getField());
            errors.add(errorBO);
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            ErrorValidationBO errorBO = constructErrorObject(ErrorType.ERROR.toString(), error.getObjectName(),
                    error.getDefaultMessage(), null);
            errors.add(errorBO);
        }
        this.response.setMessageList(errors);
        return new ResponseEntity<>(this.response, new HttpHeaders(), HttpStatus.OK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.method.annotation.
     * ResponseEntityExceptionHandler#handleBindException(org.springframework.
     * validation.BindException, org.springframework.http.HttpHeaders,
     * org.springframework.http.HttpStatus,
     * org.springframework.web.context.request.WebRequest)
     * 
     * HttpStatus = 400
     */
    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
            final HttpStatus status, final WebRequest request) {
        logger.info("handleBindException....." + ex.getClass().getName());
        logger.debug(request);
        final List<ErrorValidationBO> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            sb.append(error.getField()).append(": ").append(error.getDefaultMessage());
            ErrorValidationBO errorBO = constructErrorObject(ErrorType.ERROR.toString(), null, sb.toString(),
                    error.getField());
            errors.add(errorBO);
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            StringBuilder sb = new StringBuilder();
            sb.append(error.getObjectName()).append(": ").append(error.getDefaultMessage());
            ErrorValidationBO errorBO = constructErrorObject(ErrorType.ERROR.toString(), error.getObjectName(),
                    sb.toString(), null);
            errors.add(errorBO);
        }
        this.response.setMessageList(errors);
        return handleExceptionInternal(ex, this.response, headers, HttpStatus.BAD_REQUEST, request);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.method.annotation.
     * ResponseEntityExceptionHandler#handleTypeMismatch(org.springframework.beans.
     * TypeMismatchException, org.springframework.http.HttpHeaders,
     * org.springframework.http.HttpStatus,
     * org.springframework.web.context.request.WebRequest)
     * 
     * HttpStatus = 400
     */
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
            final HttpStatus status, final WebRequest request) {
        logger.info("handleTypeMismatch..........."+ex.getClass().getName());
        logger.debug(request);
        final List<ErrorValidationBO> errors = new ArrayList<>();
        final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type "
                + ex.getRequiredType();
        ErrorValidationBO errorBO = constructErrorObject(ErrorType.ERROR.toString(), "TYPE_MISMATCH", error, null);
        errors.add(errorBO);
        this.response.setMessageList(errors);
        return new ResponseEntity<>(this.response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.method.annotation.
     * ResponseEntityExceptionHandler#handleMissingServletRequestPart(org.
     * springframework.web.multipart.support.MissingServletRequestPartException,
     * org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus,
     * org.springframework.web.context.request.WebRequest)
     * 
     * HttpStatus = 400
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info("handleMissingServletRequestPart............."+ex.getClass().getName());
        logger.debug(request);
        final List<ErrorValidationBO> errors = new ArrayList<>();
        final String error = ex.getRequestPartName() + " part is missing";
        ErrorValidationBO errorBO = constructErrorObject(ErrorType.ERROR.toString(), "MISSING_SERVLET_REQUEST_PART",
                error, null);
        errors.add(errorBO);
        this.response.setMessageList(errors);
        return new ResponseEntity<>(this.response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.method.annotation.
     * ResponseEntityExceptionHandler#handleMissingServletRequestParameter(org.
     * springframework.web.bind.MissingServletRequestParameterException,
     * org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus,
     * org.springframework.web.context.request.WebRequest)
     * 
     * HttpStatus = 400
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status,
            final WebRequest request) {
        logger.info("handleMissingServletRequestParameter......." + ex.getClass().getName());
        logger.debug(request);
        final List<ErrorValidationBO> errors = new ArrayList<>();
        final String error = ex.getParameterName() + " parameter is missing";
        ErrorValidationBO errorBO = constructErrorObject(ErrorType.ERROR.toString(), "MISSING_SERVLET_REQUEST_PARAM",
                error, null);
        errors.add(errorBO);
        this.response.setMessageList(errors);
        return new ResponseEntity<>(this.response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * HttpStatus = 400
     * 
     * @param ex
     * @param request, not null
     * @return
     */
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
            final WebRequest request) {
        logger.info("handleMethodArgumentTypeMismatch............." + ex.getClass().getName());
        logger.debug(request);
        final List<ErrorValidationBO> errors = new ArrayList<>();
        final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        ErrorValidationBO errorBO = constructErrorObject(ErrorType.ERROR.toString(), "METHOD_ARGUMENT_MISMATCH", error,
                null);
        errors.add(errorBO);
        this.response.setMessageList(errors);
        return new ResponseEntity<>(this.response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex,
            final WebRequest request) {
        logger.info("handleConstraintViolation............." + ex.getClass().getName());
        logger.debug(request);
        final List<ErrorValidationBO> errors = new ArrayList<>();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            StringBuilder sb = new StringBuilder();
            sb.append(violation.getRootBeanClass().getName()).append(" ").append(violation.getPropertyPath())
                    .append(": ").append(violation.getMessage());
            ErrorValidationBO errorBO = constructErrorObject(ErrorType.ERROR.toString(),
                    violation.getRootBeanClass().getName(), sb.toString(), null);
            errors.add(errorBO);
            this.response.setMessageList(errors);
        }
        return new ResponseEntity<>(this.response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private static ErrorValidationBO constructErrorObject(String errorType, String errorCode, String errorDesc,
            String propertyName) {
        ErrorValidationBO errorBO = new ErrorValidationBO();
        errorBO.setType(errorType);
        errorBO.setCode(errorCode);
        errorBO.setMessage(errorDesc);
        errorBO.setReferenceId(propertyName);
        return errorBO;
    }

}
