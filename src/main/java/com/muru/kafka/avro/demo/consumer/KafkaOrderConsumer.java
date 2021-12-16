package com.muru.kafka.avro.demo.consumer;

import com.muru.kafka.avro.demo.model.Order;
import com.muru.kafka.avro.demo.model.OrderKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaOrderConsumer {
    @KafkaListener( topics = "${kafka.topic}", containerFactory = "listenerContainerFactory")
    public void consume(ConsumerRecord<OrderKey, Order> consumerRecord) {
        log.info("Received Message Key = {}", consumerRecord.key());
        log.info("Received Message Value = {}", consumerRecord.value());
    }

}
