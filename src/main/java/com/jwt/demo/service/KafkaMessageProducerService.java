package com.jwt.demo.service;

import com.jwt.demo.constant.KafkaConstants;
import com.jwt.demo.model.RestaurentRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageProducerService.class);
   
    @Autowired
    private KafkaTemplate<String, String> kafkaStringTemplate;
    /* @Autowired
     private KafkaTemplate<String, RestaurentRequest> kafkaJsonTemplate;
    */

    public void sendStringMessage(String message) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("producing string message in service %s", message));
        }
        this.kafkaStringTemplate.send(KafkaConstants.STRING_TOPIC, message);
    }

	public void sendJsonMessage(RestaurentRequest restaurentsSaveOrUpdateRequest) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("producing json message in service %s", restaurentsSaveOrUpdateRequest));
        }
        this.kafkaStringTemplate.send(KafkaConstants.JSON_TOPIC, restaurentsSaveOrUpdateRequest.toString());
    }

}