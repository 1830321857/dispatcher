package org.example.dispatcher.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.dispatcher.container.DispatcherTaskContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class KafkaReceiveService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaReceiveService.class);

    @Resource(name = "kafkaProperties")
    Properties kafkaProperties;

    @Value("${spring.kafka.consumer.topic}")
    private String topic;

    @Autowired
    private LinkedBlockingDeque taskQueue;

    public void onMessage() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(kafkaProperties);
        List<String> topics = new ArrayList<>();
        topics.add(topic);
        consumer.subscribe(topics);

        while (!Thread.currentThread().isInterrupted()) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            if (records.count() == 0) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    logger.error("跳过sleep", e);
                }
                continue;
            }

            for (ConsumerRecord<String, String> record : records) {

            }
        }

    }

    /**
     * 在JVM退出时调用
     */
    public void stop() {
        try {
            ConsumerRecord<String, String> poison = new ConsumerRecord<>("stop", 0, 0, "stop", "stop");
            taskQueue.put(poison);
        } catch (Exception e) {
            logger.error("毒丸关闭异常，error:", e);
        }
    }
}
