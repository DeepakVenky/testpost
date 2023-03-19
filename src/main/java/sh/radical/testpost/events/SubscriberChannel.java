package sh.radical.testpost.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface SubscriberChannel {
	@Input(value = "employeeeventsin")
	SubscribableChannel employeeEventsChannel();
}
