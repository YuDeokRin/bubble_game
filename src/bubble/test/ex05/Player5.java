package bubble.test.ex05;


import bubble.test.ex04.Moveable4;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

//class Player -> new 가능한 애들이고  게임에 존재할 수 있다.(추상메서드를 가질 수 없다.)
@Getter @Setter // Getter, Setter 를 입력하는 이유 left, right, up, down에 접근하기 위해서
public class Player5 extends JLabel implements Moveable4 {

    //위치 상태
    private int x;
    private int y;

    //움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    //플레이어 속도 상태
    private final int SPEED = 4;
    private final int JUMPSPEED = 2; // up, down

    private ImageIcon playerR;
    private ImageIcon playerL;

    public Player5() {
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
        System.out.println("left ");
        left = true; // 왼쪽으로 움직이는 상태
        new Thread(()-> {   //익명클래스, 람다식
            while(left){
                setIcon(playerL); // 캐릭터가 왼쪽으로 보게한다.
                x = x - SPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10); //0.01초
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void right() {
        System.out.println("right");
        right = true;
        new Thread(()-> {
            while(right){
                setIcon(playerR);
                x = x + SPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10); //0.01초
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    // up이 갖는 경우의 수 : left + up , right + up
    @Override
    public void up() {
        System.out.println("up");
        up = true;
        new Thread(()->{
            //for로 돌리는 이유 : 끝이 있어야지 점프가 끝까지 안감.
            for (int i = 0; i < 130/JUMPSPEED ; i++) {
                y = y - JUMPSPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //점프가 끝난 상태
            up = false;
            down();

        }).start();
    }

    @Override
    public void down() {
        System.out.println("down");
        down = true;
        new Thread(()->{
            for (int i = 0; i < 130 / JUMPSPEED; i++) {
                y = y + JUMPSPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            down = false;

        }).start();
    }
}
