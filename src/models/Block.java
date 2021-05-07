package models;


//create object for one block in tetronimo
public class Block {
    // x position of block
    private int xPos;
    //y position of block
    private int yPos;
    //index for color of block
    private int color;

  //x and y coord
    public Block(int xPos, int yPos, int color){
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }

    // Get x coordinate of block
    public int getXPos(){
        return xPos;
    }

    //Set x coordinate of block

    public void setXPos(int xPos){
        this.xPos = xPos;
    }

    //Get y coordinate of block

    public int getYPos(){
        return yPos;
    }

    //Set y coordinate of block

    public void setYPos(int yPos){
        this.yPos = yPos;
    }

    //Get color
    public int getColor(){
        return color;
    }

    /**
     * Set color index of block
     */
    public void setColor(int color){
        this.color=color;
    }

}