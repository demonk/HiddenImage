package cn.demonk.hiddenimage.utils.security.rsa;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by ligs on 6/11/16.
 */
public class RSAEncrypt {

    private static final String RSA_ALGORITHM = "RSA";
    private static final String PADDING_METHOD = "RSA/ECB/PKCS1Padding";

    private static final int ENCRYPT_MODE_PUBLIC = 1;
    private static final int ENCRYPT_MODE_PRIVATE = 2;

    /**
     * 使用KEY对数据data进行解密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] encryptWithPubKey(PublicKey key, byte[] data) throws Exception {
        return encrypt(ENCRYPT_MODE_PUBLIC, key, data);
    }


    public static byte[] encryptWithPriKey(PrivateKey key, byte[] data) throws Exception {
        return encrypt(ENCRYPT_MODE_PRIVATE, key, data);
    }

    private static byte[] encrypt(int mode, Key key, byte[] data) throws Exception {

        Cipher cipher = Cipher.getInstance(PADDING_METHOD);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(data);
    }
}
