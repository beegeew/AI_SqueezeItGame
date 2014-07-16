
package squeezeboard;

import java.util.ArrayList;

public class boardState {
    
    //Constructor that setups the board to the defined max # of boards, nxn size
    //and sets the computer's color. It will also setup the board to the default
    //state of pieces at the top or bottom - the pieces colors can be changed 
    //simply by altering the color set when at the top or bottom row. 
    public boardState(int max, int size, char compCol) {
        currBoard = 0;
        maxBoards = max;
        maxSize = size;
        compColor = compCol;
        setOppColor();
        
        boards = new char[maxBoards][size][size];
        
        for (int i = 0; i < maxBoards -1; i++){
            for (int j = 0; j < maxSize; j++){
                for (int k = 0; k < maxSize; k++){
                    if (j == 0)
                        boards[i][j][k] = 'r';
                    else if (j == maxSize - 1)
                        boards[i][j][k] = 'w';
                    else
                        boards[i][j][k] = 'b';
                }
            }
        }
    };
    
    //Get the current type of cell for only the current board.
    public char getCellType(int x, int y) {
        return boards[currBoard][x][y];
    }
    
    //We can change the value of any cell at any time on the current board
    //This function should be used when the game is not in play
    public void setCellType(int x, int y, char newChar) {
        if (x < maxSize && y < maxSize && (newChar == 'r' || newChar == 'b' || newChar == 'w') )
            boards[currBoard][x][y] = newChar;
    }
    
    //This functions just changes the board to any board in the range of possible
    //preserved boards
    public void setCurrBoard(int newBoard) {
        if (newBoard < maxBoards && newBoard > -1)
            currBoard = newBoard;
    }
        
    //Returns the value of the current board that is selected to be displayed
    //on the board.
    public int getCurrBoard() {
        return currBoard;
    }
    
    //If a new move is made - we want to record the current board state and 
    //create a new board up to the max boards, otherwise we will copy each board
    //to it's ancestor if the max amount of boards has been reached and then
    //change the value of the cell input to the supplied color.
    public int newMove(int x1, int y1, int x2, int y2, char col) {
        if (currBoard == maxBoards - 1){
            for (int i = 0; i < maxBoards - 1; i++)
                for (int j = 0; j < maxSize; j++)
                    for (int k = 0; k < maxSize; k++)
                        boards[i][j][k] = boards[i+1][j][k];            
        } else{
            currBoard++;
            for (int j = 0; j < maxSize; j++)
                for (int k = 0; k < maxSize; k++)
                    boards[currBoard][j][k] = boards[currBoard-1][j][k];
        }
        setCellType(x1,y1,'b');
        setCellType(x2,y2,col);
        
        ArrayList<int[]> squeezedCells = getAllSqueezed(x2,y2,col);
        for (int i = 0; i < squeezedCells.size();  i++)
            setCellType(squeezedCells.get(i)[0], squeezedCells.get(i)[1], 'b');
      
        if ( col == compColor)
            return squeezedCells.size();
        else
            return -squeezedCells.size();
    }
    
    //This is the function that searches from a list of valid moves for the best possible move available. 
    //It considers the value of it's own possible moves and the value of the opponents possible moves
    //which are negative values. Computer squeezes are considered positive values. The function then 
    //will execute the best possible move from the compiled new possible positions and play out the move. 
    //If there are many moves with the same value, the ideal move will be an agressive move that 
    //attempts to move next to an opponent to setup a squeeze for the next move, or cause the opponent
    //to make a move that will lead to a squeeze. 
    public int makeCompMove(){
        ArrayList<int[]> store=new ArrayList<int[]>();
        ArrayList<Integer> movesList = new ArrayList<Integer>();
        for (int j = 0; j < maxSize; j++){
            for (int k = 0; k < maxSize; k++){
                if (boards[currBoard][j][k] == compColor){
                    int[][] moves=validMoves(j,k);
                    for(int i=0;i<moves.length;i++){
                        movesList.add(newMove(j,k,moves[i][0], moves[i][1], compColor));   
                        int[] temp={j,k,moves[i][0], moves[i][1],generateOppMoves(moves[i][0], moves[i][1], oppColor)};
                        store.add(temp);
                        currBoard--;
                    }
                }
            }
        }
        int best=store.get(0)[4]+movesList.get(0);
        int index=0;
        
        //This loop searches through the list of compiled moves and their values and combines
        //the best computer and likely predicted opponent move. If there is no move which obtains
        //points for either player, the computer will try to find a cell that moves it closer
        //to an opponent piece based on moves that may improve the chance of getting a squeeze on
        //future moves
        for(int i = 0; i < store.size(); i++){
            if((store.get(i)[4]+movesList.get(i)) >best){
                best=store.get(i)[4]+movesList.get(i);
                index=i;
            }
            else if((store.get(i)[4]+movesList.get(i))==best){
                if(store.get(i)[2]+1 !=8 && store.get(i)[3]+1 !=8 && store.get(i)[2]-1 !=-1 && store.get(i)[3]-1 !=-1){
                    if(getCellType(store.get(i)[2]+1, store.get(i)[3])==oppColor || getCellType(store.get(i)[2]-1, store.get(i)[3])==oppColor || getCellType(store.get(i)[2], store.get(i)[3]+1)==oppColor || getCellType(store.get(i)[2], store.get(i)[3]-1)==oppColor){
                        best=store.get(i)[4]+movesList.get(i);
                        index=i;
                    }
                }
            }
        }

        return newMove(store.get(index)[0], store.get(index)[1], store.get(index)[2], store.get(index)[3], compColor);
    }
    
