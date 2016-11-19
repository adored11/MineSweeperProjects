import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by john on 13/10/2016.
 */
public class MineSweeperTest {
    MineSweeper mineSweeper = new MineSweeper();
    //int gameDimensions[][] = mineSweeper.;
    int gameDimensions[][] = new int[][]{ {2,3}, {4,5}, {5,6}, {10,10}, {10,0} };
    /*public final void checkNumLines() {
        int[][] result = mineSweeper.getValidGameDimensions(gameDimensions);
        assertTrue("Array is populated", result.length > 0);
    }*/

    /*@Test
    public final void  checkNumSquares() {
        int num1;
        int num2;
      for (int i = 0; i<gameDimensions.length; i++){
          num1 = gameDimensions[i][0]; //1st number of multi-dimenstional array
          num2 = gameDimensions[i][1]; // 2nd number of multi-dimenstional array
          int result = mineSweeper.getNumberOfSquares(num1, num2);
          if (num1 * num2) assert result==10;
          if (i==1) assert result==18;
          if (i==2) assert result==28;
          if (i==3) assert result==0;
          if (i==4) assert result==1;
       }
    }*/

    /*@Test
    public final void checkCreateGame(){
        //int numGames = checkNumLines();
        //assert numGames == 3;
        Map result =  mineSweeper.createGameBoard(mineSweeper.getValidGameDimensions(gameDimensions));
    }*/

}
