package com.jwt.demo.exception;

import com.jwt.demo.constant.ErrorType;

public class CustomValidationException extends Exception {

    /**
     * Default serial version ID
     */
    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_ERROR_TYPE = "APP";

    public static final String DEFAULT_CUSTOM_ERROR = "ORA";

    private String errorType;

    private String customError;

    private String errorCode;

    private String propertyName;

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * Default No arg Constructor
     */
    public CustomValidationException() {
        super();
        this.errorType = ErrorType.APP.toString();
        this.customError = DEFAULT_CUSTOM_ERROR;
    }

    public CustomValidationException(String errorCode, String propertyName, String errorType) {
        this.errorCode = errorCode;
        this.propertyName = propertyName;
        this.errorType = ErrorType.fromValue(errorType).toString();
    }

    public CustomValidationException(String errorCode, String propertyName, ErrorType errorType) {
        this.errorCode = errorCode;
        this.propertyName = propertyName;
        this.errorType = errorType.toString();
    }

    public String getErrorType() {
        return this.errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = ErrorType.fromValue(errorType).toString();
    }

    /**
     * This method return the ErrorType enum value
     * 
     * @return
     */
    public ErrorType getEType() {
        return ErrorType.fromValue(this.errorType);
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType.toString();
    }

    public String getCustomError() {
        return this.customError;
    }

    public void setCustomError(String customError) {
        this.customError = customError;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}