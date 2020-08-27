package com.jwt.demo.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
public class BaseValidationResponse<T> implements Serializable {
    /**
     * The default serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The implementation specific object which is response object passed on to the
     * UI
     */
    private T data;
    /**
     * This is the list of Error objects.
     */
    private List<ErrorValidationBO> messageList;
    /**
     * This flag denotes if the Response is successful.
     */
    private boolean isSuccess;
    /**
     * This flag indicates if any Business error message needs to be displayed to
     * the user.
     */
    private boolean isConfirmMessage;

    private void writeObject(ObjectOutputStream o) throws IOException {
        o.defaultWriteObject();
        o.writeObject(this.data);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream o) throws IOException, ClassNotFoundException {
        o.defaultReadObject();
        this.data = (T) o.readObject();
    }
}
