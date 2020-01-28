package factory;

import core.solver.PuzzleSolver;
import happycube.solver.HappyCubeSolver;

/**
 * Factory class to return relevant solver 
 * based on Puzzle Type.
 * 
 * @author Anand Kumar
 *
 */
public class PuzzleSolverFactory {

	/**
	 * Returns relevant {@link PuzzleSolver} based on puzzle type.
	 * 
	 * Returns null if puzzle type not supported.
	 * 
	 * @param type
	 * @return
	 */
	public static PuzzleSolver getPuzzleSolver(PuzzleType type){
		
		if(PuzzleType.HAPPYCUBE.equals(type)){
			return HappyCubeSolver.getInstance();
		}
		
		return null;
	}
}
