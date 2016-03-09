package searhalgorithmvisualization;

import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Transformation2D;
import app2dapi.graphics.Canvas;
import app2dapi.graphics.ColorFactory;

/**
 * @author Tobias Grundtvig
 */
public class TileDrawer
{
    private final G2D g2d;
    private final ColorFactory cf;

    public TileDrawer(G2D g2d, ColorFactory cf){
        this.g2d = g2d;
        this.cf = cf;
    }
    
    public void draw(Canvas canvas, Tile tile){
        Transformation2D parent = canvas.getTransformation();
        Transformation2D t = g2d.translate(tile.getXPos(), tile.getYPos());
        Transformation2D c = g2d.combine(parent, t);
        canvas.setTransformation(c);
        if(tile.isWall()){
            canvas.setColor(cf.getBlack());
            canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 1, 1);
            canvas.setColor(cf.getBlack());
            canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);
//            canvas.setColor(cf.getGreen());
//            canvas.drawText(g2d.newPoint2D(0.85, 0.85), Integer.toString(tile.getH()), 0.25, true, true);
        }else{
            canvas.setColor(cf.getBlack());
            canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 1, 1);
            canvas.setColor(cf.getWhite());
            canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);
            canvas.setColor(cf.getBlue());
            canvas.drawText(g2d.newPoint2D(0.85, 0.85), Integer.toString(tile.getH()), 0.25, true, true);
             if(tile.onOpenList()) {//if(tile.getG() > 0)
                 canvas.setColor(cf.getYellow());
                 canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);
                 canvas.setColor(cf.getBlue());
                 canvas.drawText(g2d.newPoint2D(0.15, 0.85), Integer.toString(tile.getG()), 0.20, true, true);
                 canvas.drawText(g2d.newPoint2D(0.85, 0.85), Integer.toString(tile.getH()), 0.20, true, true);
                 canvas.drawText(g2d.newPoint2D(0.5, 0.25), Integer.toString(tile.getF()), 0.4, true, true);
            }
             if(tile.onClosedList() ) {
                 canvas.setColor(cf.getRed());
                 canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);
                 canvas.setColor(cf.getBlue());
                 canvas.drawText(g2d.newPoint2D(0.15, 0.85), Integer.toString(tile.getG()), 0.20, true, true);
                 canvas.drawText(g2d.newPoint2D(0.85, 0.85), Integer.toString(tile.getH()), 0.20, true, true);
                 canvas.drawText(g2d.newPoint2D(0.5, 0.25), Integer.toString(tile.getF()), 0.4, true, true);
            }
             if(tile.isOnShortestPath() ) {
                 canvas.setColor(cf.getBlue());
                 canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);
                 canvas.setColor(cf.getWhite());
                 canvas.drawText(g2d.newPoint2D(0.15, 0.85), Integer.toString(tile.getG()), 0.20, true, true);
                 canvas.drawText(g2d.newPoint2D(0.85, 0.85), Integer.toString(tile.getH()), 0.20, true, true);
                 canvas.drawText(g2d.newPoint2D(0.5, 0.25), Integer.toString(tile.getF()), 0.4, true, true);
            }
             if(tile.isGoal() ) {
                 canvas.setColor(cf.getBlue());
                 canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);
                 canvas.setColor(cf.getWhite());
                 canvas.drawText(g2d.newPoint2D(0.15, 0.85), Integer.toString(tile.getG()), 0.20, true, true);
                 canvas.drawText(g2d.newPoint2D(0.85, 0.85), Integer.toString(tile.getH()), 0.20, true, true);
                 canvas.drawText(g2d.newPoint2D(0.5, 0.25), Integer.toString(tile.getF()), 0.4, true, true);
            }
             
         
        }
        canvas.setTransformation(parent);
    }
}


