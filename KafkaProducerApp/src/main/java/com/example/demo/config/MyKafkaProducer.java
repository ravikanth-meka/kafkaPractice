package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.fasterxml.jackson.databind.JsonNode;

@Configuration
public class MyKafkaProducer {


	//@Value(value = "$kafka.bootstrapAddress")
	@Value(value = "localhost:9092")
	private String bootstrapAddress;

	
	/*
	 * public Producer<String,JsonNode> createProducer() { Map<String, Object>
	 * configProperties = new HashMap<>();
	 * configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
	 * bootstrapAddress);
	 * configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	 * StringSerializer.class);
	 * configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	 * JsonSerializer.class);
	 * 
	 * Producer<String,JsonNode> kafkaProducer = new
	 * KafkaProducer<>(configProperties);
	 * 
	 * return kafkaProducer; }
	 */
	
	@Bean(name="myProducer")
	public Producer<String,JsonNode> createProducer() {
		Map<String, Object> configProperties = new HashMap<>();
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
		configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		Producer<String,JsonNode> kafkaProducer =  new KafkaProducer<>(configProperties);
				  
		return kafkaProducer;
	}

	
}
