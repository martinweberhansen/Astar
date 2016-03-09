package searhalgorithmvisualization;

import app2dapi.Platform;
import app2dapi.geometry.G2D;
import app2dapi.graphics.Canvas;
import app2dapi.graphics.Color;
import app2dapi.graphics.ColorFactory;
import app2dapi.input.charinput.CharInputEvent;
import app2dapi.input.keyboard.Key;
import app2dapi.input.keyboard.KeyPressedEvent;
import app2dapi.input.keyboard.KeyReleasedEvent;
import app2dapi.panandzoom2dapp.PanAndZoom2DApp;
import app2dapi.panandzoom2dapp.PanAndZoomAdapter;
import app2dapi.panandzoom2dapp.PanAndZoomInit;
import app2dapi.panandzoom2dapp.PanAndZoomToolKit;
import app2dpcimpl.PCPlatformImpl;

public class SearchAlgorithmVisualizationApp implements PanAndZoom2DApp{
    private double hudHeight;
    private double hudWidth;
    
    private ColorFactory cf;
    private G2D g2d;
    private Tile[][] tiles;
    private TileDrawer tileDrawer;
    private Astar astar; 
    
    @Override
    public PanAndZoomInit initialize(PanAndZoomToolKit tk, double aspectRatio){
        int startX = 0; int startY = 9;   //0,9   0,0  6,7
        int goalX   = 12; int goalY   = 9;   //4,4   -   9,4
        astar = new Astar(); 
        astar.initWalls();
        
        int worldWidth = astar.getWorldWidth();
        int worldHeight = astar.getWorldHeight();
        tiles = new Tile[worldWidth][worldHeight];
        for(int y = 0; y < worldHeight; ++y){
            for(int x = 0; x < worldWidth; ++x){
                
                boolean iAmWall = false;
                for(Tile wall : astar.getWalls()){
                    if( wall.getXPos() == x && wall.getYPos() == y ){
                         tiles[x][y] = new Tile(x, y,true); // makes a wall
                        iAmWall = true; 
                    }
                }
                if(   ! iAmWall   ){ 
                    tiles[x][y] = new Tile(x, y); 
                }
            }
        }
        astar.setStart              (tiles[startX][startY]);
        astar.setGoal                (tiles[goalX][goalY]);
        astar.setCurrentTile     (tiles[startX][startY]);
        tiles[startX][startY].setOnShortestPath();
        tiles[goalX][goalY].setToIAmGoal();
        
        Tile startTile = astar.getStart();
        startTile.setG(0);
        //tilføj neighbour  left
        if( startX > 0   &&   !tiles[startX-1][startY].isWall() ){
            startTile.addNeighBour(tiles[startX-1][startY]);
            tiles[startX-1][startY].setG(10);
        }
        //tilføj neighbour  up  left
        if( startX > 0   &&    startY < worldHeight-1   &&   !tiles[startX-1][startY+1].isWall() ){
            startTile.addNeighBour(tiles[startX-1][startY+1]);
            tiles[startX-1][startY+1].setG(14);
        }
        //tilføj neighbour  down  left
        if( startX > 0   &&   startY > 0   &&   !tiles[startX-1][startY-1].isWall() ){
            startTile.addNeighBour(tiles[startX-1][startY-1]);
            tiles[startX-1][startY-1].setG(14);
        }
         //tilføj neighbour  upper right
        if(startY < worldHeight-1   &&   startX < worldWidth-1   &&   !tiles[startX+1][startY+1].isWall()){
            startTile.addNeighBour(tiles[startX+1][startY+1]);
            tiles[startX+1][startY+1].setG(14);
        }
        //tilføj neighbour down right 
        if(startX < worldWidth-1   &&   startY > 0   &&   !tiles[startX+1][startY-1].isWall()){
            startTile.addNeighBour(tiles[startX+1][startY-1]);
            tiles[startX+1][startY-1].setG(1);
        }
         //tilføj neighbour  down  
        if( startY > 0   &&   !tiles[startX][startY-1].isWall() ){
            startTile.addNeighBour(tiles[startX][startY-1]);
            tiles[startX][startY-1].setG(1);
        }
         //tilføj neighbour  right 
        if(startX < worldWidth-1   &&   !tiles[startX+1][startY].isWall()){
            startTile.addNeighBour(tiles[startX+1][startY]);
            tiles[startX+1][startY].setG(10);
        }
         //tilføj neighbour  upper
        if(startY < worldHeight-1    &&   !tiles[startX][startY+1].isWall()){
            startTile.addNeighBour(tiles[startX][startY+1]);
            tiles[startX][startY+1].setG(10);
        }

        
        this.hudHeight = 1000;
        this.hudWidth = hudHeight * aspectRatio;
        this.cf = tk.cf();
        this.g2d = tk.g2d();
        tileDrawer = new TileDrawer(g2d, cf);
        return new PanAndZoomInit(  g2d.origo(),
                                    g2d.newPoint2D(hudWidth, hudHeight),
                                    g2d.origo(),
                                    g2d.newPoint2D(worldWidth, worldHeight),
                                    g2d.newPoint2D(worldWidth*0.5, worldHeight*0.5),
                                    worldWidth, 1, worldWidth);
    }

    @Override
    public boolean showMouseCursor(){
        return true;
    }

    @Override
    public void onMouseMoved(G2D.Point2D mouseHUDPos, G2D.Point2D mouseWorldPos){
        
    }

    @Override
    public void onMousePressed(G2D.Point2D mouseHUDPos, G2D.Point2D mouseWorldPos){
        
    }

    @Override
    public void onMouseReleased(G2D.Point2D mouseHUDPos, G2D.Point2D mouseWorldPos){
        
    }

    @Override
    public void onKeyPressed(KeyPressedEvent e){
         Key key = e.getKey();
         if(key == Key.VK_SPACE){
             astar.step(tiles);
         }
    }
    
    @Override
    public void onKeyReleased(KeyReleasedEvent e){
        
    }

    @Override
    public void onCharInput(CharInputEvent event){
        
    }

    @Override
    public boolean update(double time){
        return true;
    }

    @Override
    public Color getBackgroundColor(){
        return cf.getGreen();
    }

    @Override
    public void drawWorld(Canvas canvas){
        for(int y = 0; y < astar.getWorldHeight(); ++y){
            for(int x = 0; x < astar.getWorldWidth(); ++x){
                tileDrawer.draw(canvas, tiles[x][y]);
            }
        }
    }

    @Override
    public void drawHUD(Canvas canvas){
        
    }

    @Override
    public void destroy(){
        
    }
    
    public static void main(String[] args){
        PanAndZoom2DApp app = new SearchAlgorithmVisualizationApp();
        Platform pc = new PCPlatformImpl();
        pc.runApplication(new PanAndZoomAdapter(app));
    }
    
}
