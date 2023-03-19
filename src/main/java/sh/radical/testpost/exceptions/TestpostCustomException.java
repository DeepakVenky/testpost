package sh.radical.testpost.exceptions;

import java.lang.RuntimeException;
import java.lang.Throwable;

public class TestpostCustomException extends RuntimeException {

	public TestpostCustomException() {
		super();
	}

	public TestpostCustomException(String message) {
		super(message);
	}

	public TestpostCustomException(String message, Throwable t) {
		super(message, t);
	}
}
