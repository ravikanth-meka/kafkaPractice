package com.example.demo.service;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class KafkaPublisher {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	@Autowired
	private Producer<String,JsonNode>  myProducer;

	
	public void sendMessage(String topicName, String msg) {
		kafkaTemplate.send(topicName,msg);
	}
	
	public void sendObject(String topicName, JsonNode hello) {
		 
		 ProducerRecord<String, JsonNode> rec = new ProducerRecord<String, JsonNode>(topicName,hello);
		// final Producer<String, StockPrice> producer = createProducer();
		 myProducer.send(rec);
	}

}

