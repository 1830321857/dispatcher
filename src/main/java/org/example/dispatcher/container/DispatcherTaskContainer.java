package org.example.dispatcher.container;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.dispatcher.exception.DefaultUncaugntExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;

public class DispatcherTaskContainer {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherTaskContainer.class);

    @Autowired
    private LinkedBlockingDeque taskQueue;

    @Autowired
    private ExecutorService executorService;

    public void processTasks() throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(new DefaultUncaugntExceptionHandler());

        while (true) {
            ConsumerRecord<String, String> record = (ConsumerRecord<String, String>) taskQueue.take();
            if ("stop".equals(record.topic())) {
                logger.info("stop, taskQueue size:{}", taskQueue.size());
                break;
            }
            executorService.execute(new SubThread(record));
            Thread.sleep(2);
        }
    }

    private class SubThread implements Runnable {
        private ConsumerRecord<String, String> record;

        public SubThread(ConsumerRecord<String, String> record) {
            this.record = record;
        }


        @Override
        public void run() {

        }
    }
}
