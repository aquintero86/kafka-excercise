package org.example.kafka.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MessageConsumerService {
    Map<String, Set<Integer>> consumedPartitions = new ConcurrentHashMap<>();


    @KafkaListener(topics = "my-topic", groupId = "my-consumer-group")
    public void myConsumer(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("enter to consumer "+consumerRecord.toString());
        trackConsumedPartitions("consumer-0", consumerRecord);
    }


    private void trackConsumedPartitions(String key, ConsumerRecord<?, ?> record) {
        consumedPartitions.computeIfAbsent(key, k -> new HashSet<>());
        consumedPartitions.computeIfPresent(key, (k, v) -> {
            System.out.println(k +"--->"+ v);
            v.add(record.partition());
            return v;
        });
    }
}
