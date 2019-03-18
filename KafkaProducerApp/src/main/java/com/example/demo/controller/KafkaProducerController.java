package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.KafkaPublisher;
import com.example.dto.Hello;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("produce")
public class KafkaProducerController {

	private final String topicName;

	public KafkaProducerController(@Value("${kafka.topic1}") final String topicName) {
		this.topicName = topicName;
	}

	@Autowired
	KafkaPublisher kafkaPublisher;

	@PostMapping(path = "/msg", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> hello(@RequestBody String msg) {
		kafkaPublisher.sendMessage(this.topicName, msg);
		return new ResponseEntity<String>("Message posted to Kafka topic", HttpStatus.OK);
	}

	@PostMapping(path = "/msgobj", produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode helloObject(@RequestBody Hello hello) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.valueToTree(hello);
		kafkaPublisher.sendObject(this.topicName, jsonNode);
		return jsonNode;
	}

}
