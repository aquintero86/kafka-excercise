package org.example.kafka.client.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class Sender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private String topicName = "my-topic";


    public String sendMessage(String message) {
        final String[] strResult = {""};
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                strResult[0] =  String.format("Sent message=%s with offset=%d", message, result.getRecordMetadata().offset());
                System.out.println(strResult[0]);
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
        return strResult[0];

    }
}
