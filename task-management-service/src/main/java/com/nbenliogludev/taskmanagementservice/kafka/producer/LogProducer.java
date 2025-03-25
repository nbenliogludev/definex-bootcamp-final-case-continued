package com.nbenliogludev.taskmanagementservice.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbenliogludev.taskmanagementservice.dto.ErrorLogDTO;
import com.nbenliogludev.taskmanagementservice.dto.InfoLogDTO;
import com.nbenliogludev.taskmanagementservice.exception.LogProducerException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


/**
 * @author nbenliogludev
 */
@Service
@RequiredArgsConstructor
public class LogProducer {

    @Value("${spring.kafka.topic.error-log}")
    private String errorLogTopic;

    @Value("${spring.kafka.topic.info-log}")
    private String infoLogTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void produceErrorLog(ErrorLogDTO errorLogDTO) {
        try {
            String errorLogJson = objectMapper.writeValueAsString(errorLogDTO);
            kafkaTemplate.send(new ProducerRecord<>(errorLogTopic, errorLogJson));

        } catch (Exception e) {
            throw new LogProducerException();
        }
    }

    public void produceInfoLog(InfoLogDTO infoLogDTO) {
        try {
            String infoLogJson = objectMapper.writeValueAsString(infoLogDTO);
            kafkaTemplate.send(new ProducerRecord<>(infoLogTopic, infoLogJson));

        } catch (Exception e) {
            throw new LogProducerException();
        }
    }
}
