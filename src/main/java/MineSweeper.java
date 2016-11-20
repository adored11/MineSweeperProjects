import java.util.*;

/**
 * Created by john on 13/10/2016.
 */
public class MineSweeper {
    MineSweeperUtils utils = new MineSweeperUtils();

    public static void main(String [] args){
        Scanner scanner = new Scanner (System.in);
        StringBuffer sb = new StringBuffer();
        List<String> validatedGameDimensions = new ArrayList<String>();

        while (true) {
            System.out.print("Enter Dimension (x): ");
            String rowLength = scanner.next();
            System.out.print("Enter Dimension (y): ");
            String numColumns = scanner.next();

            if ( ((Integer.valueOf(numColumns) == 0) || (Integer.valueOf(rowLength) == 0)) ||
                    ((Integer.valueOf(numColumns) > 100) || (Integer.valueOf(rowLength) > 100))){
                break;
            }
            sb.append(rowLength);
            sb.append(numColumns);
            validatedGameDimensions.add(rowLength + "," + numColumns);
        }

        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.createGameBoard(validatedGameDimensions);
    }

    public Map<String, String> createGameBoard(List<String> validatedGameDimensions) {
        int numMines;
        String []numRowCharactersString = new String[2];
        int numRowCharacters = 0;
        int numColCharacters = 0;
        List initialLayoutList = new ArrayList();
        List gameBoardLayoutList = new ArrayList();
        Map finalGameOutputMap = new LinkedHashMap<Integer, String>();
        int gameNumber=1;
        // split each list item into 2 parts - numrows and numCols
        for (String newGameDimensions : validatedGameDimensions){
            numRowCharactersString = newGameDimensions.split(",");
            numRowCharacters = Integer.valueOf(numRowCharactersString[0]);
            numColCharacters = Integer.valueOf(numRowCharactersString[1]);
            numMines = utils.getNumMines(numColCharacters, numRowCharacters);
            StringBuilder b = new StringBuilder();

            // Fill each row with "0"'s then convert each row to a List entry.
            for (int j = 0; j<numColCharacters; j++) {
                for (int k = 0; k<numRowCharacters; k++) {
                    b.append("0");
                }
                initialLayoutList.add(j, utils.addToList(b.toString()));
                b.delete(0, b.length());
            }
            System.out.println("Field #" + gameNumber);

            // Pass each List row into method insertMines to populate the mines on the game board
            gameBoardLayoutList = insertMines(initialLayoutList, numMines);

            // Replace "0"'s with appropriate number flags around mines
            finalGameOutputMap = insertMarkers(gameBoardLayoutList);
            //System.out.println("finalGameOutputMap = " + finalGameOutputMap);

            StringBuilder outputLine = new StringBuilder();
            int rowPositionCount=1;
            for (int i = 1; i<=finalGameOutputMap.size(); i++) {
                if(rowPositionCount<=numRowCharacters){
                    outputLine.append(finalGameOutputMap.get(i)); //add character at position i
                    rowPositionCount++;
                }
                else {
                    System.out.println(outputLine.toString()); //print output line
                    rowPositionCount=1;
                    i--;    //subtract 1 from the current loop to redo the current iteration since we've reached the start of a new row
                    outputLine.delete(0, outputLine.length()); //clear stringBuilder
                }
            }

            System.out.println(outputLine.toString());  // print final output line and clear objects
            outputLine.delete(0, outputLine.length());
            initialLayoutList.clear();
            gameBoardLayoutList.clear();
            System.out.println("\n");
            gameNumber++;
        }
        return finalGameOutputMap;
    }

