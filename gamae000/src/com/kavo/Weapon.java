package com.kavo;

import javax.swing.*;
import java.util.ArrayList;

public class Weapon implements IWeaponable{
    public static ArrayList<Playable> objects_array_buffer_add;
    Playable player;


    public Weapon(Playable _player){
        player = _player;
    }

    public void Shoot(){
        int x=0,y=0;
        x = player.get_X();
        if (player.get_Tag()=="player") x+=player.get_Width();
        y = player.get_Y()+player.get_Height()/2;

        //JOptionPane.showMessageDialog(null,"spawn bullet"+x+" "+y);
        objects_array_buffer_add.add(new Bullet(x,y,player.get_Tag()));
    }
}
