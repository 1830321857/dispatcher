package org.example.dispatcher.exception;

import org.example.dispatcher.container.DispatcherTaskContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultExceptionHandler implements ExceptionHandler{
    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @Override
    public void handle(Throwable e) {
        logger.error("handle error:", e);
    }
}
