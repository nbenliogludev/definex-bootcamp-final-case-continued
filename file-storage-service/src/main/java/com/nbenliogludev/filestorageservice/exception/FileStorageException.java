package com.nbenliogludev.filestorageservice.exception;

/**
 * @author nbenliogludev
 */
public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super("FileStorageException: " + message);
    }

    public FileStorageException(String message, Throwable cause) {
        super("FileStorageException: " + message, cause);
    }
}
