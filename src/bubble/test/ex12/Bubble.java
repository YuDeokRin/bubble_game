package bubble.test.ex12;

import bubble.test.ex12.Moveable12;
import bubble.test.ex12.Player12;
import bubble.test.ex12.PlayerWay;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter @Setter
public class Bubble extends JLabel implements Moveable12 {

    //의존성 컴포지션
    private Player12 player12;
    private BackgroundBubbleService backgroundBubbleService;

    //위치 상태
    private int x;
    private int y;

    //움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;

    //적군을 맞춘 상태
    private int state; // 0(물방울) , 1(적을 가둔 물방울)

    private ImageIcon bubble; // 물방울
    private ImageIcon bubbled; //적을 가둔 물방울
    private ImageIcon bomb; // 물방울이 터진 상태

    public Bubble(Player12 player12) {
        this.player12 = player12;
        initObject();
        initSetting();
        initThread();
    }

    private void initObject() {
        bubble = new ImageIcon("image/bubble.png");
        bubbled = new ImageIcon("image/bubbled.png");
        bomb = new ImageIcon("image/bomb.png");

        backgroundBubbleService = new BackgroundBubbleService(this);
    }

    private void initSetting() {
        left = false;
        right = false;
        up = false;

        x = player12.getX();
        y = player12.getY();

        setIcon(bubble);
        setSize(50, 50);


        state = 0; // 적을 가둔 물방울이 아닌 물방울 상태 이기때문에 0 이다.

    }

    private void initThread() {
        //버블은 스레드가 하나만 필요하다.
        new Thread(()->{
            if(player12.getPlayerWay() == PlayerWay.LEFT){
                left();
            }else{
                right();
            }
        }).start();
    }

    @Override
    public void left() {
        left = true;
        for (int i = 0; i < 400; i++) {
            x--;
            setLocation(x, y);

            if (backgroundBubbleService.leftWall()){
                 break;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        up();
    }

    @Override
    public void right() {
        right = true;
        for (int i = 0; i < 400; i++) {
            x++;
            setLocation(x, y);

            if (backgroundBubbleService.rightWall()){
                break;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        up();
    }

    @Override
    public void up() {
        up = true;
        while (up) {
            y--;
            setLocation(x, y);

            if(backgroundBubbleService.topWall()){
                break;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
