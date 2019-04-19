package com.hosmos.learning.common.utils.exception;

public class ControllerException extends Exception {
    public ControllerException() {}

    public ControllerException(String arg0) {
        super(arg0);
    }

    public ControllerException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ControllerException(Throwable arg0) {
        super(arg0);
    }
}
