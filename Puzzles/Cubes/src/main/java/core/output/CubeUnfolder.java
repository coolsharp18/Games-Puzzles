package core.output;

import java.util.List;

import core.piece.CubePiece;

/**
 * Interface to provide unfold options for a cube.
 * 
 * @author Anand Kumar
 *
 */
public interface CubeUnfolder {

	/**
	 * unfold the passed {@link List}<{@link CubePiece}> pieces and prints to the console.
	 * 
	 * @param pieces
	 */
	public void unfoldAndPrintToConsole(List<CubePiece> pieces);
	
	/**
	 * unfold the passed {@link List}<{@link CubePiece}> pieces and prints to the file passed.
	 * 
	 * @param pieces
	 */
	public void unfoldAndPrintToFile(List<CubePiece> pieces, String filePath);
	
}
