package org.example.demo1.producer;

import org.example.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaAvroProducer {

    @Value("${topic.name2}")
    private String topicName;

    private KafkaTemplate<String, Object> template;

    public KafkaAvroProducer(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    public void send(String key, PaymentRequest payment){
        CompletableFuture<SendResult<String, Object>> future = template.send(topicName,key, payment);
        future.whenComplete((result,ex)-> {
            if (ex == null) {
                System.out.println("Sent message = " + payment + " with offset= " + result.getRecordMetadata().offset());
            } else {
                System.out.println("Unable to send message : "+ ex.getMessage());

            }

        });

    }
}
