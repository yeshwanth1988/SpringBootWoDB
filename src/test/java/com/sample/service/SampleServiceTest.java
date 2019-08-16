package com.sample.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.models.Message;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleServiceTest {
	@Autowired
	private SimpleService myService;
	@Test
	public void testStoreMessages() {
		Message message = new Message();
		message.setId(11);
		message.setMessages("Test");
		long b = myService.storeMessages(11, message);
		assertNotEquals(b, -1);
	}

	@Test
	public void testGetMessages() {
		List<Message> list = myService.getMessages(11);
		assertEquals(list.size(), 1);
	}
	

}
