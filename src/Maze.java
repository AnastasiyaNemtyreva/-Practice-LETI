
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Event;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Vector;

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
    
    boolean check_left(int x , int y){
        if (x<1) return false;
        if (maze_wall[x-1][y]==0) return true;
        else return false;
    } 
    
        
    boolean check_right(int x , int y){
        if (x >=width-2) return false;
        if (maze_wall[x+1][y]==0) return true;
        else return false;
    } 
    
    
    boolean check_up(int x , int y){
        if (y<1) return false;
        if (maze_wall[x][y-1]==0) return true;
        else return false;
    } 
        
    boolean check_down(int x , int y){
        if (y>=height-2) return false;
        if (maze_wall[x][y+1]==0) return true;
        else return false;
    } 
     
     
    void Find_way_out(int x, int y) throws InterruptedException{
        Thread.sleep(100);
        dont_edit=true;
        /*Integer[] arr;
        arr = new Integer[2];
        arr[0]=x;
        arr[1]=y;
        stack.push(arr);*/
        
        if (check_up(x,y)){
            canvas.draw_rect(x,y-1,Color.BLACK,Color.GREEN);
            Find_way_out(x,y-1);
            return;
        } 
        
        if (check_left(x,y)){
            canvas.draw_rect(x-1,y,Color.BLACK,Color.GREEN);
            Find_way_out(x-1,y);
            return;
        }
        
        if (check_down(x,y)){
            canvas.draw_rect(x,y+1,Color.BLACK,Color.GREEN);
            Find_way_out(x,y+1);
            return;
        }
        
        if (check_right(x,y)){
            canvas.draw_rect(x+1,y,Color.BLACK,Color.GREEN);
            Find_way_out(x+1,y);
            return;
        } 

     }

     
    void set_width(int width){
        this.width = width;
        maze_wall = new int[width][height];
        canvas.set_width(width);
    }
    
}
