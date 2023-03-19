package sh.radical.testpost.events;

import lombok.Getter;
import lombok.Setter;
import sh.radical.testpost.entities.Context;

@Getter
@Setter
public class EmployeeDeleteAcceptedPayload {

	Context context;

	String employeeId;

	public EmployeeDeleteAcceptedPayload(Context context, String employeeId) {
		this.context = context;
		this.employeeId = employeeId;
	}
}
