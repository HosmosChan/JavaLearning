package com.hosmos.learning.common.utils.exception;

public class ServiceException extends Exception {
    public ServiceException() {}

    public ServiceException(String arg0) {
        super(arg0);
    }

    public ServiceException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ServiceException(Throwable arg0) {
        super(arg0);
    }
}
