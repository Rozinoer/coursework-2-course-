package com.kavo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bullet extends Playable {
    public static ArrayList<Image> img = new ArrayList<>();
    static int width = 20,height = 20;
    private int direction = 1;
    private int speed = 10;
    private int damage = 1;
    private int spawn_x=0,spawn_y = 0;


    public Bullet(int _spawn_x, int _spawn_y, String _tag) {
        super(img,20,20);
        set_Position(_spawn_x,_spawn_y);
        if (_tag == "player"){
            direction = 1;
            tag = "bullet";
        }
        if (_tag == "enemy"){
            direction = -1;
            tag = "enemy_bullet";
        }
    }
    /*public Bullet(String _path){
        super(_path);
    }
    public Bullet(Image _img)
    {
        super(_img);
    }
    public Bullet(String _path, int _s_width,int _s_height){
        super(_path,_s_width,_s_height);
    }
    public Bullet(ArrayList<Image> _imgs, int _s_width, int _s_height){
        super(_imgs,_s_width,_s_height);
    }
     */

    protected void Spawn(){
        set_Position(spawn_x,spawn_y);
    }

    public void Do(){
        Move();
        Check();
    }

    protected void Move(){
        object.Move(speed*direction,0);
    }

    public void Check(){
        if(object.get_X()+object.get_Width()<=0){
            set_Deleted();
        }
        if(object.get_X()>=object.frame_width){
            set_Deleted();
        }
    }
    public int get_Damage(){
        return damage;
    }
}
