
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Event;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author niksh
 */
public class Maze {
    final int size_canvas = 510;
    final int thikness = 1;
    int width,height;
    int[][] maze_wall;
    boolean dont_edit=false;
    PaintCanvas canvas;
     Deque<Integer[]> stack = new ArrayDeque<Integer[]>();
    
    
    Maze(Canvas canv,int height , int width){
        canvas = new PaintCanvas(canv,height,width);
        canvas.paint_canvas();
        this.height = height;
        this.width = width;
        maze_wall = new int[width][height];
    }
    void on_mouse_dragged(int x,int y){
       int coord_x = (x*width)/size_canvas;
       int coord_y = (y*height)/size_canvas;
       if ((coord_x==0 && coord_y==0) || (coord_x==width-1 && coord_y==height-1) || (dont_edit)) return;
       canvas.paint_selection(coord_x, coord_y,maze_wall[coord_x][coord_y]!=0);
    }
    
    void paint(){
        if (dont_edit) return;
        canvas.paint_canvas();
    }
    
    void on_mouse_clicked(int x,int y){
       int coord_x = (x*width)/size_canvas;
       int coord_y = (y*height)/size_canvas;
       if ((coord_x==0 && coord_y==0) || (coord_x==width-1 && coord_y==height-1) || (dont_edit)) return;
       if (maze_wall[coord_x][coord_y]!=0)  maze_wall[coord_x][coord_y] = 0;
       else maze_wall[coord_x][coord_y] = 1;
       canvas.paint_selection(coord_x, coord_y,maze_wall[coord_x][coord_y]!=0);
       
    }
    
     void set_height(int height){
        this.height = height;
        maze_wall = new int[width][height];
        canvas.set_height(height);
    }
    
    void clear(){
        stack.clear();
        dont_edit=false;
        maze_wall = new int[width][height];
        canvas.paint_canvas();
    }
     
    void go_maze(){
        try {
            dont_edit=true;
            maze_wall[width-1][height-1]=2;
            Find_way_out(width-1, height-1);
        } catch (InterruptedException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    boolean check_left(int x , int y){
        if (x<1) return false;
        if (maze_wall[x-1][y]==0) return true;
        else return false;
    } 
    
        
    boolean check_right(int x , int y){
        if (x >width-2) return false;
        if (maze_wall[x+1][y]==0) return true;
        else return false;
    } 
    
    
    boolean check_up(int x , int y){
        if (y<1) return false;
        if (maze_wall[x][y-1]==0) return true;
        else return false;
    } 
        
    boolean check_down(int x , int y){
        if (y>height-2) return false;
        if (maze_wall[x][y+1]==0) return true;
        else return false;
    } 
     
     
    void Find_way_out(int x, int y) throws InterruptedException{
        if(x==0 && y==0) return;
        Thread.sleep(100);
        Integer[] arr;
        arr = new Integer[2];
        arr[0]=x;
        arr[1]=y;
        stack.push(arr);
        
        if (check_up(x,y)){
            maze_wall[x][y-1]=2;
            canvas.draw_rect(x,y-1,Color.BLACK,Color.GREEN);
            Find_way_out(x,y-1);
            return;
        } 
        
        if (check_left(x,y)){
            maze_wall[x-1][y]=2;
            canvas.draw_rect(x-1,y,Color.BLACK,Color.GREEN);
            Find_way_out(x-1,y);
            return;
        }
        
        if (check_down(x,y)){
            maze_wall[x][y+1]=2;
            canvas.draw_rect(x,y+1,Color.BLACK,Color.GREEN);
            Find_way_out(x,y+1);
            return;
        }
        
        if (check_right(x,y)){
            maze_wall[x+1][y]=2;
            canvas.draw_rect(x+1,y,Color.BLACK,Color.GREEN);
            Find_way_out(x+1,y);
            return;
        } 
        
        canvas.draw_rect(x,y,Color.BLACK,Color.RED);
        if (stack.size()>1){
            arr = stack.pop();
            arr = stack.pop();
            Find_way_out(arr[0],arr[1]);
        }
     }

     
    void set_width(int width){
        this.width = width;
        maze_wall = new int[width][height];
        canvas.set_width(width);
    }
    
}
