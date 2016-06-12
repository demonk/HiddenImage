package cn.demonk.hiddenimage;

import cn.demonk.hiddenimage.utils.GZipUtil;
import cn.demonk.hiddenimage.utils.ImageUtil;
import cn.demonk.hiddenimage.utils.security.rsa.RSACipher;
import cn.demonk.hiddenimage.utils.security.rsa.RSAKey;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ligs on 6/12/16.
 */
public class ShowImage {

    private static int readSecuritySize(String imgPath) throws IOException {
        int size = -1;

        ImageInputStream input = ImageIO.createImageInputStream(new File(imgPath));
        if (input != null) {
            input.seek(input.length() - 4);
            size = input.readInt();
            input.seek(0);
            input.close();
        }
        return size;
    }

    private static boolean showByte(BufferedImage bi, byte[] data) {

        int width = bi.getWidth();
        int height = bi.getHeight();
        int minX = bi.getMinX();
        int minY = bi.getMinY();

        byte curByte = 0;
        int index = 0;
        int bitIndicator = 0;//指示当前写到哪一个位段

        for (int y = minY; y < height; y++)
            for (int x = minX; x < width; x++) {
                int pixel = bi.getRGB(x, y);
                int ltb = pixel & 0x0003;//last two bits
                curByte |= ltb;

                if ((bitIndicator = (bitIndicator + 1) % 4) == 0) {
                    data[index++] = curByte;
                    curByte = 0;

                    if (index >= data.length)
                        return true;
                } else {
                    curByte <<= 2;
                }
            }

        return false;
    }

    private static byte[] decompressData(String priKey, byte[] data) {
        byte[] rsa_content = GZipUtil.decompress(data);

        RSACipher.instance().updatePriKey(RSAKey.restoreKey(priKey));
        byte[] src_content = RSACipher.instance().decryptWithPriKey(rsa_content);

        return src_content;
    }

    public static byte[] show(String imgPath, String priKey) {
        BufferedImage bi = null;
        int keySize = -1;

        try {
            keySize = readSecuritySize(imgPath);
            bi = ImageUtil.readImage(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (keySize > 0) {
            byte[] data = new byte[keySize];

            if (!showByte(bi, data))
                break;

            return decompressData(priKey, data);
        }

        return null;
    }
}
