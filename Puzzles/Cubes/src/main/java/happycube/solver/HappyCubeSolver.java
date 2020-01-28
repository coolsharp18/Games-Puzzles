package happycube.solver;

import java.util.Collections;
import java.util.List;

import core.cube.Cube;
import core.piece.CubePiece;
import core.solver.PuzzleSolver;
import happycube.matcher.HappyCubePieceMatcher;

public class HappyCubeSolver implements PuzzleSolver {
	
	private static HappyCubeSolver solver;
	HappyCubePieceMatcher matcher = HappyCubePieceMatcher.getInstance();
	int count = 0;
	
	HappyCubeSolver(){
		
	}
	
	public static HappyCubeSolver getInstance(){
		if(solver == null){
			solver = new HappyCubeSolver();
		}
		return solver;
	}
	@Override
	public boolean solve(List<CubePiece> pieces) {
		return solve(pieces,1);
		
	}
	
	/**
	 * Method to recursively arrange the pieces in all possible locations except for piece at
	 * index 0 (Bottom piece).
	 * For each such arrangement, the pieces are checked if they could be oriented to fit as a cube.
	 * If an orientation is found, no further shuffling is done and the method returns. 
	 *  
	 * @param pieces
	 * @param start
	 * @return
	 */
	private boolean solve(List<CubePiece> pieces, int start){
		
		for(int i = start; i < pieces.size(); i++){
			
			Collections.swap(pieces, i, start);
			
			if(solve(pieces, start+1)){
				return true;
			}
			
			Collections.swap(pieces, i, start);
		}
		
		if(start == pieces.size() - 1){
			count++;
			for(int i = 0; i<2; i++){
				for(int j = 0; j<4 ; j++){
					boolean matched = matcher.matchPieces(pieces);
					if(matched)
						return true;
					else
						pieces.get(Cube.BOTTOM).rotate(true);
				}
				pieces.get(Cube.BOTTOM).flip();
			}
			
		}
		
		return false;
	}
	
	

}
