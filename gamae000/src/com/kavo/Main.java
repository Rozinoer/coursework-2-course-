package com.kavo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;


public class Main extends JPanel implements ActionListener{
    ArrayList<Image> mainPlayer_imgs = new ArrayList<>();
    ArrayList<Image> meteor_imgs = new ArrayList<>();
    ArrayList<Image> enemy_imgs = new ArrayList<>();
    ArrayList<Image> heal_imgs = new ArrayList<>();

    ArrayList<Image> bg_imgs = new ArrayList<>();
    ArrayList<Image> bg_imgs_scream = new ArrayList<>();

    Player mainPlayer;
    BackGround bg;


    ArrayList<Playable> objects_array = new ArrayList<>();
    ArrayList<Playable> objects_array_buffer_add = new ArrayList<>();

    Timer timer = new Timer(20, this);
    JFrame frame;

    private int  cd_meteor=0;
    private int cd_enemy=0;
    private int cd_heal = 0;

    private int score = 0;


    int count = 0;
    public Main(JFrame frame) {
        this.frame = frame;
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);

        Weapon.objects_array_buffer_add = objects_array;


        bg_imgs.add(new ImageIcon("/Users/evgenii/Desktop/sprite/background.png").getImage());
        bg_imgs_scream.add(new ImageIcon("/Users/evgenii/Desktop/sprite/screamsky.png").getImage());
        bg_imgs_scream.add(new ImageIcon("/Users/evgenii/Desktop/sprite/background.png").getImage());

        mainPlayer_imgs.add(new ImageIcon("/Users/evgenii/Desktop/sprite/player1.png").getImage());
        mainPlayer_imgs.add(new ImageIcon("/Users/evgenii/Desktop/sprite/player2.png").getImage());
        mainPlayer_imgs.add(new ImageIcon("/Users/evgenii/Desktop/sprite/player3.png").getImage());

        meteor_imgs.add(new ImageIcon("/Users/evgenii/Desktop/sprite/meteor1.png").getImage());
        meteor_imgs.add(new ImageIcon("/Users/evgenii/Desktop/sprite/meteor2.png").getImage());
        enemy_imgs.add(new ImageIcon("/Users/evgenii/Desktop/sprite/goblin1.png").getImage());

        heal_imgs.add(new ImageIcon("/Users/evgenii/Desktop/star.png").getImage());

        Bullet.img.add(new ImageIcon("/Users/evgenii/Desktop/sprite/bull.png").getImage());

        mainPlayer = new Player(mainPlayer_imgs,150,150);




