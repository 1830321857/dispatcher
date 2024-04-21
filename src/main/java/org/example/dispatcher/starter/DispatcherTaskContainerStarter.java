package org.example.dispatcher.starter;

import org.example.dispatcher.container.DispatcherTaskContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DispatcherTaskContainerStarter {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherTaskContainerStarter.class);

    @Autowired
    private DispatcherTaskContainer dispatcherTaskContainer;

    public void start() {
        new Thread(() -> {
            try {
                logger.info("---- dispatcherTaskContainer start process ----");
                dispatcherTaskContainer.processTasks();
            } catch (Exception e) {
                logger.error("---- dispatcherTaskContainer start fail, error:", e);
            }
        }).start();
    }
}
