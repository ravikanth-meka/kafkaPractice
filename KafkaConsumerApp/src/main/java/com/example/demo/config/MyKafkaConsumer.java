package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.connect.json.JsonDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.JsonNode;

@Configuration
public class MyKafkaConsumer {


	//@Value(value = "$kafka.bootstrapAddress")
	@Value(value = "localhost:9092")
	private String bootstrapAddress;
	
	//@Value(value = "$kafka.groupid")
	private String groupId = "mygrpid";
	
	//@Value(value = "$kafka.resetconfig")
	private String resetconfig = "latest";


		
	@Bean(name="myConsumer")
	public Consumer<String,JsonNode> createProducer() {
		Map<String, Object> configProperties = new HashMap<>();
		configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		configProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, resetconfig);
		
		Consumer<String,JsonNode> kafkaConsumer =  new KafkaConsumer(configProperties);
				  
		
		
		return kafkaConsumer;
	}

	
}

