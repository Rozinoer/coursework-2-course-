package com.kavo;

import java.awt.*;
import java.util.ArrayList;

public abstract class Playable {
    protected Objects object;
    private int id = 0;
    protected static int all_id=0;
    protected String tag = "none";
    private boolean delete = false;


    public Playable(String _path){
        object = new Objects(_path);
        all_id++;
        id = all_id;
        Spawn();
    }
    public Playable(Image _img){
        object = new Objects(_img);
        all_id++;
        id = all_id;
        Spawn();
    }
    public Playable(String _path, int _s_width,int _s_height){
        object = new Objects(_path, _s_width,_s_height);
        all_id++;
        id = all_id;
        Spawn();
    }
    public Playable(ArrayList<Image> _imgs, int _s_width, int _s_height){
        object = new Objects(_imgs, _s_width,_s_height);
        all_id++;
        id = all_id;
        Spawn();
    }



    public abstract void Do();
    protected abstract void Move();
    protected abstract void Spawn();

    public void Draw(Graphics g){
        object.Draw(g);
    }
    public boolean CollisionCheck(Playable item){
        if (item.get_ID()!=id)
            return object.CollisionCheck(item.object);
        else return false;
    }

    public int get_ID(){
        return id;
    }
    public void set_Deleted(){
        delete = true;
    }
    public boolean isDeleted(){
        return delete;
    }

    public String get_Tag(){
        return tag;
    }
    public void set_DefaultPos(){
        object.set_DefaultPosition();
    }
    public void set_Position(int _x,int _y){
        object.set_Position(_x,_y);
    }
    public int get_X(){
        return object.get_X();
    }
    public int get_Y(){
        return object.get_Y();
    }
    public int get_Width(){
        return object.get_Width();
    }
    public int get_Height(){
        return object.get_Height();
    }
}
