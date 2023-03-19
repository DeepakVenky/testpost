package sh.radical.testpost.exceptions;

import java.lang.Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sh.radical.testpost.exceptions.EmployeeNotFound;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = EmployeeNotFound.class)
	public ResponseEntity handleEmployeeNotFound(EmployeeNotFound exception) {
		return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity genericExceptionHandler(Exception exception) {
		log.error("Unhandled exception", exception);
		return new ResponseEntity(
			"Internal Server Error",
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}
}
