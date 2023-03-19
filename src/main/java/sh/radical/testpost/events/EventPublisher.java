package sh.radical.testpost.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import sh.radical.testpost.events.BaseEvent;
import sh.radical.testpost.events.PublisherChannel;

@Component
@EnableBinding(value = PublisherChannel.class)
public class EventPublisher {

	@Autowired
	PublisherChannel publishChannel;

	private static final <T extends BaseEvent> Message<T> message(
		T val,
		String key
	) {
		return MessageBuilder
			.withPayload(val)
			.setHeader(KafkaHeaders.MESSAGE_KEY, key)
			.setHeader("eventType", val.eventType)
			.build();
	}

	public void sendEmployeeEvent(BaseEvent event, String key) {
		publishChannel.employeeEventsChannel().send(message(event, key));
	}
}
