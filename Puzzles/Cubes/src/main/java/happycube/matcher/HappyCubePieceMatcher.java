package happycube.matcher;

import java.util.List;

import core.cube.Cube;
import core.matcher.CubePieceMatcher;
import core.piece.CubePiece;
import happycube.Validation.Validator;
import happycube.config.CubeParameters;
import happycube.exception.ErrorMessage;

public class HappyCubePieceMatcher implements CubePieceMatcher {
	
	private static HappyCubePieceMatcher instance;
	
	private HappyCubePieceMatcher(){
		
	}

	/**
	 * 
	 * @return Singleton instance of {@link HappyCubePieceMatcher}
	 */
	public static HappyCubePieceMatcher getInstance(){
		
		if(instance == null){
			instance = new HappyCubePieceMatcher();
		}
		
		return instance;
	}
	
	/**
	 * Matches the pieces one by one in the following order - 
	 * 
	 * LEFT, FRONT, RIGHT, BACK, TOP
	 * 
	 * The bottom piece is taken as the base and all the other pieces are rotated and flipped until a Happy Cube resolution is found. 
	 * 
	 * @param pieces
	 * @return true if matching orientation of the pieces to form a Happy Cube is found, false otherwise.
	 */
	public boolean matchPieces(List<CubePiece> pieces){
		
		return matchLeftPiece(pieces);
	}
	
	private boolean matchLeftPiece(List<CubePiece> pieces) {

		boolean matched = false;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {

				if (matchSide(pieces.get(Cube.BOTTOM).getLeftSide(), pieces.get(Cube.LEFT).getRightSide()))
				{
					matched = matchFrontPiece(pieces);
					if(matched)
						return true;
					else
						pieces.get(Cube.LEFT).rotate(true);
				}
				else
					pieces.get(Cube.LEFT).rotate(true);
			}
			pieces.get(Cube.LEFT).flip();
		}

