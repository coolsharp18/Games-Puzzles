package happycube.pieces;

import static happycube.config.CubeParameters.PIECE_SIDE_LENGTH;

import java.util.ArrayList;
import java.util.List;

import core.piece.CubePiece;

/**
 * {@link CubePiece} implemetation to represent a HappyCubePiece.
 * 
 * This piece maintains a state and can be flipped and rotated to check
 * if it could fit into other pieces to complete the Happy Cube.
 * 
 * The state of the piece is represented by the moving corners based on Rotate and Flip operation.
 * 
 * @author Anand Kumar
 *
 */
public class HappyCubePiece implements CubePiece {

	private Corner topLeft = new Corner(0,0);
	private Corner topRight = new Corner(0,PIECE_SIDE_LENGTH-1);
	private Corner bottomLeft = new Corner(PIECE_SIDE_LENGTH-1,0);
	private Corner bottomRight = new Corner(PIECE_SIDE_LENGTH-1,PIECE_SIDE_LENGTH-1);
	boolean flipped = false;
	
	private final String[][] piece;

	public HappyCubePiece(String[][] piece) {
		super();
		this.piece = piece;
	}

	@Override
	public String[] getLeftSide() {
		return getSide(topLeft, bottomLeft);

	}

	@Override
	public String[] getRightSide() {
		return getSide(topRight, bottomRight);
	}

	@Override
	public String[] getTopSide() {
		return getSide(topLeft, topRight);
	}

	@Override
	public String[] getBottomSide() {
		return getSide(bottomLeft, bottomRight);
	}

	/**
	 * Flips the piece along vertical axis
	 */
	@Override
	public void flip() {
		//The original Array remains unchanged, only the corners are moved around.
		Corner tempTopLeft = topLeft;
		topLeft = topRight;
		topRight= tempTopLeft;
		
		Corner tempBottomLeft = bottomLeft;
		bottomLeft = bottomRight;
		bottomRight = tempBottomLeft;
		
		flipped = !flipped;
	}

	/**
	 * Rotates the piece in the specified direction. true for clockwise and
	 * false for counter clockwise.
	 */
	@Override
	public void rotate(boolean clockwise) {
		//The original Array remains unchanged, only the corners are moved around.
		if (clockwise) {
			Corner tempTopLeft = topLeft;
			topLeft = bottomLeft;
			bottomLeft = bottomRight;
			bottomRight = topRight;
			topRight = tempTopLeft;

		} else {

			Corner tempTopRight = topRight;
			topRight = bottomRight;
			bottomRight = bottomLeft;
			bottomLeft = topLeft;
			topLeft = tempTopRight;
		}

	}


	@Override
	public String getTopLeftCorner() {
		return piece[topLeft.getPositionY()][topLeft.getPositionX()];
	}

	@Override
	public String getTopRightCorner() {
		return piece[topRight.getPositionY()][topRight.getPositionX()];
	}

	@Override
	public String getBottomLeftCorner() {
		return piece[bottomLeft.getPositionY()][bottomLeft.getPositionX()];
	}

	@Override
	public String getBottomRightCorner() {
		return piece[bottomRight.getPositionY()][bottomRight.getPositionX()];
	}


	/**
	 * Gets the side between the corner passed.
	 * 
	 * @param startCorner
	 * @param endCorner
	 * @return String[] representing the side between the corners.
	 */
	public String[] getSide(Corner startCorner, Corner endCorner) {

		String[] side = new String[PIECE_SIDE_LENGTH];
		boolean horizontal = false;
		boolean reverse = false;
		int sideIndex;
		int startIndex;
		int incrementer = 1;
		
		if (startCorner.getPositionY() == endCorner.getPositionY()) {//Y Axis remains same, indicating horizontal read.
			horizontal = true;
			sideIndex = startCorner.getPositionY();
			startIndex = startCorner.getPositionX();
			if (startCorner.getPositionX() > endCorner.getPositionX()){//Start index is greater than end index, indicating a reverse read in original Array.
				incrementer = -1; //Read line in Reverse direction
				reverse = true;
			}
			else
				return piece[sideIndex];
			
		} else {
			if (startCorner.getPositionY() > endCorner.getPositionY())
			{
				incrementer = -1; //Read line in Reverse direction
				reverse = true;
			}
			sideIndex = startCorner.getPositionX();
			startIndex = startCorner.getPositionY();
		}
		
		for(int i = startIndex,k=0; reverse? i >=0: i<PIECE_SIDE_LENGTH; i+=incrementer,k++){
			
			if(horizontal)
				side[k] = piece[sideIndex][i];
			else
				side[k] = piece[i][sideIndex];
		}

		return side;
	}
	
	/**
	 * Returns the list of start and end points from top to bottom based on the current orientation
	 * of the Piece.
	 */
	@Override
	public List<Corner[]> getStartEndPoints(){
		List<Corner[]> points = new ArrayList<>();
		boolean horizontal = false;
		boolean reverse = false;
		
		if (topLeft.getPositionY() == topRight.getPositionY()) {//Y Axis remains same, indicating a horizontal read of original Array.
			horizontal = true;
			if (topLeft.getPositionX() > topRight.getPositionX()){//Start index is greater than end index, indicating a reverse read in original Array.
				reverse = true;
			}
			
		} else {
			if (topLeft.getPositionY() > topRight.getPositionY())//Start index is greater than end index, indicating a reverse read in original Array.
			{
				reverse = true;
			}
			
		}
		
		int leftX = topLeft.getPositionX();
		int leftY = topLeft.getPositionY();
		
		int rightX = topRight.getPositionX();
		int rightY = topRight.getPositionY();
		Corner[] line;
		Corner start, end;
		for(int i = 0; i<PIECE_SIDE_LENGTH; i++){
		line = new Corner[2];
		
		if(horizontal){ //Read along Y axis
			if(flipped){
				if(reverse){
					start = new Corner(leftY++, leftX);
					end = new Corner(rightY++, rightX);
					}else{
						start = new Corner(leftY--, leftX) ;
						end = new Corner(rightY--, rightX);	
					}
			}
			else{
				if(reverse){
					start = new Corner(leftY--, leftX);
					end = new Corner(rightY--, rightX);
					}else{
						start = new Corner(leftY++, leftX) ;
						end = new Corner(rightY++, rightX);	
					}
			}
			
		}else{

			if(flipped){
				if(reverse){
					start = new Corner(leftY, leftX--);
					end = new Corner(rightY, rightX--);
					}else{
					start = new Corner(leftY, leftX++);
					end = new Corner(rightY, rightX++);	
					}
				
			}
			else{
			if(reverse){
				start = new Corner(leftY, leftX++);
				end = new Corner(rightY, rightX++);
				}else{
				start = new Corner(leftY, leftX--);
				end = new Corner(rightY, rightX--);	
				}
			}

		}
		
		line[0] = start;
		line[1] = end;
		points.add(line);
		
		}
		
		return points;
	}
}
