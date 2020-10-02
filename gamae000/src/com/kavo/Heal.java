package com.kavo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Heal extends Playable {
    private int speed_Y = -10;
    private int speed_X = speed_Y/2;

    private int damage = 10;

    public Heal(String _path){
        super(_path);
    }
    public Heal(Image _img)
    {
        super(_img);
    }
    public Heal(String _path, int _s_width,int _s_height){
        super(_path,_s_width,_s_height);
    }
    public Heal(ArrayList<Image> _imgs, int _s_width, int _s_height){
        super(_imgs,_s_width,_s_height);
    }


    protected void Spawn() {
        tag = "heal";
        set_Position((int) ((Math.random()) * (object.frame_width - object.get_Width())), object.frame_height);
        //JOptionPane.showMessageDialog(null, "Spawn heal:" + object.get_X() + " " + object.get_Y());
    }

    public void Move(){
        object.Move(speed_X,speed_Y);
    }
    public void Check(){
        if(object.get_X()+object.get_Width()<=0){
            set_Deleted();
        }
    }

    public void Do(){
        Move();
        Check();
    }
}
