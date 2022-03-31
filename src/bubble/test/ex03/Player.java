package bubble.test.ex03;

import javax.swing.*;

//class Player -> new 가능한 애들이고  게임에 존재할 수 있다.(추상메서드를 가질 수 없다.)
public class Player extends JLabel implements Moveable {

    //위치 상태
    private int x;
    private int y;

    //움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;


    private ImageIcon playerR;
    private ImageIcon playerL;

    public Player() {
        initObject();
        initSetting();
    }

    private void initObject() {
        playerR = new ImageIcon("image/playerR.png");
        playerL = new ImageIcon("image/playerL.png");
    }

    //게임이 시작하는 상태
    private void initSetting() {
        x = 65;
        y = 535;

        //게임 시작 캐릭터의 기본값 = 아무것도 안하고 있는 상
        left = false;
        right = false;
        up = false;
        down = false;

        setIcon(playerR);
        setSize(50, 50);
        setLocation(x, y);
    }

    //이벤트 핸들러
    @Override
    public void left() {
        setIcon(playerL); // 캐릭터가 왼쪽으로 보게한다.
        x = x - 10;
        setLocation(x,y);
    }

    @Override
    public void right() {
        setIcon(playerR);
        x = x + 10;
        setLocation(x,y);
    }

    @Override
    public void up() {

    }

    @Override
    public void down() {

    }
}
