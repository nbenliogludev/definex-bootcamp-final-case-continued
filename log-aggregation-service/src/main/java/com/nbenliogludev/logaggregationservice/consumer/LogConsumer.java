package com.nbenliogludev.logaggregationservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbenliogludev.logaggregationservice.dto.ErrorLogDTO;
import com.nbenliogludev.logaggregationservice.dto.InfoLogDTO;
import com.nbenliogludev.logaggregationservice.service.ErrorLogService;
import com.nbenliogludev.logaggregationservice.service.InfoLogService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author nbenliogludev
 */
@Service
@RequiredArgsConstructor
public class LogConsumer {

    private final ErrorLogService errorLogService;
    private final InfoLogService infoLogService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic.error-log}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeErrorLog(ConsumerRecord<String, String> payload){

        try {
            ErrorLogDTO errorLogDto = objectMapper.readValue(payload.value(), ErrorLogDTO.class);
            errorLogService.createErrorLog(errorLogDto);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "${kafka.topic.info-log}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeInfoLog(ConsumerRecord<String, String> payload){

        try {
            InfoLogDTO infoLogDto = objectMapper.readValue(payload.value(), InfoLogDTO.class);
            infoLogService.createInfoLog(infoLogDto);
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
