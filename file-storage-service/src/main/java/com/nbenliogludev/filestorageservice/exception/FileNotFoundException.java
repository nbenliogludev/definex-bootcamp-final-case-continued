package com.nbenliogludev.filestorageservice.exception;

/**
 * @author nbenliogludev
 */
public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String fileName) {
        super("FileNotFoundException: File '" + fileName + "' not found.");
    }
}
