package bubble.test.ex10;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter @Setter
public class Bubble extends JLabel {

    //의존성 컴포지션 필요하다
    //이유 : 버블은 캐릭터에 의존해서 따라다녀야하기 때문이다.
    //의존성 컴포지션
    private Player10 player10;

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

    public Bubble(Player10 player10) {
        this.player10 = player10;
        initObject();
        initSetting();
    }

    private void initObject() {
        bubble = new ImageIcon("image/bubble.png");
        bubbled = new ImageIcon("image/bubbled.png");
        bomb = new ImageIcon("image/bomb.png");
    }

    private void initSetting() {
        left = false;
        right = false;
        up = false;

        x = player10.getX();
        y = player10.getY();

        setIcon(bubble);
        setSize(50, 50);

        state = 0; // 적을 가둔 물방울이 아닌 물방울 상태 이기때문에 0 이다.

    }
}
