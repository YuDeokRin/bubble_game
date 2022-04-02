package bubble.test.ex06;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

//메인스레드는 하는 일(키보드 이벤트 처리)때문에 바쁘다.
//백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable{

    private BufferedImage image;  // 이미지를 버퍼로 읽어야한다.
    private Player6 player6;

    public BackgroundPlayerService(Player6 player6) {
        this.player6 = player6;
        try {
            image = ImageIO.read(new File("image/backgroundMapService.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {

        while(true){
            //색상 확인
            Color leftcolor = new Color(image.getRGB(player6.getX() - 10 , player6.getY() + 25));
            Color rightcolor = new Color(image.getRGB(player6.getX() + 50 + 15 , player6.getY()+ 25));
            System.out.println("leftcolor색상 : " + leftcolor);
            System.out.println("----------------------------");
            System.out.println("rightcolor색상 : " + rightcolor);

            if (leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0 ){
                System.out.println("왼쪽 벽에 충돌");
            }else if (rightcolor.getRed() == 255 && rightcolor.getGreen() == 0 && rightcolor.getBlue() == 0 ){
                System.out.println("오른쪽 벽에 충돌");
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
