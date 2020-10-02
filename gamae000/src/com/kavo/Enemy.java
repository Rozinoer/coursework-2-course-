package com.kavo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Playable{
    private IWeaponable weapon;
    private boolean is_shooting = true;

    private int speed_X = 5;
    private int speed_Y = 0;

    private int[] casts = new int[4];
    private int current_cast = 0;
    private boolean cast1,cast2,cast3,cast4;

    private int hp = 1;


    private int shoot_count = 0;

    public Enemy(String _path){
        super(_path);
    }
    public Enemy(Image _img)
    {
        super(_img);
    }
    public Enemy(String _path, int _s_width,int _s_height){
        super(_path,_s_width,_s_height);
    }
    public Enemy(ArrayList<Image> _imgs, int _s_width, int _s_height){
        super(_imgs,_s_width,_s_height);
    }


    protected void Spawn(){
        tag = "enemy";
        weapon = new Weapon(this);
        set_Position(object.frame_width-object.get_Width()-100,(int)((Math.random())*(object.frame_height-object.get_Height())));
    }

    public void Do(){
        Move();
        Shoot();
    }

    protected void Move(){
        //object.Move(speed_X*direction_X,speed_Y*direction_Y);
        //object.CheckBorders();
    }
    private void Shoot(){
        if(is_shooting) {
            if(shoot_count>100) {
                //JOptionPane.showMessageDialog(null,"shooting");weapon.Shoot();
                weapon.Shoot();
                shoot_count=0;
            }
            shoot_count++;
        }
    }


    public void set_Weapon(IWeaponable _weapon){
        weapon = _weapon;
    }

    public void get_Damage(int _dmg){
        hp -= _dmg;
        if(hp<=0) {
            //JOptionPane.showMessageDialog(null,"dead");
            set_Deleted();
        }
    }
}
