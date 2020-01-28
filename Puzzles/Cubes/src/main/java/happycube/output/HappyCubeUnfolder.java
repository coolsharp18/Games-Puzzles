package happycube.output;

import static core.cube.Cube.BACK;
import static core.cube.Cube.BOTTOM;
import static core.cube.Cube.FRONT;
import static core.cube.Cube.LEFT;
import static core.cube.Cube.RIGHT;
import static core.cube.Cube.TOP;
import static happycube.config.CubeParameters.PIECE_SIDE_LENGTH;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import core.output.CubeUnfolder;
import core.piece.CubePiece;
import happycube.pieces.Corner;

/**
 * {@link CubeUnfolder} implementation to Unfold 
 * passed cube pieces.
 * 
 * @author Anand Kumar
 *
 */
public class HappyCubeUnfolder implements CubeUnfolder {

	private static HappyCubeUnfolder unfolder;
	
	private HappyCubeUnfolder() {
		// TODO Auto-generated constructor stub
	}
	/**
	 *  
	 * @return Singleton instance of {@link HappyCubeUnfolder}
	 */
	public static HappyCubeUnfolder getInstance(){
		if(unfolder == null){
			unfolder = new HappyCubeUnfolder();
		}
		
		return unfolder;
	}
	private String unfold(List<CubePiece> pieces){
		

		List<Corner[]> pointsLeft = pieces.get(LEFT).getStartEndPoints();
		List<Corner[]> pointsBottom = pieces.get(BOTTOM).getStartEndPoints();
		List<Corner[]> pointsRight = pieces.get(RIGHT).getStartEndPoints();
		List<Corner[]> pointsBack = pieces.get(BACK).getStartEndPoints();

		flipHorizontal(pieces.get(TOP));
		List<Corner[]> pointsTop = pieces.get(TOP).getStartEndPoints();

		flipHorizontal(pieces.get(FRONT));
		List<Corner[]> pointsFront = pieces.get(FRONT).getStartEndPoints();

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < PIECE_SIDE_LENGTH; i++) {

			builder.append(asString(pieces.get(LEFT).getSide(pointsLeft.get(i)[0], pointsLeft.get(i)[1])));
			builder.append("  ");
			builder.append(asString(pieces.get(BOTTOM).getSide(pointsBottom.get(i)[0], pointsBottom.get(i)[1])));
			builder.append("  ");
			builder.append(asString(pieces.get(RIGHT).getSide(pointsRight.get(i)[0], pointsRight.get(i)[1])));
			builder.append(System.lineSeparator());
		}
		builder.append(System.lineSeparator());
		builder.append(getPieceWithOffset(pointsFront, pieces.get(FRONT)));
		builder.append(System.lineSeparator());
		builder.append(getPieceWithOffset(pointsTop, pieces.get(TOP)));
		builder.append(System.lineSeparator());
		builder.append(getPieceWithOffset(pointsBack, pieces.get(BACK)));

		return builder.toString();
		
	
	}
	/**
	 * Unfold and print passed {@link List}<{@link CubePiece}> pieces to the console.
	 */
	@Override
	public void unfoldAndPrintToConsole(List<CubePiece> pieces) {
		
		System.out.println(unfold(pieces));
	}

	private static String asString(String[] side) {
		StringBuilder b = new StringBuilder();
		for (String a : side) {
			b.append(a);
		}

		return b.toString();
	}
	
	private static String getPieceWithOffset(List<Corner[]> points, CubePiece piece) {

		String offset = "            ";
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < PIECE_SIDE_LENGTH; i++) {

			builder.append(offset);
			builder.append(asString(piece.getSide(points.get(i)[0], points.get(i)[1])));
			builder.append(offset);
			builder.append(System.lineSeparator());
		}

		return builder.toString();

	}

	/**
	 * Flips the {@link CubePiece} along horizontal axis
	 * 
	 * @param piece
	 */
	private static void flipHorizontal(CubePiece piece) {

		piece.rotate(true);
		piece.rotate(true);
		piece.flip();
	}
	
	
	@Override
	public void unfoldAndPrintToFile(List<CubePiece> pieces, String filePath) {
		
		Path outputFile = Paths.get(filePath);
		try(Writer writer = new BufferedWriter(new FileWriter(outputFile.toFile()))) {
			
			writer.write(unfold(pieces));
			writer.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error printing output to file, printing output to console.");
			unfoldAndPrintToConsole(pieces);
		}
		
	}

}
