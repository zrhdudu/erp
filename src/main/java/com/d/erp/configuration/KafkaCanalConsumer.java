package com.d.erp.configuration;

import com.d.erp.dbchange.DBChangeEvent;
import com.d.erp.dbchange.DBChangeRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.val;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class KafkaCanalConsumer implements InitializingBean {

    ObjectMapper objectMapper = new ObjectMapper();

    @Resource
    private DBChangeDeserializer dbChangeDeserializer;

    @Resource
    private ApplicationContext applicationContext;

    @KafkaListener(topics = {"erp_db_update"},autoStartup = "true")
    public void onMessage(ConsumerRecord<String,String> record , Acknowledgment ack) throws JsonProcessingException {
        val o = objectMapper.readValue(record.value(), DBChangeRecord.class);
        o.getData().forEach( data ->{
            final val event = new DBChangeEvent(data);
            event.setType(o.getType());
            applicationContext.publishEvent(event);
        });
        ack.acknowledge();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(DBChangeRecord.class, dbChangeDeserializer);
        objectMapper.registerModule(module);
    }
}
