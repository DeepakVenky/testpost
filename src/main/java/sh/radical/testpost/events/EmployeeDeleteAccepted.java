package sh.radical.testpost.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sh.radical.testpost.events.BaseEvent;
import sh.radical.testpost.events.EmployeeDeleteAcceptedPayload;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDeleteAccepted
	extends BaseEvent<EmployeeDeleteAcceptedPayload> {

	public EmployeeDeleteAccepted(EmployeeDeleteAcceptedPayload data) {
		super(data);
		this.eventType = "sh.radical.testpost.events.EmployeeDeleteAccepted";
	}
}
