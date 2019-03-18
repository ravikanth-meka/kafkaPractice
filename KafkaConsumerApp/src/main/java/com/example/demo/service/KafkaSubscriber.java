package com.example.demo.service;

import java.time.Duration;
import java.util.Collections;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;


@Service
public class KafkaSubscriber {

	Logger logger = LoggerFactory.getLogger(KafkaSubscriber.class.getName());
	
	//@Value(value = "$kafka.topic1")
//	private String topicName = "mynewtopic";
	
	@Autowired
	private Consumer<String,JsonNode>  myConsumer;

	
	public void receiveObject(String topicName) {
		myConsumer.subscribe(Collections.singleton(topicName));
		while(true)
		{
			ConsumerRecords<String,JsonNode> records = myConsumer.poll(Duration.ofMillis(100));
			for(ConsumerRecord consumerRecord :records) {
				logger.info("" + consumerRecord.value());
			}
		}
	}

}
