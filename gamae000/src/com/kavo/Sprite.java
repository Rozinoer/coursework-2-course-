package com.kavo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Sprite {
    private ArrayList<Image> images = new ArrayList<>();
    private int current_index = 0;
    private int x,y;
    private int width,height;

    private int current_delay=0;


    public Sprite(String _path){
        images.add(new ImageIcon(_path).getImage());
        width = images.get(0).getWidth(null);
        height = images.get(0).getHeight(null);

    }
    public Sprite(Image _img){
        images.add(_img);
        width = images.get(0).getWidth(null);
        height = images.get(0).getHeight(null);
    }

    public Sprite(String _path, int _width,int _height){
        images.add(new ImageIcon(_path).getImage());;
        width = _width;
        height = _height;
    }

    public Sprite(ArrayList<Image> _imgs, int _width,int _height){
        for (Image item: _imgs) {
            images.add(item);
        }
        width = _width;
        height = _height;
    }

    public void Draw(Graphics g) {

        Image item = images.get(current_index);
        g.drawImage(item, x, y, width, height, null);

        int delay = 10;
        if (current_delay>= delay){
            current_index++;
            current_delay=0;
        }
        current_delay++;
        if(current_index>=images.size())current_index=0;
    }

    public void set_XY(int _x,int _y){
        x = _x;
        y = _y;
    }
    public void set_imgs(ArrayList<Image> _imgs){
        current_delay=0;
        current_index=0;
        images = _imgs;
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
    public int get_Height() {
        return height;
    }

}
