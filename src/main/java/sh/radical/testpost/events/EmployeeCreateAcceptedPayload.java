package sh.radical.testpost.events;

import lombok.Getter;
import lombok.Setter;
import sh.radical.testpost.entities.Context;
import sh.radical.testpost.inputs.CreateEmployeeInput;

@Getter
@Setter
public class EmployeeCreateAcceptedPayload {

	Context context;

	CreateEmployeeInput createEmployeeInput;

	String acknowledgedEmployeeId;

	public EmployeeCreateAcceptedPayload(
		Context context,
		CreateEmployeeInput createEmployeeInput,
		String acknowledgedEmployeeId
	) {
		this.context = context;
		this.createEmployeeInput = createEmployeeInput;
		this.acknowledgedEmployeeId = acknowledgedEmployeeId;
	}
}
