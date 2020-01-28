package happycube.exception;

public class SolverException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with the specified detail message. 
	 * @param message
	 */
	public SolverException(ErrorMessage message)
	{
		super(message.getMessage());
	}
	
	/**
	 * Constructs a new exception with the specified detail message and cause. 
	 * @param message
	 * @param t
	 */
	SolverException(String message, Throwable t){
		
		super(message, t);
	}

	public SolverException(String message) {
		super(message);
	}
}
