package happycube.input;

public enum InputColors {

	BLUE, RED, YELLOW, PURPLE;
	
	private static String validColors = "";
	
	static {
		for(InputColors color : values()){
			validColors = validColors + color.name() + " ,";
		}
	}
	
	public static String validColors(){
		
		return validColors.substring(0, validColors.length() - 2);
	}
}
