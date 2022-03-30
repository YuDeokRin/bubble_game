package bubble.test.ex01;

import javax.swing.*;

//1. 윈도우 창이 되었음
//2. 윈도우 창은 내부에 패널을 하나 가지고 있다.
//Tip. JFrame은 while로 계속 돌고 있기 때문에 윈도우창을 계속 실행하고 있다.
public class BubbleFrame extends JFrame {

    private JButton Button;
    private JTextField JTextField;
    private JTextArea TextArea;

    public BubbleFrame() {
        setSize(1000, 640);
        getContentPane().setLayout(null);
        setVisible(true); // 그림을 그려라
    }


    public static void main(String[] args) {
        new BubbleFrame();
    }
}
