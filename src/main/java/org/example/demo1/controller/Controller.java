package org.example.demo1.controller;

import org.example.demo1.producer.KafkaAvroProducer;

import org.example.dto.PaymentRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {
    private KafkaAvroProducer producer;

    public Controller(KafkaAvroProducer producer) {
        this.producer = producer;
    }

    @PostMapping("add")
    public String sendMessage(@RequestBody PaymentRequest payment){
        producer.send((String) payment.getBookingId(),payment);
        return "Message published!";
    }
}
