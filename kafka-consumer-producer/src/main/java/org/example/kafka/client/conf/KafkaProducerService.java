package org.example.kafka.client.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.*;


public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate ;

    private String topicName = "my-topic";

    public void sendMessage(String msg) {
        kafkaTemplate.send(getTopicName(), msg);
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
