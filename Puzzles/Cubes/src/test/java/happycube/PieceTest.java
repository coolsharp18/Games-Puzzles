package happycube;

import java.util.List;

import core.cube.Cube;
import core.piece.CubePiece;
import happycube.input.InputBlue;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Pieces.
 */
public class PieceTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PieceTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PieceTest.class );
    }

    /**
     * Test Rotation and flip
     */
    public void testRotate()
    {
    	List<CubePiece> pieces = InputBlue.getPieces();
    	CubePiece bottomPiece = pieces.get(Cube.BOTTOM);
    	String topSide[];
    	String[] testTopSideOne = {"  ","[]","  ","[]","  "};
    	String[] testTopSideTwo = {"[]","  ","[]","  ","  "};
    	String[] testTopSideThree = {"  ","  ","[]","  ","[]"};
    	String[] testTopSideFour = {"  ","[]","  ","[]","  "};
    	
    	topSide = bottomPiece.getTopSide();
        assertTrue( match(topSide, testTopSideOne) );
        
        bottomPiece.rotate(true);
        topSide = bottomPiece.getTopSide();
        assertTrue( match(topSide, testTopSideTwo) );
        
        bottomPiece.flip();
        topSide = bottomPiece.getTopSide();
        assertTrue( match(topSide, testTopSideThree) );
        
        bottomPiece.rotate(true);
        topSide = bottomPiece.getTopSide();
        assertTrue( match(topSide, testTopSideFour) );
        
    }
    
    private boolean match(String a[], String[] b){
    	
    	for(int i = 0; i < a.length; i++){
    		if(!a[i].equals(b[i]))
    			return false;
    	}
    	
    	return true;
    }
}
