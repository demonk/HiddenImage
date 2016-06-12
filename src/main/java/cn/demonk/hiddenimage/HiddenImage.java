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

    private static byte[] compressData(String pubKey, String content) {
        // 1.RSA
//        Pair<PrivateKey, PublicKey> keys = RSAKey.genKey();
//        RSACipher.instance().update(keys.getValue(), keys.getKey());

        RSACipher.instance().updatePubKey(RSAKey.restoreKey(pubKey));
        byte[] rsa_content = RSACipher.instance().encryptWithPubKey(content.getBytes());

        //2. gzip
        byte[] gzip_content = GZipUtil.compress(rsa_content);
        return gzip_content;
    }

    private static int getMinPixelNum(int dataSize) {
        return dataSize * 4;//4pixel=1byte
    }

    private static boolean hideByte(BufferedImage bi, byte[] data) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minX = bi.getMinX();
        int minY = bi.getMinY();

        int minPixelNum = getMinPixelNum(data.length);

        while ((width - minX) * (height - minY) >= minPixelNum) {
            int index = 0;
            int bitIndicator = 0;//指示当前写到哪一个位段
            byte curByte = data[index];

            for (int y = minY; y < height; y++) {
                for (int x = minX; x < width; x++) {
                    int pixel = bi.getRGB(x, y);

                    pixel &= 0xfffc;
                    pixel |= ((curByte & 0xc0) >> (2 * 3));
                    curByte <<= 2;

                    bi.setRGB(x, y, pixel);

                    if ((bitIndicator = (bitIndicator + 1) % 4) == 0) {
                        index++;
                        if (index < data.length) {
                            curByte = data[index];
                        } else {
                            return true;
                        }
                    }
                }
            }
        }

        System.out.println("Too small image,num of min pixels: " + minPixelNum);
        return false;
    }

    private static void saveImage(String imgPath, BufferedImage bi, byte[] data) {
        File srcImg = new File(imgPath);
        File dstImg = new File(srcImg.getParentFile(), "sec_" + srcImg.getName());
        ImageUtil.saveImage(dstImg.getAbsolutePath(), bi, data.length);
    }

    public static boolean hidden(String imgPath, String pubKey, String content) {
        byte[] gzip_content = compressData(pubKey, content);

        while (gzip_content != null) {
            BufferedImage bi = ImageUtil.readImage(imgPath);
            if (bi == null || //
                    !hideByte(bi, gzip_content)) break;

            saveImage(imgPath, bi, gzip_content);

            return true;
        }

        return false;
    }


}
