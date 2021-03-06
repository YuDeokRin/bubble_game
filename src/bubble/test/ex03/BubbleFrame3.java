package bubble.test.ex03;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BubbleFrame3 extends JFrame {

    private JLabel backgroundMap;
    private Player player;

    public BubbleFrame3() {
        initObject();
        initSetting();
        initListener();
        setVisible(true);
    }


    private void initObject() {
        backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
        setContentPane(backgroundMap);
        player = new Player();
        add(player);// player를 패널(JLabel) 덮붙이기

    }

    private void initSetting() {
        setSize(1000, 640);
        setLayout(null); // null값을 넣으면 absoulte 레이아웃이 설정된다. (자유롭게 그릴 수 있다.)
        setLocationRelativeTo(null); // 윈도우 창(JFrame)의 위치를 설정 : null -> 화면 정중앙
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼으로 창을 끌 때 JVM 같이 종료하기 설정
    }

    private void initListener(){
        //addKeyListener는 리스너를 만듬
        //()안에 이벤트핸들러 등록 -> keypress 스택 만듬
            addKeyListener(new KeyAdapter() {
               @Override
                public void keyPressed(KeyEvent e) {
                   //getKeyCode() : 방향 버튼을 누를 시 그 버튼이 무슨 값을 반환해주는지 알 수 있다.
                   //               즉, 아스키코드를 반환해준다.
                   //left : 37, right : 39, up : 38, down : 40
                   //left : 0x25, right : 0x27, up : 0x26, down : 0x28
                   System.out.println(e.getKeyCode());

                   switch(e.getKeyCode()){
                       case KeyEvent.VK_LEFT:
                           player.left();
                           break;
                       case KeyEvent.VK_RIGHT:
                           player.right();
                           break;
                       case KeyEvent.VK_UP:
                           player.up();
                           break;
                   }
                }
            });
    }

    public static void main(String[] args) {
        new BubbleFrame3();
    }
}
