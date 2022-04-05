package bubble.test.ex02;

import javax.swing.*;

public class Player extends JLabel {

    private int x;
    private int y;

    private ImageIcon playerR;
    private ImageIcon playerL;

    public Player(){
        initObject();
        initSetting();
    }

    private void initObject(){
        playerR = new ImageIcon("image/playerR.png");
        playerL = new ImageIcon("image/playerL.png");
    }

    private void initSetting(){
        x = 55;
        y = 535;

        this.setIcon(playerR);
        setSize(50,50);
        setLocation(x,y);
    }
}