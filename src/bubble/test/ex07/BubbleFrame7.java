package bubble.test.ex07;




import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BubbleFrame7 extends JFrame {

    private JLabel backgroundMap;
    private Player7 player7;

    public BubbleFrame7() {
        initObject();
        initSetting();
        initListener();
        setVisible(true);
    }


    private void initObject() {
        backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
        setContentPane(backgroundMap);
        player7 = new Player7();
        add(player7);// player를 패널(JLabel) 덮붙이기

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
                   //getKeyCode() : 방향 버튼을 누를 시 그 버튼이 무슨 값을 반환해주는지 알 수 있다.
                   //               즉, 아스키코드를 반환해준다.
                   //left : 37, right : 39, up : 38, down : 40
                   //left : 0x25, right : 0x27, up : 0x26, down : 0x28

                   switch(e.getKeyCode()){
                       case KeyEvent.VK_LEFT:
                           if(!player7.isLeft() && !player7.isLeftWallCrash()){
                               player7.left();
                           }
                           break;
                       case KeyEvent.VK_RIGHT:
                           if(!player7.isRight() && !player7.isRightWallCrash()){ //isRight()가 true가 아니라면
                                player7.right(); // Right는 한번만 실행된다. -> 메소드를 계속 호출하면 과부하가 심해지기때문에
                           }

                           break;
                       case KeyEvent.VK_UP:
                           //점프 상태가 아닐 때(up, down)만 up을 할 수 있다.
                           if(!player7.isUp() && !player7.isDown()){
                           player7.up();
                           }
                           break;
                   }
                }

                // 키보드 해제(키보드를 땠을 때) 이벤트 핸들러
                @Override
                public void keyReleased(KeyEvent e) {
                    switch (e.getKeyCode()){
                        case KeyEvent.VK_LEFT:
                            player7.setLeft(false); // 키보드를 땟을 때 -> 움직이지 않기
                            break;
                        case KeyEvent.VK_RIGHT:
                            player7.setRight(false); // 키보드를 땟을 때 -> 움직이지 않기
                            break;
                    }
                }
            });
    }

    public static void main(String[] args) {
        new BubbleFrame7();
    }
}
