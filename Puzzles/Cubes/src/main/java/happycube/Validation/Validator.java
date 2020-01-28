package happycube.Validation;

import happycube.exception.ErrorMessage;
import happycube.exception.SolverException;

public class Validator {

	/**
	 * Method to ensure none of the passed objects are null.
	 * Use this to validate when an operation requires non-null args to function.
	 * @param message -Message to be thrown in case of failure
	 * @param o - List of comma separated objects to be validated.
	 */
	public static void noNullPresent(ErrorMessage message, Object... o) {

		if(o == null){
			throw new SolverException(message);
		}
		for (Object object : o) {
			if (object == null) {
				throw new SolverException(message);
			}
		}
	}
	
	/**
	 * Validates the passed lengths matches to each other and to the length expected.
	 * @param message - Message to be thrown if validation fails.
	 * @param a - Length of the first side
	 * @param b - Length of the second side
	 * @param length - Expected length for both sides
	 */
	public static void matchLength(ErrorMessage message, int a, int b, int length) {
		if (a != b) {
			throw new SolverException(message);
		} else {
			if (a != length)
				throw new SolverException(message);
		}
	}
}
