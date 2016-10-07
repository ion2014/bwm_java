import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

/**
 * Project: bwm_java
 * Created by longx on 2016/10/7.
 */
public class Displayer extends Frame {
    Image im;Insets insets;
    int imgH,imgW;
    Displayer(String imgPath)throws Exception{
        super(imgPath);
        im=Toolkit.getDefaultToolkit().getImage(imgPath);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                dispose();
                System.exit(0);
            }
        });
        File file = new File(imgPath);
        FileInputStream fis = new FileInputStream(file);
        BufferedImage bufferedImg = ImageIO.read(fis);
        imgW = bufferedImg.getWidth();
        imgH = bufferedImg.getHeight();
    }
    public void addNotify() {
        super.addNotify();
        insets = getInsets();
        setBounds(300, 300, imgW + insets.left, imgH + insets.top);
    }
    public void paint(Graphics g) {
        g.drawImage(im, insets.left, insets.top, this);
    }
}
