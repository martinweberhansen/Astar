package searhalgorithmvisualization;

import java.util.ArrayList;
import java.util.List;

public class Tile{
    private final int xPos;
    private final int yPos;
    private int H,F,G;   //H = heuristic value(shortest known path to goal), G = path value to start, F = G + H
    private List<Tile>   neighbours = new ArrayList();
    
    public Tile(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Tile(int xPos, int yPos, int H, int G) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.H = H;
        this.G = G;
    }

    public List<Tile> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Tile> neighbours) {
        this.neighbours = neighbours;
    }
    
    public void addNeighBour(Tile tile){
         this.neighbours.add(tile);
    }

    public int getXPos(){
        return xPos;
    }

    public int getYPos(){
        return yPos;
    }

    public int getH() {
        return H;
    }

    public void setH(int H) {
        this.H = H;
    }

    public int getF() {
        return F;
    }

    public void setF(int F) {
        this.F = F;
    }

    public int getG() {
        return G;
    }

    public void setG(int G) {
        this.G = G;
    }
    
    
}
