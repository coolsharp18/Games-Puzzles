package core.piece;

import java.util.List;

import happycube.pieces.Corner;

/**
 * Interface to a Cube Piece
 * 
 * @author Anand Kumar
 * 
 */
public interface CubePiece {

	/**
	 * Get left side of piece based on current orientation.
	 * 
	 * @return String[] representing left side of the piece.
	 */
	
	public String[] getLeftSide();
	
	/**
	 * Get right side of piece based on current orientation.
	 * 
	 * @return String[] representing right side of the piece.
	 */
	public String[] getRightSide();
	
	/**
	 * Get top side of piece based on current orientation.
	 * 
	 * @return String[] representing top side of the piece.
	 */
	public String[] getTopSide();
	
	/**
	 * Get bottom side of piece based on current orientation.
	 * 
	 * @return String[] representing bottom side of the piece.
	 */
	public String[] getBottomSide();
	
	/**
	 * Get top left corner point of piece based on current orientation.
	 * 
	 * @return String representing top left corner point of the piece.
	 */
	public String getTopLeftCorner();
	
	/**
	 * Get top right corner point of piece based on current orientation.
	 * 
	 * @return String representing top right corner point of the piece.
	 */
	public String getTopRightCorner();
	
	/**
	 * Get bottom left corner point of piece based on current orientation.
	 * 
	 * @return String representing bottom left corner point of the piece.
	 */
	public String getBottomLeftCorner();
	
	/**
	 * Get bottom right corner point of piece based on current orientation.
	 * 
	 * @return String representing bottom right corner point of the piece.
	 */
	public String getBottomRightCorner();
	
	/**
	 * Flips the given piece
	 */
	public void flip();
	
	/**
	 * Rotates the piece in the specified direction.
	 * @param clockwise - true for clockwise, false for anti-clockwise
	 */
	public void rotate(boolean clockwise);
	
	/**
	 * Returns list of start and end points of all lines from top to bottom based on current 
	 * orientation. 
	 * 
	 * @return List<Corner[]> representing Start and End points of all lines from top to bottom.
	 */
	public List<Corner[]> getStartEndPoints();
	
	
	/**
	 * Returns the side of the piece between start and end points based on current orientation of the piece.
	 * @param startPoint
	 * @param endPoint
	 * @return String[] representing the side between the passed points.
	 */
	public String[] getSide(Corner startPoint, Corner endPoint);
	
}
