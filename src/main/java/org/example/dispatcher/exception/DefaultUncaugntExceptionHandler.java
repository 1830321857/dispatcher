package org.example.dispatcher.exception;

public class DefaultUncaugntExceptionHandler implements Thread.UncaughtExceptionHandler {
    ExceptionHandler exceptionHandler = new DefaultExceptionHandler();

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            throw new Exception(e);
        } catch (Exception e1) {
            exceptionHandler.handle(e1);
        }
    }
}
