package cn.demonk.hiddenimage.utils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ligs on 6/11/16.
 */
public class ImageUtil {


    public static BufferedImage readImage(InputStream in) {
        try {
            ImageInputStream input = ImageIO.createImageInputStream(in);
            return readImage(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage readImage(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                ImageInputStream input = ImageIO.createImageInputStream(file);
                return readImage(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static BufferedImage readImage(ImageInputStream input) throws IOException {
        return ImageIO.read(input);
    }
}
