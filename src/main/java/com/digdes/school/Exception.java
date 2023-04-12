package com.digdes.school;

public class Exception extends RuntimeException {

    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

    public Exception(String errorMassage) {
        super(errorMassage);
    }

    public Exception() { super(); }
}
