
package squeezeboard;

public class sqBoard extends javax.swing.JFrame {

    //Constructor to setup a new board with our 2d array of buttons which setup
    //the game playing board. All default values are set, such as the computer
    //player being the red color, the mode of the game (in play or not), a new 
    //boardstate setup with up to 100 boards, we draw the board and set the text
    //fields and default number of pieces. Since we don't know who will go first
    //we set the currentplayermove to 'n' which means none.
    public sqBoard() {
        initComponents();
        
        board = new sqButton[8][8];
                
        buttonHandler boardButton = new buttonHandler();
        
        for(int i = 0; i <8; i++) {
            for(int j = 0; j < 8; j++) {
                board[i][j] = new sqButton(i,j);
                playingArea.add(board[i][j]);
                board[i][j].addActionListener(boardButton);
            }
        }
        currCompColor = 'r';
        cells = new boardState(100,8,currCompColor);
        drawBoard();
        boardMode = 'n';
        gameMode.setText("Play Mode Off");
        compColor.setText("Computer: Red");
        
        compPieces = 8;
        playerPieces = 8;
        updateCompPieces();
        updatePlayerPieces();
        updateMovesMade();
        currPlayerMove = 'n';
        undo = 0;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cMove = new javax.swing.JButton();
        Undo = new javax.swing.JButton();
        Redo = new javax.swing.JButton();
        resetBoard = new javax.swing.JButton();
        MovesMadeLabel = new javax.swing.JLabel();
        compColor = new javax.swing.JLabel();
        playingArea = new javax.swing.JPanel();
        setRed = new javax.swing.JButton();
        setWhite = new javax.swing.JButton();
        SetCompColor = new javax.swing.JLabel();
        startGame = new javax.swing.JButton();
        stopGame = new javax.swing.JButton();
        gameMode = new javax.swing.JLabel();
        compPiecesLabel = new javax.swing.JLabel();
        playerPiecesLabel = new javax.swing.JLabel();
        compPiecesDisplay = new javax.swing.JLabel();
        playerPiecesDisplay = new javax.swing.JLabel();
        movesMadeDisplay = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Squeeze-it Computer Game Player");
        setBackground(new java.awt.Color(0, 0, 255));
        setFont(new java.awt.Font("Batang", 1, 14));
        setResizable(false);

        cMove.setText("Computer Move");
        cMove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cMoveActionPerformed(evt);
            }
        });

        Undo.setText("Undo");
        Undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UndoActionPerformed(evt);
            }
        });

        Redo.setText("Redo");
        Redo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RedoActionPerformed(evt);
            }
        });

        resetBoard.setText("Reset Board");
        resetBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBoardActionPerformed(evt);
            }
        });

        MovesMadeLabel.setText("Moves Made:");

        playingArea.setAlignmentX(0.0F);
        playingArea.setAlignmentY(0.0F);
        playingArea.setMaximumSize(new java.awt.Dimension(320, 320));
        playingArea.setMinimumSize(new java.awt.Dimension(320, 320));
        playingArea.setPreferredSize(new java.awt.Dimension(320, 320));
        playingArea.setLayout(new java.awt.GridLayout(8, 8));

        setRed.setText("Red");
        setRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setRedActionPerformed(evt);
            }
        });

        setWhite.setText("White");
        setWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setWhiteActionPerformed(evt);
            }
        });

        SetCompColor.setText("Select Computer Color:");

        startGame.setText("Start Play");
        startGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameActionPerformed(evt);
            }
        });

        stopGame.setText("End Play");
        stopGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopGameActionPerformed(evt);
            }
        });

        compPiecesLabel.setText("Computer Pieces Left:");

        playerPiecesLabel.setText("Player Pieces Left:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(playingArea, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(MovesMadeLabel)
                                .addGap(40, 40, 40)
                                .addComponent(movesMadeDisplay)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gameMode)
                            .addComponent(SetCompColor)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(startGame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(stopGame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(setWhite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(compColor, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(resetBoard, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(setRed, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(compPiecesLabel)
                                .addGap(45, 45, 45)
                                .addComponent(compPiecesDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(playerPiecesLabel)
                                .addGap(41, 41, 41)
                                .addComponent(playerPiecesDisplay))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cMove, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Undo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Redo)))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(resetBoard)
                        .addGap(13, 13, 13)
                        .addComponent(SetCompColor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setRed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setWhite)
                        .addGap(71, 71, 71)
                        .addComponent(startGame)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopGame)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gameMode))
                    .addComponent(playingArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MovesMadeLabel)
                    .addComponent(compColor, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movesMadeDisplay))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cMove)
                    .addComponent(Undo)
                    .addComponent(Redo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(compPiecesLabel)
                    .addComponent(playerPiecesLabel)
                    .addComponent(compPiecesDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerPiecesDisplay))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //this function handles the action if the computer move button is selected. 
    //it makes the computer move, redraws the board, removes any player pieces
    //that got squeezed and sets the current player to the human player. 
    //if it is not currently the computer's turn, it will show an error and
    //if the game mode is not currently in play, then an error will show that the
    //game isn't currently active. 
