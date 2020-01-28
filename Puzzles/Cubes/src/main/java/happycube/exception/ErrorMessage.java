package happycube.exception;

public enum ErrorMessage {

	NO_SOLUTION_FOUND("Unable to find solution for the given pieces."),
	INCORRECT_PIECE_NUM("Please check the number of pieces provided."),
	INVALID_SIDE("Please check the side dimensions. Expected 5x5 for each piece. "),
	NULL_SIDE_FOUND("Cannot have null sides"),
	NULL_VERTEX_FOUND("Cannot have null at Vertex");
	
	String message;

	private ErrorMessage(String message) {
		this.message = message;
	}

	/**
	 * Returns error message description for chosen type
	 * @return
	 */
	public String getMessage() {
		return message;
	}


}
