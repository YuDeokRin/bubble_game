package bubble.test.ex08;




import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

//메인스레드는 하는 일(키보드 이벤트 처리)때문에 바쁘다.
//백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable{

    private BufferedImage image;  // 이미지를 버퍼로 읽어야한다.
    private Player8 player8;

    public BackgroundPlayerService(Player8 player8) {
        this.player8 = player8;
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
            Color leftColor = new Color(image.getRGB(player8.getX() - 10 , player8.getY() + 25));
            Color rightColor = new Color(image.getRGB(player8.getX() + 50 + 15 , player8.getY()+ 25));
            // -2가 나온다는 의미는 바닥에 색깔 없이 흰색
            int bottomColor =  image.getRGB(player8.getX() + 10, player8.getY()+ 50 + 5) // -1 경우
                    + image.getRGB(player8.getX() + 50 - 10 , player8.getY() + 50 + 5) ; // - 1 경우

            //바닥 충돌 확인
            if (bottomColor != -2){ // -2가 아니면  색깔이 무조건 있다.
                System.out.println("바텀 컬러 : " + bottomColor);
                System.out.println("바닥에 충돌함 ");
                player8.setDown(false);
            }


            //외벽 충돌 확인
            if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0 ){
                System.out.println("왼쪽 벽에 충돌");
                player8.setLeftWallCrash(true);
                player8.setLeft(false);
            }else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0 ){
                System.out.println("오른쪽 벽에 충돌");
                player8.setRightWallCrash(true );
                player8.setRight(false);
            }else {
                player8.setLeftWallCrash(false);
                player8.setRightWallCrash(false );

            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
