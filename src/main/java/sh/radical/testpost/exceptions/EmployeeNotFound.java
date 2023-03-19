package sh.radical.testpost.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;
import sh.radical.testpost.exceptions.TestpostCustomException;

@ResponseStatus
public class EmployeeNotFound extends TestpostCustomException {

	public EmployeeNotFound() {
		super();
	}

	public EmployeeNotFound(String message) {
		super(message);
	}

	public EmployeeNotFound(String message, Throwable t) {
		super(message, t);
	}
}
