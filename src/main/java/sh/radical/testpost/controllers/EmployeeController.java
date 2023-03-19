package sh.radical.testpost.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.radical.testpost.entities.Context;
import sh.radical.testpost.inputs.CreateEmployeeInput;
import sh.radical.testpost.inputs.UpdateEmployeeInput;
import sh.radical.testpost.models.Employee;
import sh.radical.testpost.services.EmployeeService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping
	public ResponseEntity create(
		@RequestBody CreateEmployeeInput createEmployeeInput
	) {
		Context context = new Context();
		log.info("Received a new create request");
		var id = Employee.getNewEmployeeId();
		employeeService.create(context, createEmployeeInput, id);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "employees/" + id);
		log.info("Create request for Employee - {} is complete", id);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@PutMapping(value = "/{employeeId}")
	public ResponseEntity update(
		@PathVariable(value = "employeeId") String employeeId,
		@RequestBody UpdateEmployeeInput updateEmployeeInput
	) {
		log.info("Received a update request for Employee {} ", employeeId);
		Context context = new Context();
		employeeService.update(context, employeeId, updateEmployeeInput);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/" + employeeId);
		log.info("Update request for Employee {} is complete", employeeId);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/{employeeId}")
	public Employee get(@PathVariable(value = "employeeId") String employeeId) {
		log.info("Received a get request for Employee {} ", employeeId);
		Context context = new Context();
		Employee existingEmployee = employeeService.get(context, employeeId);
		log.info("Get request for Employee {} is complete ", employeeId);
		return existingEmployee;
	}

	@DeleteMapping(value = "/{employeeId}")
	public void delete(@PathVariable(value = "employeeId") String employeeId) {
		log.info("Received a delete request for Employee {} ", employeeId);
		Context context = new Context();
		employeeService.delete(context, employeeId);
		log.info("Delete request completed for Employee {} ", employeeId);
	}
}
