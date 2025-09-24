package org.example.kafka.client.conf;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Properties properties = new Properties();

        // Required configurations
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092"); // Kafka broker address(es)
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // Key serializer
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // Value serializer

        // Optional configurations (examples)
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all"); // Acknowledgment level (0, 1, all)
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, "3"); // Number of retries on failed sends
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "16384"); // Batch size in bytes
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "1"); // Linger time in milliseconds
        properties.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432"); // Producer buffer memory in bytes
        Map<String, Object> map = new HashMap<String, Object>((Map) properties);
        return new DefaultKafkaProducerFactory<>(map);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}