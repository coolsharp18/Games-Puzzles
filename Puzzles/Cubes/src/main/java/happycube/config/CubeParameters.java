package happycube.config;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import happycube.exception.SolverException;

public class CubeParameters {

	private static final Path propertiesFile = Paths.get("config/app.properties");
	private static final Properties properties = new Properties();
	private static final String pieceSideLength = "cube.piecesidelength";
	private static final String cubeplug = "cube.plug";
	private static final String cubesocket = "cube.socket";
	private static final String outputfilepath = "cube.outputfilepath";
	private static final String inputColor = "cube.color";
	
	static
	{
		
	try {
		properties.load(new FileReader(propertiesFile.toString()));
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.exit(1);
	}
	}
	
	public static final Integer PIECE_SIDE_LENGTH = getIntegerValue(pieceSideLength);
	
	public static final String CUBE_PLUG = properties.getProperty(cubeplug);
	
	public static final String CUBE_SOCKET = properties.getProperty(cubesocket);
	
	public static final String OUTPUT_FILE_PATH = properties.getProperty(outputfilepath);
	
	public static final String INPUT_COLOR = properties.getProperty(inputColor);
	
	private static Integer getIntegerValue(String propertyName){
		Integer value;
		try{
			value = Integer.parseInt(properties.getProperty(propertyName));
		}catch (NumberFormatException e) {
			throw new SolverException("Unable to parse property : " + propertyName);
		}
		
		return value;
	}
}
