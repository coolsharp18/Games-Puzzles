package core.solver;

import java.util.List;

import core.piece.CubePiece;

/**
 * Interface for puzzle solver implementations
 * 
 * @author Anand Kumar
 *
 */
public interface PuzzleSolver {

	/**
	 * Solve the passed pieces based on the PuzzleSolver implementaion.
	 * 
	 * @param pieces
	 * @return true if solution found, false if no solution found.
	 */
	public boolean solve(List<CubePiece> pieces);
}
