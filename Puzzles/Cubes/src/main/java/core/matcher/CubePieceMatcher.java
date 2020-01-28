package core.matcher;

/**
 * Interface to Match sides of a piece.
 * 
 * @author Anand Kumar
 *
 */
public interface CubePieceMatcher {

	/**
	 * Matches the passed sides based on the {@link CubePieceMatcher} implementation.
	 * 
	 * @param a
	 * @param b
	 * @return true if matched, false otherwise.
	 */
	public boolean matchSide(String[] a, String[] b);
	
	/**
	 * Matches the passed Points of the piece based on the {@link CubePieceMatcher} implementation
	 * to ensure they fit at the vertex.
	 * 
	 * @param strings
	 * @return true if the points can fit together at the vertex, false otherwise.
	 */
	public boolean matchVertex(String ... strings);
}
