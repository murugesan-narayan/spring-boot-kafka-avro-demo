package com.muru.kafka.avro.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@Configuration
@Slf4j
public class ProducerConfig {
    @Autowired
    private ProducerFactory<GenericRecord, GenericRecord> producerFactory;

    public DefaultKafkaProducerFactory<GenericRecord, GenericRecord> defaultKafkaProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerFactory.getConfigurationProperties());
    }

    @Bean
    public KafkaTemplate<GenericRecord, GenericRecord> kafkaTemplate() {
        return new KafkaTemplate<>(defaultKafkaProducerFactory());

    }
}
