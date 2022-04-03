package bubble.test.ex07;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

//메인스레드는 하는 일(키보드 이벤트 처리)때문에 바쁘다.
//백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable{

    private BufferedImage image;  // 이미지를 버퍼로 읽어야한다.
    private Player7 player7;

    public BackgroundPlayerService(Player7 player7) {
        this.player7 = player7;
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
            Color leftcolor = new Color(image.getRGB(player7.getX() - 10 , player7.getY() + 25));
            Color rightcolor = new Color(image.getRGB(player7.getX() + 50 + 15 , player7.getY()+ 25));


            if (leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0 ){
                System.out.println("왼쪽 벽에 충돌");
                player7.setLeftWallCrash(true);
                player7.setLeft(false);
            }else if (rightcolor.getRed() == 255 && rightcolor.getGreen() == 0 && rightcolor.getBlue() == 0 ){
                System.out.println("오른쪽 벽에 충돌");
                player7.setRightWallCrash(true );
                player7.setRight(false);
            }else {
                player7.setLeftWallCrash(false);
                player7.setRightWallCrash(false );

            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
