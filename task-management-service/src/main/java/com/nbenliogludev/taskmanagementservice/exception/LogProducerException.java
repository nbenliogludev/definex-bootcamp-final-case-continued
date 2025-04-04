package com.nbenliogludev.taskmanagementservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nbenliogludev
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class LogProducerException extends RuntimeException {

    public static final String ERROR_MESSAGE = "An error occurred while producing the log";

    public LogProducerException() {
        super(ERROR_MESSAGE);
    }
}
