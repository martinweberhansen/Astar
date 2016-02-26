package searhalgorithmvisualization;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maze <martinweberhansen at protonmail.com>
 */
public class Astar {
    private Tile currentTile;
    private  Tile start;
    private  Tile goal;
    private List<Tile> closedList = new ArrayList();
    private List<Tile> openList;
    private final int worldWidth = 20;
    private final int worldHeight = 10;
    
    public Astar(){
    }
    public void addStartClosedList(){
        closedList.add(start);
    }
    
    public void step(Tile[][] tiles){
        Tile currentTarget=null;
        for(Tile neighbour : currentTile.getNeighbours()){
            if( currentTarget == null || neighbour.getH() < currentTarget.getH() && !closedList.contains(neighbour) ){
                currentTarget = neighbour;
            }
            if(currentTarget.getH() == neighbour.getH() && currentTarget.getF() < neighbour.getH() && !closedList.contains(neighbour)){
                currentTarget = neighbour;
            }
        } closedList.add(currentTile);
        currentTile = currentTarget;

        if(currentTile.getXPos() > 0)
            currentTile.addNeighBour(tiles[currentTile.getXPos()-1][currentTile.getYPos()]);
        if(currentTile.getYPos() > 0)
            currentTile.addNeighBour(tiles[currentTile.getXPos()][currentTile.getYPos()-1]);
        if(currentTile.getXPos() < worldWidth-1)
            currentTile.addNeighBour(tiles[currentTile.getXPos()+1][currentTile.getYPos()]);
        if(currentTile.getYPos() < worldHeight-1)
            currentTile.addNeighBour(tiles[currentTile.getXPos()][currentTile.getYPos()+1]);
    }
   
    public void setCurrentTile(Tile newCurrentTile){
        this.currentTile = newCurrentTile;
    }
    public Tile getCurrentTile(){
        return currentTile;
    }
    public Tile getStart() {
        return start;
    }
    public void setStart(Tile start) {
        this.start = start;
    }
    public Tile getGoal() {
        return goal;
    }
    public void setGoal(Tile goal) {
        this.goal = goal;
    }
    
    public int getWorldWidth(){
        return this.worldWidth;
    }
            
    public int getWorldHeight(){
        return this.worldHeight;
    }
}
