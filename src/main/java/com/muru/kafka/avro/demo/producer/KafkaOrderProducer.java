package com.muru.kafka.avro.demo.producer;

import com.muru.kafka.avro.demo.model.Order;
import com.muru.kafka.avro.demo.model.OrderKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class KafkaOrderProducer {
    @Autowired
    private KafkaTemplate<GenericRecord, GenericRecord> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topicName;

    public void produce() {
        Order order = Order.newBuilder()
                .setOrderId("OId234")
                .setCustomerId("CId432")
                .setSupplierId("SId543")
                .setItems(4)
                .setFirstName("Sam")
                .setLastName("Kane")
                .setPrice(178.5f)
                .setWeight(12f)
                .setPromotionEmail(false)
                .build();

        OrderKey orderkey = OrderKey.newBuilder()
                .setOrderKey("CId432-OId234")
                .build();


        ListenableFuture<SendResult<GenericRecord, GenericRecord>> future
                = kafkaTemplate.send(topicName, orderkey, order);

        // register a callback with the listener to receive the result of asynchronous send
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<GenericRecord, GenericRecord> result) {
                log.info("Kafka sent message='{}' with offset={}", order,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Kafka unable to send message='{}'", order, ex);
            }
        });
    }
}