    //This function returns the value of the opponents move in an integer value. All possible valid
    //moves from a new board state are added to a list of moves and then possible results of the 
    //move are added to an arraylist. If the array list is empty, the opponent has likely lost
    //all of their game pieces and we give a positive value as a result which helps the program
    //single out this move as ideal and a game winning move. Otherwise, we look for the best move 
    //that the opponent would likely take and return the value of the move to the calling 
    //computer move function. This function will also look to the next level of possible computer
    //moves via a recursive call and may sacrifice a single piece to capture an opponent piece or pieces.
    public int generateOppMoves(int xCoord,int yCoord, char playerColor){
        ArrayList<Integer> store=new ArrayList<Integer>();
        for (int j = 0; j < maxSize; j++){
                for (int k = 0; k < maxSize; k++){
                    if (boards[currBoard][j][k] == playerColor){
                        int[][] moves=validMoves(j,k);
                        for(int i=0;i<moves.length;i++){
                            if(playerColor==oppColor){
                                store.add(newMove(j,k,moves[i][0], moves[i][1], playerColor)+generateOppMoves(moves[i][0], moves[i][1], compColor));
                            }
                            else{
                                store.add(newMove(j,k,moves[i][0], moves[i][1], playerColor));
                            }
                            currBoard--;
                        }
                    }
                }
            }
        if (store.isEmpty() && playerColor==oppColor){
            return 1;          //In the event that the opponent is left with no valid moves
        } else if(store.isEmpty())
            return -100;
        
        //If the player moving is the opponent, we are looking for the best opponent move
        //and look for the lowest value, if the player moving is the computer, we look for
        //the highest value. 
        int best=store.get(0);
        for(int i=0;i<store.size();i++){
            if(playerColor==oppColor && store.get(i)<best){
                best=store.get(i);
            }
            else if(playerColor==compColor && store.get(i)>best){
                best=store.get(i);
            }
        }
        return best;
    }
    
    //Returns an integer array of all possible moves for a piece given its coordinates.
    //We consider valid moves to only be those in any x or y positiong aligned with the position
    //of the selected piece and do not allow jumping over other pieces. This function generates
    //a 2d array of positions for moves above, below, to the left and right of the supplied
    //coordinates and will stop when a non-blank is found.
    public int[][] validMoves(int xcoord, int ycoord){
        int[][] moves=new int[14][2];
        int step=1;
        int i=0;
        for(int j=0;j<2;j++){
            step=(int)Math.pow(-1, j);
            while(xcoord+step!=maxSize && xcoord+step!=-1 && getCellType(xcoord+step,ycoord) == 'b'){
                moves[i][0]=xcoord+step;
                moves[i][1]=ycoord; 
                step+=(int)Math.pow(-1, j);
                i++;
            }
            step=(int)Math.pow(-1, j);
            while(ycoord+step!=maxSize && ycoord+step!=-1 && getCellType(xcoord,ycoord+step) == 'b'){
                moves[i][0]=xcoord;
                moves[i][1]=ycoord+step;
                step+=(int)Math.pow(-1, j);
                i++;
            }
            step=(int)Math.pow(-1, j);
        }
        int[][] export=new int[i][2];
        
        for(int k=0;k<i;k++){
            export[k][0]=moves[k][0];
            export[k][1]=moves[k][1];
        }
        
        return export;
    }
    
    //This gets the value of a move of a piece to a certain position on the
    //board and returns an integer arraylist with the row, column of the
    //move. With the player color recorded of who is moving, it will generate
    //a list of all the squeezed pieces of a single move.
    public ArrayList<int[]> getAllSqueezed(int row, int col, char whoMove) {
        int currSqueezeValue = 0, i = 0, j = 0;
        ArrayList<int[]> squeezedCells = new ArrayList<int[]>(), newSqueeze = new ArrayList<int[]>();
        
        //let's first check for a squeeze in the row, start from 0, then end of row
        //and add or subtract depending if we are looking at our moves or our
        //opponents moves. We end at 2 away from the end of the array due to the 
        //fact that if we didn't find a squeeze by then, there will be no 
        //squeeze in the row or column
        while ( j < maxSize -1) {
            newSqueeze = findSqueeze(row,j,'r',whoMove);
            currSqueezeValue = newSqueeze.size() - currSqueezeValue;
            if (currSqueezeValue > 0) {
                j += currSqueezeValue;
                squeezedCells.addAll(newSqueeze);
            } else 
               j++;
        }
        //Look at the columns for squeezes using the same method as above
        while ( i < maxSize - 1) {
            newSqueeze = findSqueeze(i,col,'c',whoMove);
            currSqueezeValue = newSqueeze.size() - currSqueezeValue;
           if (currSqueezeValue > 0) {
               i += currSqueezeValue;
               squeezedCells.addAll(newSqueeze);
           } else 
               i++;
        }

        return squeezedCells;
    }
    
