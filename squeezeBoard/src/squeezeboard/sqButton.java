
package squeezeboard;

//Simple class that stores an x and y position and extends a standard JButton
//We need the x and y position for any JButton to determine which button was
//clicked and act upon it appropriately.
public class sqButton extends javax.swing.JButton {
    //Constructor sets up x and y coordinates
    sqButton(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }
    
    //return the x position
    public int getXCoord() {
        return xCoord;
    }
    //return the y position
    public int getYCoord() {
        return yCoord;
    }
    //private data members    
    private int xCoord;
    private int yCoord;
}
