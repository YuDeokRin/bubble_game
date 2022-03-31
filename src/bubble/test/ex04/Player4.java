package bubble.test.ex04;


import javax.swing.*;

//class Player -> new 가능한 애들이고  게임에 존재할 수 있다.(추상메서드를 가질 수 없다.)
public class Player4 extends JLabel implements Moveable4 {

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

    public Player4() {
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
        System.out.println("left실행 될  : 스레드 생성 ");
        new Thread(()-> {   //익명클래스, 람다식
            setIcon(playerL); // 캐릭터가 왼쪽으로 보게한다.
            x = x - 1;
            setLocation(x,y);
            System.out.println("left 스레드 종료 ");
        }).start();

    }

    @Override
    public void right() {
        System.out.println("right");
        new Thread(()-> {
            setIcon(playerR);
            x = x + 1;
            setLocation(x,y);
        }).start();

    }

    @Override
    public void up() {

        System.out.println("점프");
    }

    @Override
    public void down() {

    }
}
