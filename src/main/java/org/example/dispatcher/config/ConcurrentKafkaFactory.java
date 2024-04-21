package org.example.dispatcher.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

import java.util.Properties;

@Configuration
public class ConcurrentKafkaFactory {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String kafkaUrl;

    @Value("${spring.kafka.consumer.groupId}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String reset;

    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private String autoCommit;

    @Value("${spring.kafka.consumer.auto-commit-interval}")
    private String autoCommitInterval;

    @Value("${spring.kafka.consumer.max-poll-records}")
    private String maxPollRecords;

    @Value("${spring.kafka.consumer.max-partition-fetch-bytes}")
    private String maxPartitionFetchBytes;

    @Value("${current.threadsize}")
    private int currentThreadOfKafka;

//    @Value("${spring.kafka.consumer.kafkapassword}")
//    private String kafkaPassword;
//
//    @Value("${spring.kafka.consumer.kafkauser}")
//    private String kafkaUser;

    /*@Bean("ackConcurrencyContainerFactory")
    public ConcurrentKafkaListenerContainerFactory ackContainerFactory() {
        Properties props = new Properties();
        //Broker连接地址
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);
    }*/

    @Bean("kafkaProperties")
    public Properties getKafkaProperties(){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, reset);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringnDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringnDeserializer");
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        properties.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, maxPartitionFetchBytes);

        return properties;
    }
}
