package com.muru.kafka.avro.demo.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkaOrderProducerTest {

    @Autowired
    private KafkaOrderProducer producer;

    @Test
    void produce() throws Exception{
        producer.produce();
        Thread.sleep(60000L);
    }
}