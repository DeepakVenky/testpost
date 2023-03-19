package sh.radical.testpost.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sh.radical.testpost.events.BaseEvent;
import sh.radical.testpost.events.EmployeeUpdateAcceptedPayload;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeUpdateAccepted
	extends BaseEvent<EmployeeUpdateAcceptedPayload> {

	public EmployeeUpdateAccepted(EmployeeUpdateAcceptedPayload data) {
		super(data);
		this.eventType = "sh.radical.testpost.events.EmployeeUpdateAccepted";
	}
}
