package sh.radical.testpost.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sh.radical.testpost.events.BaseEvent;
import sh.radical.testpost.events.EmployeeCreateAcceptedPayload;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeCreateAccepted
	extends BaseEvent<EmployeeCreateAcceptedPayload> {

	public EmployeeCreateAccepted(EmployeeCreateAcceptedPayload data) {
		super(data);
		this.eventType = "sh.radical.testpost.events.EmployeeCreateAccepted";
	}
}
