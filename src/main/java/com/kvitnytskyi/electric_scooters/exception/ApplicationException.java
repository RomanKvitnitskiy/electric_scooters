package com.kvitnytskyi.electric_scooters.exception;


public class ApplicationException extends RuntimeException{

    private StringBuffer message;
    private StringBuffer log;
    private Class throwsException;

    public ApplicationException() {
        this.message = new StringBuffer();
        this.log = new StringBuffer();
    }

    public ApplicationException(String message) {
        super(message);
        this.message = new StringBuffer(message);
        this.log = new StringBuffer();
    }

    public ApplicationException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = new StringBuffer(message);
        this.log = new StringBuffer();
    }

    public ApplicationException addMessage(String message) {
        this.message.append(message);
        return this;
    }

    public ApplicationException addLog(String log) {
        this.log.append(log);
        return this;
    }

    public Class getClassThrowsException() {
        return throwsException;
    }

    public ApplicationException setClassThrowsException(Class classThrowsException) {
        this.throwsException = classThrowsException;
        return this;
    }

    @Override
    public String getMessage() {
        return message.toString();
    }

    public String getLog() {
        return log.toString();
    }
}
