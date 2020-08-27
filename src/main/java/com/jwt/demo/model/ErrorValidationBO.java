package com.jwt.demo.model;





import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
public class ErrorValidationBO {
    /**
     * This property contains the Error Type.
     */
    private String type;
    /**
     * This property contains the Error Code.
     */
    private String code;
    /**
     * This property contains the Error Description.
     */
    private String message;
    /**
     * This property contains values to signify Error Level. S - Stop; W - Warn; I -
     * Info; C - Confirm
     */
    private String errorLevel;
    /**
     * This property contains the Locale.
     */
    private String locale;
    /**
     * This property contains the Reference ID.
     */
    private String referenceId;

    /**
     * This method adds the Error Messages related to a specific property.
     * 
     * @param propertyName, not null
     * @param errorCode,    not null
     */
    public void addError(String propertyName, String errorCode) {
        super.toString();
        this.referenceId = propertyName;
        this.code = errorCode;
    }

    /**
     * This method adds the Warning Messages related to a specific property.
     * 
     * @param propertyName, not null
     * @param errorCode,    not null
     */
    public void addWarning(String warningPropertyName, String errorCode) {
        super.toString();
        this.referenceId = warningPropertyName;
        this.code = errorCode;
    }

    /**
     * This method adds the Notification messages for a property.
     * 
     * @param propertyName, not null
     * @param errorCode,    not null
     */
    public void addNotification(String errorCode) {
        super.toString();
        this.code = errorCode;
    }
}



