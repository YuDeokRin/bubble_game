package bubble.test.ex11;




import bubble.test.ex11.Player11;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class BackgroundPlayerService implements Runnable{

    private BufferedImage image;  // 이미지를 버퍼로 읽어야한다.
    private Player11 player11;

    public BackgroundPlayerService(Player11 player11) {
        this.player11 = player11;
        try {
            image = ImageIO.read(new File("image/backgroundMapService.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while(true){
                // 하얀색 : -1
                // 빨간색 : -1237980
                // 노랑색 : -3584
                // 파랑색 : -12629812

            //색상 확인
            Color leftColor = new Color(image.getRGB(player11.getX() - 10 , player11.getY() + 25));
            Color rightColor = new Color(image.getRGB(player11.getX() + 50 + 15 , player11.getY()+ 25));
            // -2가 나온다는 의미는 바닥에 색깔 없이 흰색
            int bottomColor =  image.getRGB(player11.getX() + 10, player11.getY() + 50 + 5) //캐릭터 제일 왼쪽 하단,  -1 경우
                    + image.getRGB(player11.getX() + 50 -  10 , player11.getY() +  50 + 5) ; //캐릭터 제일 오른쪽 하단,  - 1 경우

            //바닥 충돌 확인
            if (bottomColor != -2){ // -2가 아니면  색깔이 무조건 있다.
                player11.setDown(false);
            }else { // -2 일때 실행된다. -> -2라는 뜻은 캐릭터 바닥의 색깔이 하얀색이라는 뜻이다.
                if(!player11.isUp() && !player11.isDown()){ // 점프 상태가 아닐때만 실행
                    // <-(왼쪽)  (오른쪽)->   : down 상태가 아니다 그러므로 down(false)라는 말
                    player11.down(); //  멈출 때는 true | false로 제어가 가능하지만, 다시 실행할 때는 메서드(down()) 호출을 다시해줘야한다.
                }
            }


            //외벽 충돌 확인
            if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0 ){
                player11.setLeftWallCrash(true);
                player11.setLeft(false);
            }else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0 ){
                player11.setRightWallCrash(true );
                player11.setRight(false);
            }else {
                player11.setLeftWallCrash(false);
                player11.setRightWallCrash(false );

            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
