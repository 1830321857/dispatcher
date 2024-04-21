package org.example.dispatcher.config;

import org.apache.tomcat.util.threads.TaskQueue;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

@SpringBootConfiguration
public class DispatcherConfig {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public LinkedBlockingDeque taskQueue() {
        return new LinkedBlockingDeque<>();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(8);
    }
}