        new Thread(()-> {
            JOptionPane.showMessageDialog(null, "press ok to start");
            Objects.set_FrameBorders(frame.getWidth(),frame.getHeight());
            objects_array.add(mainPlayer);
            mainPlayer.set_DefaultPos();
            bg = new BackGround(bg_imgs, frame.getWidth(),frame.getHeight());
            ScreamSky.bg = bg;
            ScreamSky.main = this;
            timer.start();
        }).start();
    }



    public void paint(Graphics g) {

        Phy();

        if (bg != null) {
            bg.Draw(g);
        }
        for (Playable item: objects_array) {
            if(!item.isDeleted()) {
                item.Draw(g);
            }
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",Font.BOLD,40));
        g.drawString(mainPlayer.get_HP(),10,50);
        g.drawString(Integer.toString(score),500,50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        setFocusable(true);
        bg.Move();

        repaint();
    }

    public void Phy(){
        for (Playable item : objects_array) {
            if (!item.isDeleted()) {
                item.Do();
            }
        }
        CollisionsCheck();

        if (count > 10) {
            count = 0;
            Delete();
        }
        count++;


        Spawn_Meteors();
        Spawn_Enemy();
        Spawn_Heal();

        if(mainPlayer.isDead()) timer.stop();
    }

    private void Spawn_Meteors(){
        if(cd_meteor>200) {
            cd_meteor = 0;
            objects_array.add(new Meteor(meteor_imgs, 200, 200));
        }
        cd_meteor++;
    }
    private void Spawn_Enemy(){
        if(cd_enemy>100) {
            cd_enemy = 0;
            objects_array.add(new Enemy(enemy_imgs,100,100));
        }
        cd_enemy++;
    }
    private void Spawn_Heal(){
        if(cd_heal>100) {
            cd_heal = 0;
            objects_array.add(new Heal(heal_imgs,100,100));
        }
        cd_heal++;
    }

    private class MyKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent event){
            mainPlayer.KeyPressed(event);
        }
        public void keyReleased(KeyEvent event){
            mainPlayer.KeyReleased(event);
        }
        public void keyDown(KeyEvent event) { }
    }

    private void CollisionsCheck() {

        for (Playable item: objects_array) {
            if (item.get_Tag().equals("player")){
                for (Playable item1 : objects_array) {
                    if (!item1.isDeleted()) {
                        if (item.CollisionCheck(item1)) {
                            if (item1.get_Tag().equals("enemy_bullet")) {
                                ((Player) item).get_Damage(((Bullet) item1).get_Damage());
                                item1.set_Deleted();
                            }
                            if (item1.get_Tag().equals("meteor")) {
                                ((Player) item).get_Damage(10);
                                item1.set_Deleted();
                            }
                            if (item1.get_Tag().equals("heal")) {
                                ((Player) item).get_Damage(-10);
                                item1.set_Deleted();
                            }
                        }
                    }
                }
            }



            if (item.get_Tag().equals("enemy")) {
                for (Playable item1 : objects_array) {
                    if (!item1.isDeleted()) {
                        if (item.CollisionCheck(item1)) {
                            if (item1.get_Tag().equals("bullet")) {
                                ((Enemy) item).get_Damage(((Bullet) item1).get_Damage());

                                item1.set_Deleted();
                            }
                        }
                    }
                }
            }
        }
    }

    public void DeleteObjects(){
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i=0;i<objects_array.size();++i){
            if (objects_array.get(i).isDeleted()) temp.add(i);
        }
        Arrays.sort(temp.toArray());
        for (Integer item:temp) {
            int a = item;
            //JOptionPane.showMessageDialog(null,item+" "+a+" size:"+objects_array.size());
            if(a<objects_array.size()) {
                objects_array.remove(a);
            }
        }
    }

    public void Delete() {
        ArrayList<Playable> temp = new ArrayList<>();
        for (Playable item : objects_array) {
            if (!item.isDeleted()) {
                temp.add(item);
            }
            else {
                if(item.get_Tag()=="enemy") score += 10;
            }
        }
        objects_array.clear();
        for (Playable item : temp) {
            objects_array.add(item);
        }
    }

    public void AddArr(){
        for (Playable item:objects_array_buffer_add) {
            objects_array.add(item);
            objects_array_buffer_add.clear();
        }
    }

    public Playable SearchTag(String _str){
        for (Playable item: objects_array) {
            if (item.get_Tag().equals(_str)) return item;
        }
        return null;
    }
    public void Damage_All(){
        for (Playable item: objects_array) {
            if (item.get_Tag().equals("enemy")) ((Enemy)item).get_Damage(1);
        }
    }


    public class BackGround{

        Sprite sprite;
        Sprite sprite2;
        int image_width = 0;
        int x1 = 0;
        int x2 = 0;
        boolean reset = false;
        int speed = 10;

        public BackGround(ArrayList<Image> _imgs, int _width, int _height){
            sprite = new Sprite(_imgs,_width,_height);
            sprite2 = new Sprite(_imgs,_width,_height);
            image_width = _width;
            //JOptionPane.showMessageDialog(null,image_width);
            x1 = 0;
            x2 = image_width;
        }
        public void set_img(){
            sprite.set_imgs(bg_imgs_scream);
            sprite2.set_imgs(bg_imgs_scream);
        }
        public void reset_imgs(){
            reset = true;
        }

        public void Move(){
            x1-=speed;
            x2-=speed;
            if(x2<=0){
                x1 = 0;
                x2 = image_width;
            }
            sprite.set_XY(x1,0);
            sprite2.set_XY(x2,0);
        }
        public void Draw(Graphics g){
            if (reset) {
                sprite.set_imgs(bg_imgs);
                sprite2.set_imgs(bg_imgs);
                reset = false;
            }
            sprite.Draw(g);
            sprite2.Draw(g);
        }



        public int get_X1(){
            return x1;
        }
        public int get_X2(){
            return x2;
        }



    }
}

