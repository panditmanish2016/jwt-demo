package com.jwt.demo.controller;

import com.jwt.demo.model.RestaurentRequest;
import com.jwt.demo.service.KafkaMessageProducerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka-producer")
public class KafkaProducerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerController.class);

    @Autowired
    private KafkaMessageProducerService kafkaMessageProducerService;

    @PostMapping(value = "/publish-string-message")
    public void sendStringMessageToKafkaTopic(@RequestParam("message") String message) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("producer string message in controller %s", message));
        }
        this.kafkaMessageProducerService.sendStringMessage(message);
    }

    @PostMapping(value = "/publish-json-message")
    public void sendJsonMessageToKafkaTopic(@RequestBody RestaurentRequest restaurentsSaveOrUpdateRequest) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("producer json message in controller %s", restaurentsSaveOrUpdateRequest));
        }
        this.kafkaMessageProducerService.sendJsonMessage(restaurentsSaveOrUpdateRequest);
    }
}