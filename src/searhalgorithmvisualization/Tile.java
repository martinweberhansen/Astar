package searhalgorithmvisualization;

import java.util.ArrayList;
import java.util.List;

public class Tile{
    private final int xPos;
    private final int yPos;
    private int H,F,G;   //H = heuristic value(shortest path to goal), G = shortest path to start, F = G + H
    private List<Tile>   neighbours = new ArrayList();
    private Tile parent;
    private boolean onClosedList;
    private boolean onOpenList;
    private final boolean isWall;
    private boolean onShortestPath;
    private boolean iAmGoal;
    
    public Tile(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.onClosedList = false;
        this.onOpenList = false;
        this.isWall = false;
        this.onShortestPath = false;
        this.iAmGoal = false;
    }
    
    public Tile(int xPos, int yPos,boolean isWall){
        this.xPos = xPos;
        this.yPos = yPos;
        this.onClosedList = false;
        this.onOpenList = false;
        this.isWall = true;
        this.F = 999;
    }

    public void            setToIAmGoal(){
        this.iAmGoal = true;
    }
    public boolean      isGoal(){
        return iAmGoal;
    }
    public boolean      isOnShortestPath() {
        return onShortestPath;
    }
    public void            setOnShortestPath() {
        this.onShortestPath = true;
    }   
    public boolean      isWall(){
        return isWall;
    }
    public void            setToClosedList(){
        this.onClosedList = true;
        this.onOpenList = false;
    }
    public void            setToOpenList(){
        this.onOpenList = true;
    }
    public boolean      onClosedList(){
        return onClosedList;
    }
    public boolean      onOpenList(){
        return onOpenList;
    }
    public Tile            getParent() {
        return parent;
    }
    public void           setParent(Tile parent) {
        this.parent = parent;
    }
    public List<Tile>  getNeighbours() {
        return neighbours;
    }
    public void           setNeighbours(List<Tile> neighbours) {
        this.neighbours = neighbours;
    }   
    public void           addNeighBour(Tile tile){
         this.neighbours.add(tile);
    }
    public int             getXPos(){
        return xPos;
    }
    public int             getYPos(){
        return yPos;
    }
    public int             getH() {
        return H;
    }
    public void           setH(int H) {
        this.H = H;
    }
    public int             getF() {
        return F;
    }
    public void           setF(int F) {
        this.F = F;
    }
    public int             getG() {
        return G;
    }
    public void          setG(int G) {
        this.G = G;
        this.F = G+H;
    }
    
    
}
