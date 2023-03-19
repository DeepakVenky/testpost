package sh.radical.testpost.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sh.radical.testpost.entities.Context;
import sh.radical.testpost.events.EmployeeCreateAccepted;
import sh.radical.testpost.events.EmployeeCreateAcceptedPayload;
import sh.radical.testpost.events.EmployeeDeleteAccepted;
import sh.radical.testpost.events.EmployeeDeleteAcceptedPayload;
import sh.radical.testpost.events.EmployeeUpdateAccepted;
import sh.radical.testpost.events.EmployeeUpdateAcceptedPayload;
import sh.radical.testpost.events.EventPublisher;
import sh.radical.testpost.exceptions.EmployeeNotFound;
import sh.radical.testpost.inputs.CreateEmployeeInput;
import sh.radical.testpost.inputs.UpdateEmployeeInput;
import sh.radical.testpost.mappers.EmployeeMapper;
import sh.radical.testpost.models.Employee;
import sh.radical.testpost.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EventPublisher eventPublisher;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeMapper employeeMapper;

	public void create(
		Context context,
		CreateEmployeeInput createEmployeeInput,
		String employeeId
	) {
		eventPublisher.sendEmployeeEvent(
			new EmployeeCreateAccepted(
				new EmployeeCreateAcceptedPayload(
					context,
					createEmployeeInput,
					employeeId
				)
			),
			employeeId
		);
	}

	public Employee handleEmployeeCreateAccepted(
		Context context,
		CreateEmployeeInput createEmployeeInput,
		String employeeId
	) {
		Employee employee = employeeMapper.createEmployee(
			context,
			createEmployeeInput,
			employeeId
		);
		Employee createdEmployee = employeeRepository.save(employee);
		return createdEmployee;
	}

	public void update(
		Context context,
		String employeeId,
		UpdateEmployeeInput updateEmployeeInput
	) {
		Optional<Employee> existingEmployeeData = employeeRepository.findById(
			employeeId
		);

		if (existingEmployeeData.isEmpty()) {
			throw new EmployeeNotFound();
		}
		eventPublisher.sendEmployeeEvent(
			new EmployeeUpdateAccepted(
				new EmployeeUpdateAcceptedPayload(
					context,
					employeeId,
					updateEmployeeInput
				)
			),
			employeeId
		);
	}

	public Employee handleEmployeeUpdateAccepted(
		Context context,
		String employeeId,
		UpdateEmployeeInput updateEmployeeInput
	) {
		Optional<Employee> existingEmployeeData = employeeRepository.findById(
			employeeId
		);

		if (existingEmployeeData.isEmpty()) {
			throw new EmployeeNotFound();
		}
		Employee updatedEmployee = employeeMapper.updateEmployee(
			context,
			employeeId,
			updateEmployeeInput,
			existingEmployeeData.get()
		);
		Employee savedEmployee = employeeRepository.save(updatedEmployee);
		return savedEmployee;
	}

	public Employee get(Context context, String employeeId) {
		Optional<Employee> existingEmployeeData = employeeRepository.findById(
			employeeId
		);
		if (existingEmployeeData.isEmpty()) {
			throw new EmployeeNotFound();
		}
		return existingEmployeeData.get();
	}

	public void delete(Context context, String employeeId) {
		Optional<Employee> existingEmployeeData = employeeRepository.findById(
			employeeId
		);

		if (existingEmployeeData.isEmpty()) {
			throw new EmployeeNotFound();
		}
		eventPublisher.sendEmployeeEvent(
			new EmployeeDeleteAccepted(
				new EmployeeDeleteAcceptedPayload(context, employeeId)
			),
			employeeId
		);
	}

	public void handleEmployeeDeleteAccepted(
		Context context,
		String employeeId
	) {
		Optional<Employee> existingEmployeeData = employeeRepository.findById(
			employeeId
		);

		if (existingEmployeeData.isEmpty()) {
			throw new EmployeeNotFound();
		}
		employeeRepository.deleteById(employeeId);
	}
}
