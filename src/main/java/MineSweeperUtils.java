import java.util.*;

/**
 * Created by john on 16/10/2016.
 */
public class MineSweeperUtils {

    public int getNumMines(int numColumns, int numRows){
        int gameBoardSize = getNumberOfSquares(numColumns, numRows);
        int numMines = gameBoardSize/4;
        return numMines;
    }

    public int getRandCoord(int i){
        Random rand = new Random();
        int coord=0;
        coord = rand.nextInt(i);
        //System.out.println("random = " + coord);
        return  coord;

    }

    public List addToList(String sb){
        List<String> lst = new ArrayList<String>();
            lst.add(sb);
            //System.out.println("List Entry- " + lst.get(0).toString());
        return lst;
    }

    // Work out how many squares surround each mine. key param is the position of the mine in the map
    public int [] countSurroundingSquares(LinkedHashMap gameBoardLayoutMap, int key, int rowStringLength){

        // Check where in the grid the mine is. Options are -
        // (1) the four corners (3 surrounding squares)
        // (2) squares in-between the 4 corners on the outer edges of the grid (5 surrounding squares)
        // (3) the inner sqaures (8 surrounding squares)

        if (key<=rowStringLength){ //1st row
            if( ((key-1)%rowStringLength)==0) {      //1st row 1st column

                int[] surrounding = {2, (1 + rowStringLength), (2 + rowStringLength)};
                return surrounding;
            }
            else if (key == rowStringLength){        //1st row last column
                int []surrounding = {(key-1), (key+rowStringLength), (key+rowStringLength-1)};
                return surrounding;
            }
            else{  //squares in-between the 4 corners on the outer edges of the grid (top row)
                int [] surrounding = {(key-1), (key+1), (key+rowStringLength), (key+rowStringLength+1), (key+rowStringLength-1)};
                return surrounding;
            }
        }
        else if (key > (gameBoardLayoutMap.size() - rowStringLength) ){ //last row
            if((key-1)%rowStringLength==0) {    //last row 1st column
                int[] surrounding = {(key + 1), (key - rowStringLength + 1), (key - rowStringLength)};
                return surrounding;
            }
            else if (key == gameBoardLayoutMap.size() ){   //last row last column
                int [] surrounding = {(key-1), (key-rowStringLength-1), (key-rowStringLength)};
                return surrounding;
            }
            else{  //squares in-between the 4 corners on the outer edges of the grid (bottom row)
                    int [] surrounding = {(key-1), (key+1), (key-rowStringLength), (key-rowStringLength+1),
                            (key-rowStringLength-1)};
                    return surrounding;
            }
        }
        else //squares in-between the 4 corners on the outer edges of the grid (left and right)
        {
            if( ((key-1)%rowStringLength)==0) {      //1st  column
                int[] surrounding = {(key-rowStringLength), (key-rowStringLength+1), (key+1), (key+rowStringLength),
                        (key+rowStringLength+1)};
                return surrounding;
            }
            if(key%rowStringLength==0) {    //last  column
                int[] surrounding = {(key - 1), (key - rowStringLength - 1), (key - rowStringLength), (key + rowStringLength),
                        (key + rowStringLength - 1)};
                return surrounding;
            }
            else {      //inner squares
                int[] surrounding = {(key-1),(key+1), (key-rowStringLength-1), (key-rowStringLength), (key-rowStringLength+1),
                        (key+rowStringLength+1), (key+rowStringLength), (key+rowStringLength-1) };
                return surrounding;
            }
        }
    }

    public String returnChar(String squareChar){
        int squareCharNextInt = Integer.valueOf(squareChar);
        switch (squareCharNextInt) {
            case 0:
                squareChar = "1";
                break;
            case 1:
                squareChar = "2";
                break;
            case 2:
                squareChar = "3";
                break;
            case 3:
                squareChar = "4";
                break;
            case 4:
                squareChar = "5";
                break;
            case 5:
                squareChar = "6";
                break;
            case 6:
                squareChar = "7";
                break;
            case 7:
                squareChar = "8";
                break;
            default:
                squareChar = squareChar;
                break;
        }
        return squareChar;
    }

    public int getNumberOfSquares(int columns, int rows){
        int totalSquares = columns * rows;
        //System.out.println("totalSquares = " + totalSquares);
        return totalSquares;
    }

}
