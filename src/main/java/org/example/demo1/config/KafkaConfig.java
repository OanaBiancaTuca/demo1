package org.example.demo1.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${topic.name}")
    private String topicName;

    @Value("${topic.name2}")
    private String topicName2;
    @Bean
    public NewTopic createTopic(){
        return new NewTopic(topicName,3,(short)1);
    }
    @Bean
    NewTopic createTopicPayentRequest() {
        return TopicBuilder.name(topicName2).partitions(3).replicas(2)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
}
