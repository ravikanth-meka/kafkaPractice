package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.KafkaSubscriber;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("consume")
public class KafkaSubscriberController {

	private final String topicName;

	public KafkaSubscriberController(@Value("${kafka.topic1}") final String topicName) {
		this.topicName = topicName;
	}

	@Autowired
	KafkaSubscriber kafkaSubscriber;

	@GetMapping(path = "/objmsg", produces = MediaType.APPLICATION_JSON_VALUE)
	public void helloObject() {
		ObjectMapper objectMapper = new ObjectMapper();
		kafkaSubscriber.receiveObject(this.topicName);
	}

}
