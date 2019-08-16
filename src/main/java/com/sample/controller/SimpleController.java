package com.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.models.Message;
import com.sample.models.Queue;
import com.sample.service.SimpleService;

@RestController
@RequestMapping(value = "/sample")
public class SimpleController {
    
    @Autowired
    private SimpleService simpleService;

    @GetMapping
    public String helloWorld() {
        return "Welcome to Spring Boot";
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping("/queue")
    public ResponseEntity<Long> createQueue(@RequestBody Queue queue) {
        long id = simpleService.createQueue(queue);
        if( id != -1) {
            return new ResponseEntity(id,HttpStatus.CREATED);
        }
        return new ResponseEntity(id,HttpStatus.BAD_REQUEST);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping("/queue/{queueId}/message")
    public ResponseEntity<Long> createMeassge(@PathVariable long queueId,@RequestBody Message message ) {
        long messageId = simpleService.storeMessages(queueId, message);
        if(messageId != -1) {
            return new ResponseEntity(messageId,HttpStatus.CREATED);
        }
        return new ResponseEntity(messageId,HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/queue/{queueId}/message/{messageId}")
    public void removeFromQueue(@PathVariable Long queueId,@PathVariable String messageId) {
        simpleService.deleteMessage(queueId, messageId);
    }
    
    @GetMapping("/queue/{queueId}/message/{messageId}")
    public Message browseMessage(@PathVariable Long queueId,@PathVariable String messageId) {
        return simpleService.browseMessage(queueId, messageId);
    }

    @DeleteMapping("/queue/{queueId}")
    public void clearQueue(@PathVariable("queueId")long queueId) {
        simpleService.clearQueue(queueId);
    }

    @GetMapping("/queue/{queueId}/message")
    public List<Message> ListOfMessage(@PathVariable("queueId") long queueId) {

        return simpleService.getMessages(queueId);
    }
}
