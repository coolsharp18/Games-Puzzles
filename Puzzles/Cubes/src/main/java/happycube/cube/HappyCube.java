package happycube.cube;

import static happycube.config.CubeParameters.OUTPUT_FILE_PATH;

import java.util.List;

import core.cube.Cube;
import core.piece.CubePiece;
import core.solver.PuzzleSolver;
import factory.PuzzleSolverFactory;
import factory.PuzzleType;
import happycube.exception.ErrorMessage;
import happycube.exception.SolverException;
import happycube.input.InputBlue;
import happycube.input.InputFactory;
import happycube.input.InputRed;
import happycube.output.HappyCubeUnfolder;

public class HappyCube implements Cube {

	private static final PuzzleSolver solver = PuzzleSolverFactory.getPuzzleSolver(PuzzleType.HAPPYCUBE);
	private static final HappyCubeUnfolder unfolder = HappyCubeUnfolder.getInstance();
	
	public static void main(String args[]) {

		try{
			
		List<CubePiece> piecesBlue = InputFactory.getPieces();
		solve(piecesBlue);

		}catch(SolverException e){
			System.out.println("Error occured in solving the pieces. " + e.getMessage());
		}catch(Exception e){
			System.out.println("Error solving the pieces");
			e.printStackTrace();
		}

	}

	private static void solve(List<CubePiece> pieces){
		
		if (solver.solve(pieces))
			unfolder.unfoldAndPrintToFile(pieces, OUTPUT_FILE_PATH);
		else
			System.out.println(ErrorMessage.NO_SOLUTION_FOUND.getMessage());
	}
}