package com.jwt.demo.exception;

import com.jwt.demo.base.model.BaseMessage;

public class ServiceException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private  BaseMessage baseMessage;

    public BaseMessage getBaseMessage() {
        return baseMessage;
    }

    public void setBaseMessage(BaseMessage baseMessage) {
        this.baseMessage = baseMessage;
    }

}
