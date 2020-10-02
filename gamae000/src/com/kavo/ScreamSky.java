package com.kavo;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static javax.swing.text.StyleConstants.Background;

public class ScreamSky extends JPanel {
    int cooldown;
    static Main.BackGround bg;
    static Main main;
    ScreamSky() {
        CoolDown();
        //Do();
    }

    private void CoolDown() {
        this.cooldown = 10;
        while (this.cooldown > 0) {
            try {
                this.cooldown--;
                if(this.cooldown==8)reset_BG();
                setCooldown(this.cooldown);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void Damage(){
        if(main!=null)
            main.Damage_All();
    }

    private void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    private int getCooldown() {
        return cooldown;
    }
    private boolean checkCoolDown() {
        return getCooldown() == 0;
    }

    private void set_BG(){
        if(bg!=null){
            bg.set_img();
        }
    }
    private void reset_BG(){
        if(bg!=null)
            bg.reset_imgs();
    }

    public void Do() {
        if (checkCoolDown()) {
            new Thread(() -> {
                int i = 1;
                while (i > 0) {
                    try {

                        i--;
                        set_BG();
                        Damage();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CoolDown();
            }).start();

        } else  JOptionPane.showMessageDialog(null, "cooldown");
    }
    public void KeySpell(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_Z) {
            Do();
        }
    }

}