package bubble.test.ex18;




import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;


public class BackgroundEnemyService implements Runnable{

    private BufferedImage image;  // 이미지를 버퍼로 읽어야한다.
    private Enemy enemy;

    //플레이어, 버블
    public BackgroundEnemyService(Enemy enemy) {
        this.enemy = enemy;
        try {
            image = ImageIO.read(new File("image/backgroundMapService.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while(enemy.getState() == 0 ){

            //1. 벽 충돌 체크
            //색상 확인
            Color leftColor = new Color(image.getRGB(enemy.getX() - 10 , enemy.getY() + 25));
            Color rightColor = new Color(image.getRGB(enemy.getX() + 50 + 15 , enemy.getY()+ 25));
            // -2가 나온다는 의미는 바닥에 색깔 없이 흰색
            int bottomColor =  image.getRGB(enemy.getX() + 10, enemy.getY() + 50 + 5) //캐릭터 제일 왼쪽 하단,  -1 경우
                    + image.getRGB(enemy.getX() + 50 -  10 , enemy.getY() +  50 + 5) ; //캐릭터 제일 오른쪽 하단,  - 1 경우

            //바닥 충돌 확인
            if (bottomColor != -2){ // -2가 아니면  색깔이 무조건 있다.
                enemy.setDown(false);
            }else { // -2 일때 실행된다. -> -2라는 뜻은 캐릭터 바닥의 색깔이 하얀색이라는 뜻이다.
                if(!enemy.isUp() && !enemy.isDown()){ // 점프 상태가 아닐때만 실행
                    // <-(왼쪽)  (오른쪽)->   : down 상태가 아니다 그러므로 down(false)라는 말
                    enemy.down(); //  멈출 때는 true | false로 제어가 가능하지만, 다시 실행할 때는 메서드(down()) 호출을 다시해줘야한다.
                }
            }


            //외벽 충돌 확인
            if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0 ){
                enemy.setLeft(false);
                if (!enemy.isRight()){
                    enemy.right();
                }
            }else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0 ){
                enemy.setRight(false);
                if (!enemy.isLeft()){
                    enemy.left();
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
