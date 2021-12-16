package com.muru.kafka.avro.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@Slf4j
public class ConsumerConfig {
    @Autowired
    private ConsumerFactory<GenericRecord, GenericRecord> consumerFactory;

    public DefaultKafkaConsumerFactory<GenericRecord, GenericRecord> defaultKafkaConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerFactory.getConfigurationProperties());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<GenericRecord, GenericRecord> listenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<GenericRecord, GenericRecord> concurrentFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        concurrentFactory.setConsumerFactory(defaultKafkaConsumerFactory());
        return concurrentFactory;
    }
}
