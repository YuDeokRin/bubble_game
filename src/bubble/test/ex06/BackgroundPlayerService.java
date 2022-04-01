package bubble.test.ex06;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

//메인스레드는 하는 일(키보드 이벤트 처리)때문에 바쁘다.
//백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable{

    private BufferedImage image;

    @Override
    public void run() {
        try{
            image = ImageIO.read("image/test.png");
        }catch (Exception e) {

        }
    }
}
