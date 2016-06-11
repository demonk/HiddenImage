package cn.demonk.hiddenimage.utils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
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


    /**
     * 将有保密内容的图片输出到文件
     *
     * @param path
     * @param size  加密内容长度
     * @param image
     * @return
     */
    public static boolean saveImage(String path, int size, BufferedImage image) {
        try {
            ImageOutputStream output = ImageIO.createImageOutputStream(new File(path));
            ImageIO.write(image, "png", output);
            output.writeInt(size);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static BufferedImage readImage(ImageInputStream input) throws IOException {
        return ImageIO.read(input);
    }
}
