package cn.demonk.hiddenimage.utils.security.rsa;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by ligs on 6/11/16.
 */
public class RSADecrypt {

    private static final String RSA_ALGORITHM = "RSA";
    private static final String PADDING_METHOD = "RSA/ECB/PKCS1Padding";

    private static final int DECRYPT_MODE_PUBLIC = 1;
    private static final int DECRYPT_MODE_PRIVATE = 2;

    /**
     * 使用对应的key对数据data进行加密
     *
     * @param key
     * @param data
     * @return
     */
    public static byte[] decryptWithPubKey(PublicKey key, byte[] data) throws Exception {
        return decryptWithKey(DECRYPT_MODE_PUBLIC, key, data);
    }

    public static byte[] decryptWithPriKey(PrivateKey key, byte[] data) throws Exception {
        return decryptWithKey(DECRYPT_MODE_PRIVATE, key, data);
    }

    private static byte[] decryptWithKey(int mode, Key key, byte[] data) throws Exception {
//        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
//        PKCS8EncodedKeySpec encodedKeySpec=new PKCS8EncodedKeySpec(key);
//        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
//
//        Key customKey = null;
//        if (DECRYPT_MODE_PUBLIC == mode) {
//            customKey = keyFactory.generatePublic(encodedKeySpec);
//        } else if (DECRYPT_MODE_PRIVATE == mode) {
//            customKey = keyFactory.generatePrivate(encodedKeySpec);
//        }

        Cipher cipher = Cipher.getInstance(PADDING_METHOD);
        cipher.init(Cipher.DECRYPT_MODE, key);

        return cipher.doFinal(data);
    }

}
