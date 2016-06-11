package cn.demonk.hiddenimage;

import cn.demonk.hiddenimage.utils.GZipUtil;
import cn.demonk.hiddenimage.utils.ImageUtil;
import cn.demonk.hiddenimage.utils.security.rsa.RSACipher;
import cn.demonk.hiddenimage.utils.security.rsa.RSAKey;
import javafx.util.Pair;

import java.awt.image.BufferedImage;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by ligs on 6/11/16.
 */
public class HiddenImage {

    public static boolean hidden(String imgPath, String content) {
        // 1.RSA
        Pair<PrivateKey, PublicKey> keys = RSAKey.genKey();
        RSACipher.instance().update(keys.getValue(), keys.getKey());
        byte[] rsa_content = RSACipher.instance().encryptWithPubKey(content.getBytes());

        //2. gzip
        byte[] gzip_content = GZipUtil.compress(rsa_content);

        BufferedImage bi = ImageUtil.readImage(imgPath);

        assert bi != null && gzip_content != null;

        while (bi != null) {
            int width = bi.getWidth();
            int height = bi.getHeight();
            int minX = bi.getMinX();
            int minY = bi.getMinY();

            int minPixelNum = gzip_content.length * 4;//4pixel=1byte

            if ((width - minX) * (height - minY) < minPixelNum) {
                System.err.println("need a bigger pic");
                return false;
            }

            int index = 0;
            int bitIndicator = 0;//指示当前写到哪一个位段
            byte curByte = gzip_content[index];

            outer:
            for (int y = minY; y < height; y++) {
                for (int x = minX; x < width; x++) {
                    int pixel = bi.getRGB(x, y);

                    pixel &= 0xfc;
                    pixel |= (curByte & 0x03);
                    curByte >>= 2;

                    bi.setRGB(x, y, pixel);

                    if ((bitIndicator = (bitIndicator + 1) % 3) == 0) {
                        index++;
                        if (index < gzip_content.length) {
                            curByte = gzip_content[index];
                        } else {
                            break outer;
                        }
                    }
                }
            }

            File srcImg = new File(imgPath);
            File dstImg = new File(srcImg.getParentFile(), "sec_" + srcImg.getName());
            return ImageUtil.saveImage(dstImg.getAbsolutePath(), gzip_content.length, bi);
        }
        return false;
    }
}
