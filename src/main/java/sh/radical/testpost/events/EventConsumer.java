package sh.radical.testpost.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import sh.radical.testpost.events.EmployeeCreateAccepted;
import sh.radical.testpost.events.EmployeeDeleteAccepted;
import sh.radical.testpost.events.EmployeeUpdateAccepted;
import sh.radical.testpost.events.SubscriberChannel;
import sh.radical.testpost.services.EmployeeService;

@Component
@EnableBinding(value = SubscriberChannel.class)
@Slf4j
public class EventConsumer {

	@Autowired
	EmployeeService employeeService;

	@StreamListener(
		value = "employeeeventsin",
		condition = "headers['eventType'] == 'sh.radical.testpost.events.EmployeeCreateAccepted'"
	)
	public void handleEmployeeCreateAccepted(EmployeeCreateAccepted event) {
		try {
			log.info(
				"Consumed event of employee {} ",
				event.data.acknowledgedEmployeeId
			);
			employeeService.handleEmployeeCreateAccepted(
				event.data.context,
				event.data.createEmployeeInput,
				event.data.acknowledgedEmployeeId
			);
			log.info(
				"Processing complete for event employee {}",
				event.data.acknowledgedEmployeeId
			);
		} catch (Exception e) {
			log.error(
				"Error while processing for Employee {} ",
				event.data.acknowledgedEmployeeId,
				e
			);
			throw e;
		}
	}

	@StreamListener(
		value = "employeeeventsin",
		condition = "headers['eventType'] == 'sh.radical.testpost.events.EmployeeUpdateAccepted'"
	)
	public void handleEmployeeUpdateAccepted(EmployeeUpdateAccepted event) {
		try {
			log.info("Consumed event of employee {} ", event.data.employeeId);
			employeeService.handleEmployeeUpdateAccepted(
				event.data.context,
				event.data.employeeId,
				event.data.updateEmployeeInput
			);
			log.info(
				"Processing complete for event employee {}",
				event.data.employeeId
			);
		} catch (Exception e) {
			log.error(
				"Error while processing for Employee {} ",
				event.data.employeeId,
				e
			);
			throw e;
		}
	}

	@StreamListener(
		value = "employeeeventsin",
		condition = "headers['eventType'] == 'sh.radical.testpost.events.EmployeeDeleteAccepted'"
	)
	public void handleEmployeeDeleteAccepted(EmployeeDeleteAccepted event) {
		try {
			log.info("Consumed event of employee {} ", event.data.employeeId);
			employeeService.handleEmployeeDeleteAccepted(
				event.data.context,
				event.data.employeeId
			);
			log.info(
				"Processing complete for event employee {}",
				event.data.employeeId
			);
		} catch (Exception e) {
			log.error(
				"Error while processing for Employee {} ",
				event.data.employeeId,
				e
			);
			throw e;
		}
	}
}
