package sh.radical.testpost.events;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class BaseEvent<T> {

	String eventId = UUID.randomUUID().toString();

	Instant eventPublishedAt = OffsetDateTime.now().toInstant();

	Instant eventCreatedAt = OffsetDateTime.now().toInstant();

	String eventType;

	T data;

	public BaseEvent(T data) {
		this.data = data;
	}
}
