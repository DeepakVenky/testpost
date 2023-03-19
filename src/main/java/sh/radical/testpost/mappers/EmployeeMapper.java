package sh.radical.testpost.mappers;

import org.springframework.stereotype.Component;
import sh.radical.testpost.entities.Context;
import sh.radical.testpost.inputs.CreateEmployeeInput;
import sh.radical.testpost.inputs.UpdateEmployeeInput;
import sh.radical.testpost.models.Employee;

@Component
public class EmployeeMapper {

	public Employee createEmployee(
		Context context,
		CreateEmployeeInput createEmployeeInput,
		String employeeId
	) {
		Employee employee = new Employee();
		employee.setName(createEmployeeInput.name);
		employee.setEmployeeId(employeeId);
		return employee;
	}

	public Employee updateEmployee(
		Context context,
		String employeeId,
		UpdateEmployeeInput updateEmployeeInput,
		Employee existingEmployee
	) {
		existingEmployee.setName(updateEmployeeInput.name);
		return existingEmployee;
	}
}
