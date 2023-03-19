package sh.radical.testpost.events;

import lombok.Getter;
import lombok.Setter;
import sh.radical.testpost.entities.Context;
import sh.radical.testpost.inputs.UpdateEmployeeInput;

@Getter
@Setter
public class EmployeeUpdateAcceptedPayload {

	Context context;

	String employeeId;

	UpdateEmployeeInput updateEmployeeInput;

	public EmployeeUpdateAcceptedPayload(
		Context context,
		String employeeId,
		UpdateEmployeeInput updateEmployeeInput
	) {
		this.context = context;
		this.employeeId = employeeId;
		this.updateEmployeeInput = updateEmployeeInput;
	}
}
