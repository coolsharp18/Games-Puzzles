package happycube.input;


import java.util.ArrayList;
import java.util.List;

import core.piece.CubePiece;
import happycube.pieces.HappyCubePiece;

public enum InputPurple { 

	PIECE_1(new String[][]{
			{"[]","[]","  ","[]","  "},
			{"[]","[]","[]","[]","  "},
			{"[]","[]","[]","[]","  "},
			{"  ","[]","[]","[]","[]"},
			{"  ","  ","[]","  ","  "}
			}
			),
	
	PIECE_2(new String[][]{
			{"  ","  ","  ","[]","[]"},
			{"[]","[]","[]","[]","  "},
			{"[]","[]","[]","[]","[]"},
			{"  ","[]","[]","[]","  "},
			{"  ","[]","  ","[]","  "}
			}
			),
	
	PIECE_3(new String[][]{
			{"  ","[]","  ","  ","  "},
			{"[]","[]","[]","[]","  "},
			{"  ","[]","[]","[]","[]"},
			{"[]","[]","[]","[]","  "},
			{"  ","  ","[]","  ","  "}
			}
			),
	
	PIECE_4(new String[][]{
			{"[]","[]","  ","[]","[]"},
			{"  ","[]","[]","[]","[]"},
			{"[]","[]","[]","[]","  "},
			{"  ","[]","[]","[]","  "},
			{"  ","[]","  ","[]","  "}
			}
			),
	
	PIECE_5(new String[][]{
			{"  ","  ","[]","  ","[]"},
			{"  ","[]","[]","[]","[]"},
			{"[]","[]","[]","[]","[]"},
			{"[]","[]","[]","[]","  "},
			{"[]","  ","[]","[]","  "}
			}
			),
	
	PIECE_6(new String[][]{
			{"  ","[]","  ","[]","[]"},
			{"  ","[]","[]","[]","  "},
			{"  ","[]","[]","[]","[]"},
			{"[]","[]","[]","[]","  "},
			{"[]","[]","  ","[]","  "}
			}
			);
	
	String [][] piece;

	static List<CubePiece> pieces = new ArrayList<>();
	
	static {
		for(InputPurple piece : values()){
			pieces.add(new HappyCubePiece(piece.getPiece()));
		}
		
	}
	private String[][] getPiece() {
		return piece;
	}
	
	public static List<CubePiece> getPieces(){
		return pieces;
	}

	private InputPurple(String[][] piece) {
		this.piece = piece;
	}

}

