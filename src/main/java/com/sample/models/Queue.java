package com.sample.models;

import java.util.List;
import lombok.Data;

public @Data class Queue {
	private String queueName;
	private List<Message> messages;
}
