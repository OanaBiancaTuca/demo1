package org.example.demo1.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.dto.PaymentRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaAvroConsumer {

    @KafkaListener(topics = "${topic.name}")
    public void read(ConsumerRecord<String, PaymentRequest> consumerRecord){
        String key = consumerRecord.key();
        PaymentRequest value = consumerRecord.value();
        log.info("Avro message received for key "+ key+" value: "+value.toString());
    }
}
