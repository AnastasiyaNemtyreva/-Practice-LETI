
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 *
 * @author niksh
 */


public class PaintCanvas extends Canvas {
    final int size_canvas = 510;
    final int thikness = 1;
    Graphics2D graphic;
    Canvas canvas;
    int width,height;
    int coord_x=1,coord_y=1;
    boolean clicked = false;
    
    PaintCanvas(Canvas canvas,int width,int height){
        this.graphic =(Graphics2D) canvas.getGraphics();
        this.canvas = canvas;
        this.height=height;
        this.width = width;
        paint_canvas();
    }
    
    void set_height(int height){
        this.height = height;
        paint_canvas();
    }
    
    void set_width(int width){
        this.width = width;
        paint_canvas();
    }
    
    void paint_canvas(){
        //canvas.revalidate();
        //canvas.repaint();
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                draw_rect(i,j,Color.BLACK,Color.white);
            }
        }
        draw_rect(0,0,Color.BLACK,Color.yellow);
        draw_rect(width-1,height-1,Color.BLACK,Color.green);
    }
    
    void draw_rect(int x,int y,Color color,Color c_filling){
        graphic.setPaint(color);
        graphic.fillRect((size_canvas/width)*x,(size_canvas/height)*y,size_canvas/width,size_canvas/height);
        graphic.setPaint(c_filling);
        graphic.fillRect((size_canvas/width)*x+thikness,(size_canvas/height)*y+thikness,(size_canvas/width)-(thikness*2),(size_canvas/height)-(thikness*2));
    }
    
    void paint_selection(int x,int y,boolean clicked){
        if (this.clicked)   draw_rect(coord_x,coord_y,Color.BLACK,Color.gray);
        else draw_rect(coord_x,coord_y,Color.BLACK,Color.white);
        coord_x = x;
        coord_y = y;
        Stroke oldStroke = graphic.getStroke();
        graphic.setStroke(new BasicStroke(5));
        if (clicked) {draw_rect(coord_x, coord_y,Color.CYAN,Color.gray);}
        else draw_rect(coord_x, coord_y,Color.CYAN,Color.white);
        this.clicked = clicked;
        graphic.setStroke(oldStroke);
    }
}
