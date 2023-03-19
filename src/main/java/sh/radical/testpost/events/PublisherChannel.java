package sh.radical.testpost.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface PublisherChannel {
	@Output(value = "employeeeventsout")
	SubscribableChannel employeeEventsChannel();
}
