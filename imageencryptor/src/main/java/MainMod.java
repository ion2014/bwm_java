import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Project: bwm_java
 * Created by longx on 2016/10/7.
 */
public class MainMod{
    public static void main(String[] args){
        String imgPath="C:/Users/longx/Documents/GitHub/bwm_java/res/Kato_Megumi.jpg";
        String wmPath="C:/Users/longx/Documents/GitHub/bwm_java/res/wm.jpg";
        String outImgPath="res/encrypted.jpg";
        String dcImgPath="res/decode.jpg";
        blindWaterMark.encode(imgPath,wmPath,outImgPath);
        //encode the wm into the img and output @outImgPath
        blindWaterMark.decode(imgPath,outImgPath,dcImgPath);
        //decode the wm with the img and output @dcImgPath;
        try{
            Displayer dp=new Displayer(imgPath);
            dp.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
