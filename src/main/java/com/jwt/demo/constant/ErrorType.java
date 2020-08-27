package com.jwt.demo.constant;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ErrorType {

    ERROR("E"), WARNING("W"), NOTIFICATION("N"), STOP("S"), APP("APP");

    private final String type;

    private ErrorType(String value) {
        this.type = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return String.valueOf(this.type);
    }

    /**
     * Returns the ENUM for the specified String.
     * 
     * @param value
     * @return
     */
    @JsonCreator
    public static ErrorType fromValue(String value) {
        for (ErrorType eType : values()) {
            if (eType.type.equalsIgnoreCase(value)) {
                return eType;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }

}