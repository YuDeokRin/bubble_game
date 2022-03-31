package bubble.test.ex02;

import javax.swing.*;

public class BubbleFrame extends JFrame {

    private JLabel backgroundMap;
    private Player player;

    public BubbleFrame(){
        initObject();
        initSetting();
        setVisible(true);
    }

    private void initObject(){
        backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
        setContentPane(backgroundMap);

        Player player = new Player();
        add(player);// player를 패널(JLabel) 덮붙이기
        //backgroundMap.setSize(100, 100);
        //backgroundMap.setLocation(300, 300);
        //backgroundMap.setSize(1000,600);
        //add(backgroundMap); // JFrame에 JLabel이 그려진다.

    }

    private void initSetting(){
        setSize(1000, 640);
        getContentPane().setLayout(null); // null값을 넣으면 absoulte 레이아웃이 설정된다. (자유롭게 그릴 수 있다.)

        setLocationRelativeTo(null); // 윈도우 창(JFrame)의 위치를 설정 : null -> 화면 정중앙
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼으로 창을 끌 때 JVM 같이 종료하기 설정
    }
    public static void main(String[] args) {
        new BubbleFrame();
    }
}
