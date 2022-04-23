package bubble.test.ex18;

import bubble.test.ex18.BackgroundBubbleService;
import bubble.test.ex18.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter @Setter
public class Bubble extends JLabel implements Moveable18{

    //의존성 컴포지션
    private BubbleFrame18 mContext;
    private Player18 player18;
    private Enemy enemy;
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

    public Bubble(BubbleFrame18 mContext) {
        this.mContext = mContext;
        this.player18 = mContext.getPlayer18();
        this.enemy = mContext.getEnemy();
        initObject();
        initSetting();
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

        x = player18.getX();
        y = player18.getY();

        setIcon(bubble);
        setSize(50, 50);


        state = 0; // 적을 가둔 물방울이 아닌 물방울 상태 이기때문에 0 이다.

    }


    @Override
    public void left() {
        left = true;
        for (int i = 0; i < 400; i++) {
            x--;
            setLocation(x, y);

            if (backgroundBubbleService.leftWall()){
                left = false;
                 break;
            }

            //아군과 적군의 거리가 10
           if ((Math.abs(x - enemy.getX()) < 10 ) &&
                   Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50){
                System.out.println("물방울이 적군과 충돌하였습니다.");
                if (enemy.getState() == 0 ){
                    attack();
                    break;
                }
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
                right = false;
                break;
            }

            if ((Math.abs(x - enemy.getX()) < 10 ) &&
                    Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50){
                System.out.println("물방울이 적군과 충돌하였습니다.");
                if (enemy.getState() == 0 ){
                    attack();
                    break;
                }
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
                up = false;
                break;
            }

            try {
                if (state == 0) { // 기본 물방울
                    Thread.sleep(1);
                }else{ // 적을 가둔 물방울
                    Thread.sleep(10);
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (state == 0 ) {
            clearBubble(); // 천장에 버블이 도착하고 나서 3초 후에 메모리에서 소멸
        }
    }

    @Override
    public void attack() {
        state = 1; // 물방울 상태
        enemy.setState(1); //적군의 상태
        setIcon(bubbled);
        mContext.remove(enemy); //메모리에서 사라지게 한다. (가비지 컬렉션 -> 즉시 일어나지 않음)
        mContext.repaint(); //화면 다시 그린다.
    }

    //private인 것은 다른 곳에서 호출하지 않기 때문이다.
    // 메소드 -> 행위 -> clear(동사) ->bubble(목적어)
    private void clearBubble() {
        try {
            Thread.sleep( 3000); // 3초간 머문다.
            setIcon(bomb);

            Thread.sleep(500);
            mContext.remove(this); //BubbleFrame의 bubble이 메모리에서 소멸된다.
            mContext.repaint(); //BubbleFrame의 전체를 다시 그린다. (메모리에서 없는 것은 그리지 않는다.)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clearBubbled(){
        new Thread(()-> {
            System.out.println("clearBubbled");
            try{
                up = false;
                setIcon(bomb);
                Thread.sleep(1000);
                mContext.remove(this);
                mContext.repaint();
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

    }
}
