package happycube.input;

import java.util.List;

import core.piece.CubePiece;
import happycube.config.CubeParameters;
import happycube.exception.SolverException;

public class InputFactory {

	public static List<CubePiece> getPieces(){
		
		if(InputColors.BLUE.name().equalsIgnoreCase(CubeParameters.INPUT_COLOR)){
			return InputBlue.getPieces();
		}
		else if(InputColors.RED.name().equalsIgnoreCase(CubeParameters.INPUT_COLOR)){
			return InputRed.getPieces();
		}
		else if(InputColors.PURPLE.name().equalsIgnoreCase(CubeParameters.INPUT_COLOR)){
			return InputPurple.getPieces();
		}
		else if(InputColors.YELLOW.name().equalsIgnoreCase(CubeParameters.INPUT_COLOR)){
			return InputYellow.getPieces();
		}
		else{
			 throw new SolverException("Invalid Cube color chosen! Valid colors are : " + InputColors.validColors());
		}
		
	}
}
