package com.sample.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sample.models.DataSet;
import com.sample.models.Message;
import com.sample.models.Queue;

@Service
public class SimpleService {

    public Long storeMessages(long queueId, Message message) {
        long messageId = -1;
        Queue queue = DataSet.getInstance().getQueue(queueId);
        if (queue != null) {
            messageId = new Date().getTime();
            message.setId(messageId);
            
            if(queue.getMessages() == null) {
                queue.setMessages(new ArrayList<Message>());
            }
            queue.getMessages().add(message);
        }
        return messageId;
    }

    public List<Message> getMessages(long id){
        Map<Long, Queue> map= DataSet.getInstance().getDataSet();
        return map.get(id).getMessages();
    }
    
    public void clearQueue(long queueId) {
        Map<Long, Queue> map= DataSet.getInstance().getDataSet();
        map.remove(queueId);        
    }
    
    public void deleteMessage(long queueId, String messageId) {
        Map<Long, Queue> map= DataSet.getInstance().getDataSet();
        if (map.containsKey(queueId)) {
            Queue q = map.get(queueId);
            List<Message> msgList = q.getMessages();
            for (Message m : msgList) {
                if (m.getId() == Long.parseLong(messageId)) {
                    msgList.remove(m);
                }
            }
        }
    }
    public Message browseMessage(long queueId, String messageId) {
        Message message = null;
        Map<Long, Queue> map= DataSet.getInstance().getDataSet();
        if (map.containsKey(queueId)) {
            Queue q = map.get(queueId);
            List<Message> msgList = q.getMessages();
            for (Message m : msgList) {
                if (m.getId() == Long.parseLong(messageId)) {
                    message = m;
                }
            }
        }
        return message;
    }  
    public Long createQueue(Queue queue) {
        long queueId = -1;
        Map<Long, Queue> map= DataSet.getInstance().getDataSet();
        Date date = new Date();
        queueId = date.getTime();
        map.put(queueId, queue);
        return queueId;
    }
}
