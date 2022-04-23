package bubble.test.ex18;




import bubble.test.ex18.Bubble;
import bubble.test.ex18.Player18;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;


public class BackgroundPlayerService implements Runnable{

    private BufferedImage image;  // 이미지를 버퍼로 읽어야한다.
    private Player18 player18;
    private List<Bubble> bubbleList;

    //플레이어, 버블
    public BackgroundPlayerService(Player18 player18) {
        this.player18 = player18;
        this.bubbleList = player18.getBubbleList();
        try {
            image = ImageIO.read(new File("image/backgroundMapService.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while(true){

            //1. 버블 충돌 체크
            for (int i = 0; i < bubbleList.size(); i++) {
                Bubble bubble = bubbleList.get(i);
                if(bubble.getState() == 1){
                    if ((Math.abs(player18.getX() - bubble.getX()) < 10 ) &&
                            Math.abs(player18.getY() - bubble.getY()) > 0 && Math.abs(player18.getY() - bubble.getY()) < 50){
                        System.out.println("적군 처리 완료");
                        bubble.clearBubbled();
                        break;
                    }
                }
            }

            //2. 벽 충돌 체크
            //색상 확인
            Color leftColor = new Color(image.getRGB(player18.getX() - 10 , player18.getY() + 25));
            Color rightColor = new Color(image.getRGB(player18.getX() + 50 + 15 , player18.getY()+ 25));
            // -2가 나온다는 의미는 바닥에 색깔 없이 흰색
            int bottomColor =  image.getRGB(player18.getX() + 10, player18.getY() + 50 + 5) //캐릭터 제일 왼쪽 하단,  -1 경우
                    + image.getRGB(player18.getX() + 50 -  10 , player18.getY() +  50 + 5) ; //캐릭터 제일 오른쪽 하단,  - 1 경우

            //바닥 충돌 확인
            if (bottomColor != -2){ // -2가 아니면  색깔이 무조건 있다.
                player18.setDown(false);
            }else { // -2 일때 실행된다. -> -2라는 뜻은 캐릭터 바닥의 색깔이 하얀색이라는 뜻이다.
                if(!player18.isUp() && !player18.isDown()){ // 점프 상태가 아닐때만 실행
                    // <-(왼쪽)  (오른쪽)->   : down 상태가 아니다 그러므로 down(false)라는 말
                    player18.down(); //  멈출 때는 true | false로 제어가 가능하지만, 다시 실행할 때는 메서드(down()) 호출을 다시해줘야한다.
                }
            }


            //외벽 충돌 확인
            if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0 ){
                player18.setLeftWallCrash(true);
                player18.setLeft(false);
            }else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0 ){
                player18.setRightWallCrash(true );
                player18.setRight(false);
            }else {
                player18.setLeftWallCrash(false);
                player18.setRightWallCrash(false );

            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
