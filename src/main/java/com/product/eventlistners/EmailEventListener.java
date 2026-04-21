package com.product.eventlistners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.product.event.SimpleMessageEvent;
import com.product.service.MailService;

@Component
public class EmailEventListener {
	@Autowired
	private MailService emailService;

	@EventListener
	@Async
	public void handleSimpleEmailEvent(SimpleMessageEvent event) {
		System.out.println(Thread.currentThread());
		emailService.sentEmail(event.getReceiverEmail(), event.getMessage(), event.getSubject());
	}
}
