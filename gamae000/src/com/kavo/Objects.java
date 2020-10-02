package com.kavo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Objects {
    private Sprite sprite;
    private int x,y;
    private int width,height;
    private boolean collisions_checked = false;


    public static int frame_width,frame_height;
    public static void set_FrameBorders(int _fwidth,int _fheight){
        frame_width = _fwidth;
        frame_height = _fheight;
    }

    public Objects(String _path){
        sprite = new Sprite(_path);
        width = sprite.get_Width();
        height = sprite.get_Height();
    }
    public Objects(Image _img){
        sprite = new Sprite(_img);
        width = sprite.get_Width();
        height = sprite.get_Height();
    }
    public Objects(String _path, int _s_width,int _s_height){
        sprite = new Sprite(_path,_s_width,_s_height);
        width = _s_width;
        height = _s_height;
    }
    public Objects(ArrayList<Image> _imgs,int _s_width,int _s_height){
        sprite = new Sprite(_imgs,_s_width,_s_height);
        width = sprite.get_Width();
        height = sprite.get_Height();
    }
    public boolean CollisionCheck(Objects _object){
        boolean collision = false;
        if (((x<=(_object.x+_object.width)) && (x>=(_object.x))) || (((x+width)>_object.x) && ((x+width)<(_object.x+_object.width)))){
            if (((y<=(_object.y+_object.height)) && (y>=(_object.y))) || (((y+height)>_object.y) && ((y+height)<(_object.y+_object.height)))){
                //
                // JOptionPane.showMessageDialog(null,x+" "+y+" "+width+" "+height+" "+_object.x+" "+_object.y+_object.width+" "+_object.height);
                collision = true;
            }
        }
        if (!collision) collision = _object.CollisionCheck2(this);


        if (collision){
            return true;
        }
        else return false;
    }
    private boolean CollisionCheck2(Objects _object){
        boolean collision = false;
        if (((x<=(_object.x+_object.width)) && (x>=(_object.x))) || (((x+width)>_object.x) && ((x+width)<(_object.x+_object.width)))){
            if (((y<=(_object.y+_object.height)) && (y>=(_object.y))) || (((y+height)>_object.y) && ((y+height)<(_object.y+_object.height)))){
                //JOptionPane.showMessageDialog(null,x+" "+y+" "+width+" "+height+" thats life and it aint funny as it may seem"+_object.x+" "+_object.y+_object.width+" "+_object.height);
                collision = true;
            }
        }

        if (collision){
            return true;
        }
        else return false;
    }

    public void set_CollisionsChecked(){
        collisions_checked = true;
    }

    public void set_CollisionsUnChecked(){
        collisions_checked = false;
    }


    public void Move(int _x, int _y){
        x+=_x;
        y+=_y;
        sprite.set_XY(x,y);
    }

    public void set_DefaultPosition(){
        x = 100;
        y = 100;
        sprite.set_XY(x,y);
    }
    public void set_Position(int _x,int _y){
        x = _x;
        y = _y;
        sprite.set_XY(x,y);
    }

    public void CheckBorders(){
        if(x<0) x = 0;
        if(x+width>frame_width/2) x = frame_width/2 - width;
        if(y<0) y = 0;
        if(y+height>frame_height) y = frame_height-height;
    }


    public void Draw(Graphics g){
        sprite.Draw(g);
    }

    public int get_X(){
        return x;
    }
    public int get_Y(){
        return y;
    }
    public int get_Width(){
        return width;
    }
    public int get_Height(){
        return height;
    }
}
