import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

/**
 * Project: bwm_java
 * Created by longx on 2016/10/7.
 */
public class blindWaterMark {
    static void encode(String fn1,String fn2,String output){

        Mat img=Highgui.imread("C:/Users/longx/Documents/GitHub/bwm_java/res/Kato_Megumi.jpg",Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        Mat wm=Highgui.imread(fn2);
        int w=img.cols();
        int h=img.rows();
        System.out.print(w);
        System.out.print(h);
    }
    static void decode(String src,String wm,String output){

    }
}
