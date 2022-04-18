package bubble.test.ex16;


import bubble.test.ex16.BubbleFrame16;
import bubble.test.ex16.EnemyWay;
import bubble.test.ex16.Moveable16;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

// 적(Enemy)은 그림이 되어야하기 때문에 extends JLabel을 상속하고
// 적(Enemy)은 행동의 제약이 필요하기 때문에 implements Moveable15가 필요하다.
@Getter
@Setter
public class Enemy extends JLabel implements Moveable16 {



    private BubbleFrame16 mContext;

    //위치 상태
    private int x;
    private int y;

    //적군의 방향
    private EnemyWay enemyWay ;


    //움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private int state; // 0(살아있는 상태), 1(물방울에 갇힌 상태)


    //적군의 속도의상태
    private final int SPEED = 3;
    private final int JUMPSPEED = 1; // up, down

    private ImageIcon enemyR;
    private ImageIcon enemyL;

    public Enemy(BubbleFrame16 mContext) {
        this.mContext = mContext;
        initObject();
        initSetting();
        //initBackgroundEnemyService();
    }

    private void initObject() {
        enemyR = new ImageIcon("image/enemyR.png");
        enemyL = new ImageIcon("image/enemyL.png");
    }


    //게임이 시작하는 상태
    private void initSetting() {
        x = 480;
        y = 178;

        //게임 시작 캐릭터의 기본값 = 아무것도 안하고 있는 상
        left = false;
        right = false;
        up = false;
        down = false;

        state = 0;

        enemyWay = EnemyWay.RIGHT; //방향

        setIcon(enemyR);
        setSize(50, 50);
        setLocation(x, y);
    }
    private void initBackgroundEnemyService(){
//        new Thread(new BackgroundEnemyService(this)).start();
    }


    //이벤트 핸들러
    @Override
    public void left() {
        enemyWay = EnemyWay.LEFT; //방향
        left = true; // 왼쪽으로 움직이는 상태
        new Thread(()-> {   //익명클래스, 람다식
            while(left){
                setIcon(enemyL); // 캐릭터가 왼쪽으로 보게한다.
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
        enemyWay= EnemyWay.RIGHT; //방향
        right = true;
        new Thread(()-> {
            while(right){
                setIcon(enemyR);
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
        up = true;
        new Thread(()->{
            //for로 돌리는 이유 : 끝이 있어야지 점프가 끝까지 안감.
            for (int i = 0; i < 130/JUMPSPEED ; i++) {
                y = y - JUMPSPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(5);
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

        down = true;
        new Thread(()->{
            while (down) { // while로 주면 down이 false이 나오면 캐릭터는 멈춘다.
                y = y + JUMPSPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            down = false;

        }).start();
    }
}
