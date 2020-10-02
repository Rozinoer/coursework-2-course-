package com.kavo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class Player extends Playable {
    private IWeaponable weapon = new Weapon(this);
    private ScreamSky spell = new ScreamSky();
    private int direction_X=0,direction_Y=0;
    private boolean is_shooting = false;
    private boolean is_dead = false;

    private int speed_X = 5;
    private int speed_Y = 5;

    private int[] casts = new int[4];
    private int current_cast = 0;

    private int hp = 100;

    public Player(String _path){
        super(_path);
    }
    public Player(Image _img)
    {
        super(_img);
    }
    public Player(String _path, int _s_width,int _s_height){
        super(_path,_s_width,_s_height);
    }
    public Player(ArrayList<Image> _imgs, int _s_width, int _s_height){
        super(_imgs,_s_width,_s_height);
    }

    private int shoot_count = 0;

    protected void Spawn(){
        tag = "player";
        weapon = new Weapon(this);
    }

    public void Do(){
        Move();
        Shoot();
    }

    protected void Move(){
        object.Move(speed_X*direction_X,speed_Y*direction_Y);
        object.CheckBorders();
    }
    private void Shoot(){
        if(is_shooting) {
            if(shoot_count>30) {
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
            is_dead = true;
            hp=667;
            JOptionPane.showMessageDialog(null,"IGOR`,BbI UmerLi( you are dead )");
        }
    }

    private void AddCast(int num){
        casts[current_cast] = num;
        current_cast++;
        if (current_cast>=4) current_cast = 0;

    }
    private void Cast(){
        String temp="";
        for(int i=0;i<4;++i){
            temp += Integer.toString(casts[i]);

        }
        //JOptionPane.showMessageDialog(null,temp);
        if (temp.contains("1322")||temp.contains("2132")||temp.contains("2213")||temp.contains("3221")) spell.Do();

    }


    public void KeyPressed(KeyEvent event) {
        //JOptionPane.showMessageDialog(null,"key pressed");
        int key = event.getKeyCode();
        switch (key) {
            case (KeyEvent.VK_UP):
                //direction_X = 0;
                direction_Y = -1;
                break;
            case (KeyEvent.VK_LEFT):
                direction_X = -1;
                //direction_Y = 0;
                break;
            case (KeyEvent.VK_DOWN):
                //direction_X = 0;
                direction_Y = 1;
                break;
            case (KeyEvent.VK_RIGHT):
                direction_X = 1;
                //direction_Y = 0;
                break;
            case (KeyEvent.VK_SPACE):
                is_shooting = true;
                break;
        }
    }
    public void KeyReleased(KeyEvent event){
        int key = event.getKeyCode();
        switch (key) {
            case (KeyEvent.VK_UP):
                //direction_X = 0;
                direction_Y = 0;
                break;
            case (KeyEvent.VK_LEFT):
                direction_X = 0;
                //direction_Y = 0;
                break;
            case (KeyEvent.VK_DOWN):
                //direction_X = 0;
                direction_Y = 0;
                break;
            case (KeyEvent.VK_RIGHT):
                direction_X = 0;
                //direction_Y = 0;
                break;
            case (KeyEvent.VK_SPACE):
                is_shooting = false;
                break;


            case (KeyEvent.VK_M):
                AddCast(1);
                break;
            case (KeyEvent.VK_COMMA):
                AddCast(2);
                break;
            case (KeyEvent.VK_PERIOD):
                AddCast(3);
                //JOptionPane.showMessageDialog(null,"");
                break;
            case (KeyEvent.VK_SLASH):
                AddCast(4);
                break;
            case (KeyEvent.VK_SHIFT):
                Cast();
                break;
        }
    }

    public String get_HP(){
        return Integer.toString(hp);
    }

    public boolean isDead(){
        return is_dead;
    }
}