    //Finds a single squeeze based on a start position and if we are looking
    //for a row or column squeeze. This function returns an array list of integer pairs
    //indicating the row and column of a squeezed cell
    private ArrayList<int[]> findSqueeze(int row, int col, char squeezeType, char whoMove) {
        int index = 0;
        int[] tempItem = new int[2];
        ArrayList<int[]> squeezedCells = new ArrayList<int[]>();
        char squeezeColor;
        
        if (whoMove == 'r')
            squeezeColor = 'w';
        else
            squeezeColor = 'r';
        
        //If the start position is a blank cell, there's no squeeze.
        if (boards[currBoard][row][col] == 'b') {
            return squeezedCells;
        }
        //Otherwise, check to see if we're looking for a row squeeze.  If so, then
        //set our index to two away from the current column value. This allows
        //us to check for a bookend type squeeze. 
        else if (squeezeType == 'r'){
            index = col+2;
            
            //If the next cell is blank or the same color, there's no squeeze
            //starting at this position
            if ( boards[currBoard][row][col] == boards[currBoard][row][col+1]
                    || boards[currBoard][row][col+1] == 'b') {
                return squeezedCells;
            }

            //Otherwise, let's iterate through the row looking at the two ends 
            //and seeing if they are the same while the index is still within
            //a valid range.
            while (index < maxSize ) {
                //If we've found two ends that are the same color, here is our
                //squeeze, if the ends are the opponents color, then we moved 
                //internally to create the squeeze IE if we are red, we made a
                //WRRW type pattern, and we eliminate 2 by default. Otherwise, 
                //we iterate until just before the index and add the row and current
                //column to the arraylist. Once we've found a squeeze and added it 
                //to the array list, we return the array list to avoid finding
                //multiple squeeze patterns that may involve the same cell.
                if (boards[currBoard][row][index] == boards[currBoard][row][col]) {
                    if (boards[currBoard][row][index] == squeezeColor) {
                        tempItem = new int[] {row,index};
                        squeezedCells.add(tempItem);
                        tempItem = new int[] {row,col};
                        squeezedCells.add(tempItem);
                        return squeezedCells;
                    } else {
                        for (int i = col+1; i < index; i++) {
                            tempItem = new int[]{row,i};
                            squeezedCells.add(tempItem);
                        }
                        return squeezedCells; 
                    }
                }
                //If we hit a blank before seeing another color that completes the
                //squeeze, there is no squeeze
                else if (boards[currBoard][row][index] == 'b')
                    return squeezedCells;
                index++;
            }            
        } else if (squeezeType == 'c') {
            //Column squeeze checker works the same as the row except it iterates
            //through the possible valid row positions.
            index = row+2;
            if ( boards[currBoard][row][col] == boards[currBoard][row+1][col]
                    || boards[currBoard][row+1][col] == 'b')
                return squeezedCells;
            while (index < maxSize ) {
                if (boards[currBoard][index][col] == boards[currBoard][row][col]) {
                    if (boards[currBoard][index][col] == squeezeColor) {
                        tempItem = new int[] {index,col};
                        squeezedCells.add(tempItem);
                        tempItem = new int[] {row,col};
                        squeezedCells.add(tempItem);
                        return squeezedCells;
                    } else {
                        for (int i = row+1; i < index; i++) {
                            tempItem = new int[]{i,col};
                            squeezedCells.add(tempItem); 
                        }
                        return squeezedCells;
                    }
                }
                else if (boards[currBoard][index][col] == 'b')
                    return squeezedCells;
                index++;
            }
        }
        //If we reach this section, no squeeze was ever found.
        return squeezedCells;
    }
    
    //Return the maximum # of boards.
    public int getMaxBoards() {
        return maxBoards;
    }
    
    //Sets up the computer's color so it knows which pieces to move then calls
    //the setOppColor function
    public void setCompColor(char newCol) {
        compColor = newCol;
        setOppColor();
    }
    
    //Set opp color determines the opponents color based on the current computer
    //color and is only used by the class itself and thus is private.
    private void setOppColor() {
        if (compColor == 'r')
            oppColor = 'w';
        else
            oppColor = 'r';
    }
    
    //get the opponents color
    public char getOppColor() {
        return oppColor;
    }
    
    private char compColor;
    private char oppColor;
    private char boards[][][];
    private int currBoard;
    private int maxBoards;
    private int maxSize;
}