		return matched;
	}
	
	private boolean matchFrontPiece(List<CubePiece> pieces) {
		boolean matched = false;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {

				if (matchSide(pieces.get(Cube.LEFT).getBottomSide(), pieces.get(Cube.FRONT).getLeftSide())
						&& matchSide(pieces.get(Cube.BOTTOM).getBottomSide(), pieces.get(Cube.FRONT).getBottomSide())
						&& matchFrontBottomLeftVertex(pieces))
				{
					matched = matchRightPiece(pieces);
					if(matched)
					return true;
					else
						pieces.get(Cube.FRONT).rotate(true);
						
				}
				else
					pieces.get(Cube.FRONT).rotate(true);
			}
			pieces.get(Cube.FRONT).flip();
		}

		return matched;
	}
	
	private boolean matchRightPiece(List<CubePiece> pieces) {
		boolean matched = false;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {

				if (matchSide(pieces.get(Cube.RIGHT).getBottomSide(), pieces.get(Cube.FRONT).getRightSide())
						&& matchSide(pieces.get(Cube.RIGHT).getLeftSide(), pieces.get(Cube.BOTTOM).getRightSide())
						&& matchFrontBottomRightVertex(pieces))
				{
					matched = matchBackPiece(pieces);
					if(matched)
						return true;
					else
						pieces.get(Cube.RIGHT).rotate(true);
				}
				else
					pieces.get(Cube.RIGHT).rotate(true);
			}
			pieces.get(Cube.RIGHT).flip();
		}

		return matched;
	}
	
	private boolean matchBackPiece(List<CubePiece> pieces) {
		boolean matched = false;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {

				if (matchSide(pieces.get(Cube.BACK).getLeftSide(), pieces.get(Cube.LEFT).getTopSide())
						&& matchSide(pieces.get(Cube.BACK).getBottomSide(), pieces.get(Cube.BOTTOM).getTopSide())
						&& matchSide(pieces.get(Cube.BACK).getRightSide(), pieces.get(Cube.RIGHT).getTopSide())
						&& matchBackBottomLeftVertex(pieces)
						&& matchBackBottomRightVertex(pieces))
				{
					matched = matchTopPiece(pieces);
					if(matched)
						return true;
					else
						pieces.get(Cube.BACK).rotate(true);
				}
				else
					pieces.get(Cube.BACK).rotate(true);
			}
			pieces.get(Cube.BACK).flip();
		}

		return matched;
	}
	
	private boolean matchTopPiece(List<CubePiece> pieces) {
		boolean matched = false;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {

				if (matchSide(pieces.get(Cube.TOP).getBottomSide(), pieces.get(Cube.FRONT).getTopSide())
						&& matchSide(pieces.get(Cube.TOP).getLeftSide(), pieces.get(Cube.LEFT).getLeftSide())
						&& matchSide(pieces.get(Cube.TOP).getRightSide(), pieces.get(Cube.RIGHT).getRightSide())
						&& matchSide(pieces.get(Cube.TOP).getTopSide(), pieces.get(Cube.BACK).getTopSide())
						&& matchFrontTopLeftVertex(pieces)
						&& matchFrontTopRightVertex(pieces)
						&& matchBackTopLeftVertex(pieces)
						&& matchBackTopRightVertex(pieces)
						)
					return true;
				else
					pieces.get(Cube.TOP).rotate(true);
			}
			pieces.get(Cube.TOP).flip();
		}

		return matched;
	}
	
	private boolean matchFrontBottomLeftVertex(List<CubePiece> pieces) {

		
		return matchVertex(pieces.get(Cube.LEFT).getBottomRightCorner(), pieces.get(Cube.BOTTOM).getBottomLeftCorner(), pieces.get(Cube.FRONT).getBottomLeftCorner());
	}
	
	private boolean matchFrontBottomRightVertex(List<CubePiece> pieces) {

		
		return matchVertex(pieces.get(Cube.RIGHT).getBottomLeftCorner(), pieces.get(Cube.BOTTOM).getBottomRightCorner(), pieces.get(Cube.FRONT).getBottomRightCorner());
	}
	
	private boolean matchBackBottomLeftVertex(List<CubePiece> pieces) {

		
		return matchVertex(pieces.get(Cube.LEFT).getTopRightCorner(), pieces.get(Cube.BOTTOM).getTopLeftCorner(), pieces.get(Cube.BACK).getBottomLeftCorner());
	}
	
	private boolean matchBackBottomRightVertex(List<CubePiece> pieces) {

		
		return matchVertex(pieces.get(Cube.RIGHT).getTopLeftCorner(), pieces.get(Cube.BOTTOM).getTopRightCorner(), pieces.get(Cube.BACK).getBottomRightCorner());
	}
	
	private boolean matchFrontTopLeftVertex(List<CubePiece> pieces) {

		
		return matchVertex(pieces.get(Cube.LEFT).getBottomLeftCorner(), pieces.get(Cube.TOP).getBottomLeftCorner(), pieces.get(Cube.FRONT).getTopLeftCorner());
	}
	
	private boolean matchFrontTopRightVertex(List<CubePiece> pieces) {

		
		return matchVertex(pieces.get(Cube.RIGHT).getBottomRightCorner(), pieces.get(Cube.TOP).getBottomRightCorner(), pieces.get(Cube.FRONT).getTopRightCorner());
	}
	
	private boolean matchBackTopLeftVertex(List<CubePiece> pieces) {

		
		return matchVertex(pieces.get(Cube.LEFT).getTopLeftCorner(), pieces.get(Cube.TOP).getTopLeftCorner(), pieces.get(Cube.BACK).getTopLeftCorner());
	}
	
	private boolean matchBackTopRightVertex(List<CubePiece> pieces) {

		
		return matchVertex(pieces.get(Cube.RIGHT).getTopRightCorner(), pieces.get(Cube.TOP).getTopRightCorner(), pieces.get(Cube.BACK).getTopRightCorner());
	}
	
	@Override
	public boolean matchSide(String[] a, String[] b) {

		Validator.noNullPresent(ErrorMessage.NULL_SIDE_FOUND, a, b);
		Validator.matchLength(ErrorMessage.INCORRECT_PIECE_NUM, a.length, b.length, CubeParameters.PIECE_SIDE_LENGTH);

		for (int i = 0; i < a.length; i++) {
			
			if (a[i].equals(CubeParameters.CUBE_PLUG)) {
				
				if (a[i].equals(b[i]))
					return false;
			}
		}

		return true;
	}

	@Override
	public boolean matchVertex(String... vertex) {
		Validator.noNullPresent(ErrorMessage.NULL_VERTEX_FOUND, (Object[]) vertex);
		int plugCount=0;
		for(String value : vertex){
			if(value.equals(CubeParameters.CUBE_PLUG)){
				plugCount++;
			}
			
			if(plugCount == 1 ){
				return true;
			}
		}
		
		return false;
	}

}