private void cMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cMoveActionPerformed
    if (boardMode == 'p' && currPlayerMove != 'p' ) {
        int i = cells.makeCompMove();
        drawBoard();
        movesMade++;
        playerPieces-=i;
        updatePlayerPieces();
        updateMovesMade();
        currPlayerMove = 'p';
        cMove.setVisible(false);
    } else if (currPlayerMove == 'p') {
        playerMove();
    } else {
            javax.swing.JOptionPane.showMessageDialog(this,
            "The game is not currently in play.",
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_cMoveActionPerformed

    //This function handles the redo button which will set the board to a board
    //that may have been undone. It will ensure that the undo function has actually
    //been called before by checking the value of undo. If no undo has been run before
    //or there is no more possible move to redo and we are at the last board, then 
    //and error will be displayed. If the game is not currently in play, then an error
    //will inform the user of the current game state.
private void RedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RedoActionPerformed
    int boardNumber = cells.getCurrBoard();
    if (boardMode == 'p' && boardNumber < cells.getMaxBoards()-1 && undo > 0) {
        cells.setCurrBoard(boardNumber+1);
        drawBoard();
        undo--;
        movesMade++;
        updateMovesMade();
        currPlayerMove = 'n';
        
        compPieces = 0;
        playerPieces = 0;
        //This loop will update the player pieces for the player and computer
        for (int i = 0; i< 8; i++)
            for ( int j = 0; j < 8; j++) {
                char currCell = cells.getCellType(i,j);
                if (currCell == 'w' || currCell == 'r')
                    if (currCell == currCompColor) {
                        compPieces++;
                    } else 
                        playerPieces++;
            }
        updateCompPieces();
        updatePlayerPieces();
        cMove.setVisible(true);
    } else if (boardMode == 'p') {
        javax.swing.JOptionPane.showMessageDialog(this,
            "No more possible redo moves!",
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    } else {
        javax.swing.JOptionPane.showMessageDialog(this,
            "The game is not currently in play.",
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_RedoActionPerformed

    //This function returns the board to a previous state only if the game is in play
    //It will increment the undo counter if the board number is above zero, which means
    //the board is not in a start state. Otherwise, it will inform the user that
    //there are no more possible undo moves or that the game is not in a playing mode
private void UndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UndoActionPerformed
    int boardNumber = cells.getCurrBoard();
    if (boardMode == 'p' && boardNumber > 0) {
        cells.setCurrBoard(boardNumber-1);
        drawBoard();
        undo++;
        movesMade--;
        updateMovesMade();
        currPlayerMove = 'n';
        
        compPieces = 0;
        playerPieces = 0;
        //This loop will update the player pieces for the player and computer
        for (int i = 0; i< 8; i++)
            for ( int j = 0; j < 8; j++) {
                char currCell = cells.getCellType(i,j);
                if (currCell == 'w' || currCell == 'r')
                    if (currCell == currCompColor) {
                        compPieces++;
                    } else 
                        playerPieces++;
            }
        updateCompPieces();
        updatePlayerPieces();
        cMove.setVisible(true);
    } else if (boardMode == 'p') {
        javax.swing.JOptionPane.showMessageDialog(this,
            "No more possible undo moves!",
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    } else {
        javax.swing.JOptionPane.showMessageDialog(this,
            "The game is not currently in play.",
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_UndoActionPerformed

    //This button allows the play to reset the entire game state and return to the
    //initial default state. It will also turn the game mode to off and reset the computer
    //to red. All previous game board data will be gone after this button is pressed.
private void resetBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBoardActionPerformed
    currCompColor = 'r';
    cells = new boardState(100,8,currCompColor);
    drawBoard();
    boardMode = 'n';
    gameMode.setText("Play Mode Off");
    compColor.setText("Computer: Red");
    oppMoveStarted = false;
    compPieces = playerPieces = 8;
    movesMade = 0;
    updateCompPieces();
    updatePlayerPieces();
    updateMovesMade();
    currPlayerMove = 'n';
    undo = 0;
    cMove.setVisible(true);
}//GEN-LAST:event_resetBoardActionPerformed

    //This function is used when the red mode button is selected. If the game is in 
    //play mode, the function will produce an error message. Otherwise, the game will
    //update the computer color and display, and will appropriately swap the piece counts
    //and their display.
private void setRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setRedActionPerformed
    if (boardMode == 'p')
        failedColorChange();
    else {
        currCompColor = 'r';
        cells.setCompColor(currCompColor);
        compColor.setText("Computer: Red");
        
        compPieces+=playerPieces;
        playerPieces=compPieces-playerPieces;
        compPieces=compPieces-playerPieces;
        updatePlayerPieces();
        updateCompPieces();
    }
}//GEN-LAST:event_setRedActionPerformed

    //This function is used when the white mode button is selected. If the game is in 
    //play mode, the function will produce an error message. Otherwise, the game will
    //update the computer color and display, and will appropriately swap the piece counts
    //and their display.
private void setWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setWhiteActionPerformed
    if (boardMode == 'p')
        failedColorChange();
    else {
        currCompColor = 'w';
        cells.setCompColor(currCompColor);
        compColor.setText("Computer: White");
        
        compPieces+=playerPieces;
        playerPieces=compPieces-playerPieces;
        compPieces=compPieces-playerPieces;
        updatePlayerPieces();
        updateCompPieces();
    }
}//GEN-LAST:event_setWhiteActionPerformed

    //This function triggers when the start play button is pressed and sets the game
    //to a start state
private void startGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameActionPerformed
    boardMode = 'p';
    gameMode.setText("Play Mode On");
}//GEN-LAST:event_startGameActionPerformed

    //This function triggers when the stop play button is pressed and sets the game
    //to a stop state
private void stopGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopGameActionPerformed
    boardMode = 'n';
    gameMode.setText("Play Mode Off");
    oppMoveStarted = false;
    drawBoard();
    cMove.setVisible(true);
}//GEN-LAST:event_stopGameActionPerformed

    //This function is called when a new board needs to be drawn, it pulls the cell
    //color information from the cells array and will set each of the buttons to the 
    //appropriate png icon. 
    private void drawBoard() {
        char currChar;
        for(int i = 0; i <8; i++) {
            for(int j = 0; j < 8; j++) {
                currChar = cells.getCellType(i, j);
                if ( currChar == 'r')
                    board[i][j].setIcon(redButton);
                else if (currChar == 'w')
                    board[i][j].setIcon(whiteButton);
                else
                    board[i][j].setIcon(emptyCell);
            }
        }
    }
    
    //This class is specifically to handle the clicks of the sqButtons which make up 
    //our game playing board. 
    private class buttonHandler implements java.awt.event.ActionListener
    {
        //The action performed function is a large error handling and game playing 
        //function which handles the bulk of the user interaction before and during 
        //gameplay. It retrieves the x and y positions initially and then it will 
        //reach the conditional statements. 
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
            //This section sets up our data, we type or action event into an 
            //sqbutton and then retrieve the x and y values from the button. 
            //We also retrieve the cell type initially to enable use in our 
            //comparison statements.
            sqButton item = (sqButton) e.getSource();
            int x = item.getXCoord(), y = item.getYCoord(), newBoard;
            char currChar = cells.getCellType(x, y);
            
            //If the board isn't in play, then all we are wanting to do is setup 
            //a board. This section will increment or decrement the piece counts 
            //based on the cell selected and the computer's piece color. The new color 
            //cycles through the three choices: red, black and white. This section
            //also will update the celltype but not make a new move since the board
            //is in a setup state
            if (boardMode != 'p') {
                if ( currChar == 'r') {
                    if (currCompColor == currChar) {
                        compPieces--;
                        playerPieces++;
                    } else {
                        compPieces++;
                        playerPieces--;
                    }
                    cells.setCellType(x, y, 'w');
                    item.setIcon(whiteButton);
                } else if (currChar == 'w') {
                    if (currCompColor == currChar)
                        compPieces--;
                    else
                        playerPieces--;
                    cells.setCellType(x, y, 'b');
                    item.setIcon(emptyCell);
                } else if (currChar == 'b') {
                    if (currCompColor == 'r')
                        compPieces++;
                    else
                        playerPieces++;                    
                    cells.setCellType(x, y, 'r');
                    item.setIcon(redButton);
                }
            }
            //if the board is currently in play and the opponent move has been started
            //and if the computer was the last to play or no one has played yet, we move 
            //into this conditional block and check if it's a valid move. If so, we retrieve
            //the value of the move and since it is negative, add it to the number of computer pieces.
            //we set opponent move started to false and current player moving to the computer.
            else if (currChar != currCompColor && oppMoveStarted && currPlayerMove != 'c') {
                if (isValidMove(x,y)) {
                    int z = cells.newMove(lastCellSelected[0], lastCellSelected[1],x, y, cells.getOppColor());
                    compPieces+=z;
                    drawBoard();
                    oppMoveStarted = false;
                    currPlayerMove = 'c';
                    undo=0;
                    movesMade++;
                    cMoveActionPerformed(e);
                }
                //If we've selected the same piece, we set move started to false and turn all the 
                //valid cell highlights to the empty cell icon type. This will un-highlight all of the
                //possible move cells. 
                else if (x == lastCellSelected[0] && y == lastCellSelected[1])
                {
                    oppMoveStarted = false;                    
                    int i = 0;
                    while ( i < possMoves.length) {
                    board[possMoves[i][0]][possMoves[i][1]].setIcon(emptyCell);
                    i++;
                    }
                }
                else //Otherwise, the move is invalid since it's not the original cell
                    //or in the possible moves list. 
                    invalidMove();
            }
            //If the current cell isn't blank or the computer color and the current player isn't the computer and 
            //the opponents move hasn't been started, we hit this section. First we generate an array of the possible moves
            //and if the length of this array is 0, then the piece cannot be moved and we 
            //produce an error to that effect. Otherwise, we start a move, set the cell to a blank cell and store
            //the x and y values in the last cell selected to allow us to detect if the 
            //player clicks on the cell in the future. 
            else if (currChar != 'b' && currChar != currCompColor && !oppMoveStarted && currPlayerMove != 'c') {
                
                possMoves = cells.validMoves(x,y);
                if (possMoves.length == 0) {
                    pieceLocked();
                } else {
                    oppMoveStarted = true;
                    lastCellSelected = new int[]{x,y};
                }
                
                int i = 0;
                //We then iterate through the possible moves list and will set the icon of the 
                //buttons a player could move to to a yellow highlight.
                while ( i < possMoves.length) {
                    board[possMoves[i][0]][possMoves[i][1]].setIcon(possMove);
                    i++;
                } //If the current move is the computer's move, display an error
            } else if (currPlayerMove == 'c')
                computerMove();
            else { //If we end up here, there is no condition which satisfied any 
                //possible valid option and we have an invalid click. This rule is 
                //unlikely to ever be fired but is here as a safeguard
                invalidMove();
            }
            //These calls simply update the moves made, player and computer pieces 
            //displays on the gui
            updatePlayerPieces();
            updateCompPieces();
            updateMovesMade();
        }
    }
    
    //This function checks the possible moves and will return true or false as to 
    //if the move is found to be valid. 
    private boolean isValidMove(int x , int y ) 
    {
        for (int i = 0; i < possMoves.length; i++)
            if (x == possMoves[i][0] && y == possMoves[i][1])
                return true;
        return false;
    }
    
    //This function simply updates the jLabel pertaining the the player piece counts. 
    private void updatePlayerPieces() 
    {
        String display = "" + playerPieces;
        playerPiecesDisplay.setText(display);
    }
    
    //This function simply updates the jLabel pertaining the the computer piece counts. 
    private void updateCompPieces() 
    {
        String display = "" + compPieces;
        compPiecesDisplay.setText(display);
    }
    
    //This function simply updates the jLabel pertaining the the Valid Move counts.   
    private void updateMovesMade() 
    {
        String display = "" + movesMade;
        movesMadeDisplay.setText(display);
    }
    
    //This is the error box that informs the player of an invalid move
    private void invalidMove() {
        javax.swing.JOptionPane.showMessageDialog(this,
            "The game is currently in play, you cannot select this cell to move from or to.",
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    
    //This is the error box that informs the player of a locked piece
    private void pieceLocked() {
        javax.swing.JOptionPane.showMessageDialog(this,
            "This piece has no moves possible, please pick another piece.",
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE);        
    }
    
    //This is the error box that informs the player that they can't change colors
    //while the game is running
    private void failedColorChange() {
        javax.swing.JOptionPane.showMessageDialog(this,
            "You cannot change player color while the game is in progress.",
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE);        
    }
    
    //This is the error that asks the player to run a computer move instead.
    private void computerMove() {
        javax.swing.JOptionPane.showMessageDialog(this,
            "It is the Computer's Move at this time.\nPlease start the computer move.",
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE); 
    }
    
    //This error tells the player to play their own move instead of trying a computer
    //move
    private void playerMove() {
        javax.swing.JOptionPane.showMessageDialog(this,
            "It is the Player's move at this time.\nPlease start the player move.",
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE); 
    }
    
    private int undo;
    private char currPlayerMove;
    private int playerPieces;
    private int compPieces;
    private int movesMade;
    private int[][] possMoves;
    private int[] lastCellSelected;
    private boolean oppMoveStarted;
    private boardState cells;
    private sqButton board[][];
    private javax.swing.Icon redButton = new javax.swing.ImageIcon("redButton.png");
    private javax.swing.Icon whiteButton = new javax.swing.ImageIcon("whiteButton.png");
    private javax.swing.Icon emptyCell = new javax.swing.ImageIcon("emptyCell.png");
    private javax.swing.Icon possMove = new javax.swing.ImageIcon("possMove.png");
    private char currCompColor;
    private char boardMode;
    java.util.Random ranNum;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MovesMadeLabel;
    private javax.swing.JButton Redo;
    private javax.swing.JLabel SetCompColor;
    private javax.swing.JButton Undo;
    private javax.swing.JButton cMove;
    private javax.swing.JLabel compColor;
    private javax.swing.JLabel compPiecesDisplay;
    private javax.swing.JLabel compPiecesLabel;
    private javax.swing.JLabel gameMode;
    private javax.swing.JLabel movesMadeDisplay;
    private javax.swing.JLabel playerPiecesDisplay;
    private javax.swing.JLabel playerPiecesLabel;
    private javax.swing.JPanel playingArea;
    private javax.swing.JButton resetBoard;
    private javax.swing.JButton setRed;
    private javax.swing.JButton setWhite;
    private javax.swing.JButton startGame;
    private javax.swing.JButton stopGame;
    // End of variables declaration//GEN-END:variables
}