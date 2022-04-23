package bubble.test.ex17;


import bubble.test.ex17.Enemy;
import bubble.test.ex17.Player17;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Getter @Setter
public class BubbleFrame17 extends JFrame {

    private BubbleFrame17 mContext = this;
    private JLabel backgroundMap;
    private Player17 player17;
    private Enemy enemy;  // 적을 많이 만들 때 -> List<Enemy> enemy

    public BubbleFrame17() {
        initObject();
        initSetting();
        initListener();
        setVisible(true);
    }


    private void initObject() {
        backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
        setContentPane(backgroundMap);
        player17 = new Player17(mContext);
        add(player17);
        enemy = new Enemy(mContext);
        add(enemy);
        new BGM();
    }

    private void initSetting() {
        setSize(1000, 640);
        setLayout(null); // null값을 넣으면 absoulte 레이아웃이 설정된다. (자유롭게 그릴 수 있다.)
        setLocationRelativeTo(null); // 윈도우 창(JFrame)의 위치를 설정 : null -> 화면 정중앙
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼으로 창을 끌 때 JVM 같이 종료하기 설정
    }

    private void initListener(){
            addKeyListener(new KeyAdapter() {

                //키보드 클릭 이벤트 핸들러
               @Override
                public void keyPressed(KeyEvent e) {

                   switch(e.getKeyCode()){
                       case KeyEvent.VK_LEFT:
                           if(!player17.isLeft() && !player17.isLeftWallCrash()){
                               player17.left();
                           }
                           break;
                       case KeyEvent.VK_RIGHT:
                           if(!player17.isRight() && !player17.isRightWallCrash()){ //isRight()가 true가 아니라면
                                player17.right(); // Right는 한번만 실행된다. -> 메소드를 계속 호출하면 과부하가 심해지기때문에
                           }

                           break;
                       case KeyEvent.VK_UP:
                           //점프 상태가 아닐 때(up, down)만 up을 할 수 있다.
                           if(!player17.isUp() && !player17.isDown()){
                           player17.up();
                           }
                           break;
                       case KeyEvent.VK_SPACE:
                           player17.attack();
                           break;
                   }
                }

                // 키보드 해제(키보드를 땠을 때) 이벤트 핸들러
                @Override
                public void keyReleased(KeyEvent e) {
                    switch (e.getKeyCode()){
                        case KeyEvent.VK_LEFT:
                            player17.setLeft(false); // 키보드를 땟을 때 -> 움직이지 않기
                            break;
                        case KeyEvent.VK_RIGHT:
                            player17.setRight(false); // 키보드를 땟을 때 -> 움직이지 않기
                            break;
                    }
                }
            });
    }

    public static void main(String[] args) {
        new BubbleFrame17();
    }
}
