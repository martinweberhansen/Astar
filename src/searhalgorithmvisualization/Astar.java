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
    private List<Tile> openList = new ArrayList();
    private List<Tile> walls = new ArrayList();
    private List <Tile> shortestPath = new ArrayList();
    private final int worldWidth = 20;
    private final int worldHeight = 10;
    
    public Astar(){
    }
    
    public void initWalls(){
        
        // LEFT WALLS
//        walls.add( new Tile(3,7,true));
//        walls.add( new Tile(4,7,true));
//        walls.add( new Tile(5,7,true));
//        walls.add( new Tile(5,6,true));
//        walls.add( new Tile(5,5,true));
//        walls.add( new Tile(5,4,true));
//        walls.add( new Tile(5,3,true));
//        walls.add( new Tile(4,3,true));
//        walls.add( new Tile(3,3,true));
        
        // RIGHT WALLS
//        walls.add( new Tile(10,7,true));
//        walls.add( new Tile(9,7,true));
//        walls.add( new Tile(8,7,true));
//        walls.add( new Tile(8,6,true));
//        walls.add( new Tile(8,5,true));
//        walls.add( new Tile(8,4,true));
//        walls.add( new Tile(8,3,true));
//        walls.add( new Tile(9,3,true));
//        walls.add( new Tile(10,3,true));
        
        //random
        walls.add( new Tile(2,2,true));
        walls.add( new Tile(9,2,true));
        walls.add( new Tile(8,2,true));
        walls.add( new Tile(8,2,true));
        walls.add( new Tile(10,2,true));
        walls.add( new Tile(11,2,true));
        walls.add( new Tile(12,2,true));
        walls.add( new Tile(13,2,true));
        walls.add( new Tile(14,2,true));
        walls.add( new Tile(10,8,true));
        walls.add( new Tile(11,8,true));
        walls.add( new Tile(11,9,true));
        walls.add( new Tile(11,10,true));
        
        
         //random2
        walls.add( new Tile(2,2,true));
        walls.add( new Tile(9,4,true));
        walls.add( new Tile(9,5,true));
        walls.add( new Tile(9,6,true));
        walls.add( new Tile(9,7,true));
        walls.add( new Tile(9,8,true));
        walls.add( new Tile(4,8,true));
        walls.add( new Tile(4,7,true));
        walls.add( new Tile(4,6,true));
        walls.add( new Tile(5,8,true));
        walls.add( new Tile(4,8,true));
        walls.add( new Tile(4,9,true));
        walls.add( new Tile(4,10,true));
        
        
        
        walls.add( new Tile(2,2,true));
        walls.add( new Tile(4,2,true));
        walls.add( new Tile(6,2,true));
        walls.add( new Tile(8,2,true));
        walls.add( new Tile(10,2,true));
        walls.add( new Tile(12,2,true));
        walls.add( new Tile(14,2,true));
        walls.add( new Tile(16,2,true));
        walls.add( new Tile(18,2,true));
        
        walls.add( new Tile(2,4,true));
        walls.add( new Tile(4,4,true));
        walls.add( new Tile(6,4,true));
        walls.add( new Tile(8,4,true));
        walls.add( new Tile(10,4,true));
        walls.add( new Tile(12,4,true));
        walls.add( new Tile(14,4,true));
        walls.add( new Tile(16,4,true));
        walls.add( new Tile(18,4,true));
        
        walls.add( new Tile(2,6,true));
        walls.add( new Tile(4,6,true));
        walls.add( new Tile(6,6,true));
        walls.add( new Tile(8,6,true));
        walls.add( new Tile(10,6,true));
        walls.add( new Tile(12,6,true));
        walls.add( new Tile(14,6,true));
        walls.add( new Tile(16,6,true));
        walls.add( new Tile(18,6,true));
        
        walls.add( new Tile(2,8,true));
        walls.add( new Tile(4,8,true));
        walls.add( new Tile(6,8,true));
        walls.add( new Tile(8,8,true));
        walls.add( new Tile(10,8,true));
        walls.add( new Tile(12,8,true));
        walls.add( new Tile(14,8,true));
        walls.add( new Tile(16,8,true));
        walls.add( new Tile(18,8,true));
        
        walls.add( new Tile(2,10,true));
        walls.add( new Tile(4,10,true));
        walls.add( new Tile(6,10,true));
        walls.add( new Tile(8,10,true));
        walls.add( new Tile(10,10,true));
        walls.add( new Tile(12,10,true));
        walls.add( new Tile(14,10,true));
        walls.add( new Tile(16,10,true));
        walls.add( new Tile(18,10,true));
        
        
    }
    public List<Tile> getWalls(){
        return this.walls;
    }
    public void step(Tile[][] tiles){
        
        Tile currentTarget= null;
 // if goal reached-----------------------------------------------------------------------------------------------------------------------------------
        if(currentTile == goal){
            currentTarget = currentTile;
            while(currentTarget.getParent() != null){
                currentTarget.setOnShortestPath();
                currentTarget = currentTarget.getParent();
            }
        }
//------------------------------------------------------------------------------------------------------------------------------------------------------
//calculate heuristic--------------------------------------------------------------------------------------------------------------------------------
        else{
            addTileToClosedList(currentTile);
            for(Tile neighbour : currentTile.getNeighbours()){ //start loop for each neighbour--------------------------------------------->
                if(  !neighbour.isWall() ){
                    
                    int heuristicValue = 0;                    
                    int heuristicX = neighbour.getXPos()*10  -   goal.getXPos()*10;
                    int heuristicY = neighbour.getYPos()*10  -   goal.getYPos()*10;

                    if(heuristicX < 0) { heuristicX = heuristicX * -1; }
                    if(heuristicY < 0) { heuristicY = heuristicY * -1; }
                    
                    while(heuristicX > 0 && heuristicY > 0){
                        heuristicValue += 14;
                        heuristicY  -= 10;
                        heuristicX  -= 10;
                    }
                    heuristicValue += heuristicY;
                    heuristicValue += heuristicX;
                    
                    neighbour.setH(  heuristicValue  );
//------------------------------------------------------------------------------------------------------------------------------------------------------
//calculate shortes distance to start and set G value-------------------------------------------------------------------------------------------                    
                    if(   openList.contains(neighbour)   ) {
                        if(   currentTile.getXPos()-neighbour.getXPos() == 0 || currentTile.getYPos()-neighbour.getYPos() == 0   ) {
                            if(   currentTile.getG()+10 < neighbour.getG()   ) {
                                 neighbour.setG(currentTile.getG()+10);
                                 neighbour.setParent(currentTile);
                            }
                        }
                        else{
                             if(   currentTile.getG()+14 < neighbour.getG()   ) {
                                  neighbour.setG(currentTile.getG()+14);
                                  neighbour.setParent(currentTile);
                             }
                        }
                    }
                    if( !openList.contains( neighbour )   &&   !closedList.contains( neighbour ) ){
                        addTileToOpenList( neighbour );
                        neighbour.setParent(currentTile);
                        
                        if(currentTile.getXPos()-neighbour.getXPos() == 0 || currentTile.getYPos()-neighbour.getYPos() == 0)
                            neighbour.setG(currentTile.getG()+10);
                        else
                            neighbour.setG(currentTile.getG()+14);
                    }
//------------------------------------------------------------------------------------------------------------------------------------------------------                   
//select the neighbour with lowest F cost--------------------------------------------------------------------------------------------------------                     
                    if( !closedList.contains(neighbour) ){
                        if( currentTarget == null  || neighbour.getF() < currentTarget.getF() ){ 
                            currentTarget = neighbour;
                        }
                        if(currentTarget.getF() == neighbour.getF() && currentTarget.getH() < neighbour.getH() ){
                            currentTarget = neighbour;
                        }
                    }
                }
            }  //end of neighbour loop----------------------------------------------------------------------------------------------------------------<
//------------------------------------------------------------------------------------------------------------------------------------------------------            
//check if a tile in the openList has lower F value-----------------------------------------------------------------------------------------------       
            if(currentTarget != null){
                for(Tile t : openList){
                    if( currentTarget.getF() > t.getF() ){ 
                        currentTarget = t;
                    }
                }
            }
//-------------------------------------------------------------------------------------------------------------------------------------------------------        
// if currentTarget is null, select the tile from the openList, with the lowest F value------------------------------------------------------   
            if(currentTarget == null){
                for(Tile tile : openList){
                    if(currentTarget == null)
                        currentTarget = tile;
                    else if( tile.getF() < currentTarget.getF()) { 
                        currentTarget = tile;
                    }
                }
            }
            
            currentTile = currentTarget;
//--------------------------------------------------------------------------------------------------------------------------------------------------------   
//tjek om neighbour tile er på map,  tjek om tile er en wall, & add tile as neighbour----------------------------------------------------------
           
            //tilføj neighbour left
            if(currentTile.getXPos() > 0   &&   !tiles[currentTile.getXPos()-1][currentTile.getYPos()].isWall())
                currentTile.addNeighBour(tiles[currentTile.getXPos()-1][currentTile.getYPos()]);
            //tilføj neighbour left up
            if(currentTile.getXPos() > 0   &&   currentTile.getYPos() < worldHeight-1   && !tiles[currentTile.getXPos()-1][currentTile.getYPos()+1].isWall())
                currentTile.addNeighBour(tiles[currentTile.getXPos()-1][currentTile.getYPos()+1]);
            //tilføj neighbour left down
            if(currentTile.getXPos() > 0   &&   currentTile.getYPos() > 0   &&   !tiles[currentTile.getXPos()-1][currentTile.getYPos()-1].isWall())
                currentTile.addNeighBour(tiles[currentTile.getXPos()-1][currentTile.getYPos()-1]);
            //tilføj neighbour down
            if(currentTile.getYPos() > 0    &&   !tiles[currentTile.getXPos()][currentTile.getYPos()-1].isWall())
                currentTile.addNeighBour(tiles[currentTile.getXPos()][currentTile.getYPos()-1]);
            //tilføj neighbour down right
            if(currentTile.getYPos() > 0   &&   currentTile.getXPos() < worldWidth-1   &&   !tiles[currentTile.getXPos()+1][currentTile.getYPos()-1].isWall())
                currentTile.addNeighBour(tiles[currentTile.getXPos()+1][currentTile.getYPos()-1]);
            //tilføj neighbour right 
            if(currentTile.getXPos() < worldWidth-1   &&   !tiles[currentTile.getXPos()+1][currentTile.getYPos()].isWall())
                currentTile.addNeighBour(tiles[currentTile.getXPos()+1][currentTile.getYPos()]);
            //tilføj neighbour right up
            if(currentTile.getXPos() < worldWidth-1   &&   currentTile.getYPos() < worldHeight-1   &&   !tiles[currentTile.getXPos()+1][currentTile.getYPos()+1].isWall())
                currentTile.addNeighBour(tiles[currentTile.getXPos()+1][currentTile.getYPos()+1]);
            //tilføj neighbour up
            if(currentTile.getYPos() < worldHeight-1   &&   !tiles[currentTile.getXPos()][currentTile.getYPos()+1].isWall())
                currentTile.addNeighBour(tiles[currentTile.getXPos()][currentTile.getYPos()+1]);
        }
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
    public void addTileToClosedList(Tile tile){
        this.closedList.add(tile);
        this.openList.remove(tile);
        tile.setToClosedList();
    }
    public void addTileToOpenList(Tile tile){
        this.openList.add(tile);
        tile.setToOpenList();
    }
}
