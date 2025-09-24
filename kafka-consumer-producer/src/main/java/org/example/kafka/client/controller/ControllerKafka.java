package org.example.kafka.client.controller;


import org.example.kafka.client.conf.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerKafka {

    @Autowired
    private Sender sender;

    @PostMapping("/message")
    String  senMessage() {
        String message ="hello sender 1";
        return sender.sendMessage(message);
    }


}
