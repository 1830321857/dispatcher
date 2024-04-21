package org.example.dispatcher;

import org.example.dispatcher.service.KafkaReceiveService;
import org.example.dispatcher.starter.DispatcherTaskContainerStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class StartApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StartApplication.class);

        DispatcherTaskContainerStarter starter = context.getBean(DispatcherTaskContainerStarter.class);
        starter.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            KafkaReceiveService kafkaReceiveService = context.getBean(KafkaReceiveService.class);
            kafkaReceiveService.stop();
        }, "dispatchertask-shutdown-hook"));
    }
}
