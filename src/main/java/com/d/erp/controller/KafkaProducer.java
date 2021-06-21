package com.d.erp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "kafka")
@RestController
@RequestMapping("kafka")
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    // 发送消息
    @ApiOperation("kafka send message")
    @GetMapping("/kafka/{message}")
    public void sendMessage1(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send("erp_db_update", normalMessage);
    }

}