    public List insertMines(List initialLayoutList, int numMines){
        System.out.println("Maximum number of Mines = " + numMines);
        String getRandRowContent;
        String rowMinusBrackets;
        StringBuffer mineString = new StringBuffer();
        int listSize = initialLayoutList.size();
        int newRowLength;
        int getMinePosition;
        int getRandRowNumber=0;

        for (int i = 0; i<numMines; i++) {
            getRandRowNumber = utils.getRandCoord(listSize);
            //System.out.println("getRandRowNumber = " + getRandRowNumber);
            getRandRowContent = initialLayoutList.get(getRandRowNumber).toString();
            rowMinusBrackets = getRandRowContent.substring(1, (getRandRowContent.length() - 1));
            //System.out.println("rowMinusBrackets = " + rowMinusBrackets);
            newRowLength = rowMinusBrackets.length();
            //System.out.println("newRowLength = " + newRowLength);
            getMinePosition = utils.getRandCoord(newRowLength);
            //System.out.println("getMinePosition = " + getMinePosition);

            // loop through by row length. getMinePosition starts at position 0 hence (getMinePosition + 1)
            // add the character at each position to buffer - mineString at minePosition append a "*"
            for (int rowPosition = 1; rowPosition <= rowMinusBrackets.length(); rowPosition++) {
                if (rowPosition < (getMinePosition + 1)) {
                    mineString.append(rowMinusBrackets.charAt(rowPosition - 1));
                } else if (rowPosition == (getMinePosition + 1)) {
                    mineString.append("*");
                } else {
                    mineString.append(rowMinusBrackets.charAt(rowPosition - 1));
                }
            }
            mineString.insert(0, "[");
            mineString.append("]");
            //System.out.println("mineString = " + mineString);
            initialLayoutList.set(getRandRowNumber, mineString.toString());
            mineString.delete(0, mineString.length());
        }
        return initialLayoutList;
    }

    public LinkedHashMap<Integer, String> insertMarkers(List gameBoardLayoutList){
        LinkedHashMap<Integer,String> gameBoardLayoutMap = new LinkedHashMap<Integer, String>();
        String rowToString;
        String rowMinusBrackets;
        int rowStringLength;
        rowToString = gameBoardLayoutList.get(0).toString();
        rowStringLength = rowToString.length() -2;
        //System.out.println("rowStringLength = " + rowStringLength);
        int keyPlaceHolder = 0;

        // For each row in the list, loop through each character and copy the value to Map gameBoardLayoutMap
        for (int rowNumber = 0; rowNumber<gameBoardLayoutList.size(); rowNumber++){
            rowToString = gameBoardLayoutList.get(rowNumber).toString();
            //System.out.println("rowToString = " + rowToString);
            rowMinusBrackets = rowToString.substring(1, (rowToString.length() - 1));
            //System.out.println("rowMinusBrackets = " + rowMinusBrackets);
            rowStringLength = rowMinusBrackets.length();
            for (int rowPosition = 0; rowPosition<rowStringLength; rowPosition++){
                //System.out.println("key = " + (keyPlaceHolder) + ", " + "value = "+ rowMinusBrackets.charAt(rowNumber) );
                keyPlaceHolder++;
                gameBoardLayoutMap.put((keyPlaceHolder), String.valueOf(rowMinusBrackets.charAt(rowPosition)));
            }
        }
        //System.out.println(Arrays.asList(gameBoardLayoutMap));
        String mapSquareCharacter;
        int key = 0;
        // Loop through gameBoardLayoutMap and extract key and value
        for (Map.Entry<Integer, String> square : gameBoardLayoutMap.entrySet()) {
            key = square.getKey();
            mapSquareCharacter = square.getValue();
            //System.out.println("key- "+ key + " " + "value- " + squareChar);

            // Count surrounding squares to the mine "*" - different depending on mine position on the grid
            if (mapSquareCharacter.equals("*")) {
                int []numberOfSurrounding = utils.countSurroundingSquares(gameBoardLayoutMap, key, rowStringLength);
                //System.out.println("numberOfSurrounding Square = " + Arrays.toString(numberOfSurrounding));

                // for each surrounding square, lookup the value from key and pass to returnChar() if it's NOT a mine (*)
                // This will pass back a number relating to the number of mines near each square
                for (int keyNumber=0; keyNumber<numberOfSurrounding.length; keyNumber++){
                    int keyValue = numberOfSurrounding[keyNumber];
                    String mapEntryAtKey = gameBoardLayoutMap.get(keyValue);
                    if (!(mapEntryAtKey.equals("*")) ) {
                        gameBoardLayoutMap.put(numberOfSurrounding[keyNumber], utils.returnChar(mapEntryAtKey));
                    }
                    //System.out.println("gameBoardLayoutMap = " + Arrays.asList(gameBoardLayoutMap));
                }
            }
        }
        return gameBoardLayoutMap;
    }
}