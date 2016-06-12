package cn.demonk.hiddenimage.utils.security.rsa;

import javafx.util.Pair;

import java.security.*;

/**
 * Created by ligs on 6/11/16.
 */
public class RSACipher {

    private static final RSACipher INSTANCE = new RSACipher();

    /**
     * public key for RSA
     */
    //TODO 密钥保存在内存，当代码被修改时容易被轻易破解
    private PrivateKey mPriKey = null;
    private PublicKey mPubKey = null;

    private RSACipher() {
    }

    public static RSACipher instance() {
        return INSTANCE;
    }


    /**
     * 使用公钥加密
     *
     * @param data
     * @return
     */
    public byte[] encryptWithPubKey(byte[] data) {
        try {
            return RSAEncrypt.encryptWithPubKey(mPubKey, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 使用私钥解密
     *
     * @param data
     * @return
     */
    public byte[] decryptWithPriKey(byte[] data) {
        try {
            return RSADecrypt.decryptWithPriKey(mPriKey, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePubKey(byte[] pubKey) {
        mPubKey = RSAKey.getPublicKey(pubKey);
    }

    public void updatePriKey(byte[] priKey) {
        mPriKey = RSAKey.getPrivateKey(priKey);
    }

    public void update(byte[] pubkey, byte[] prikey) {
        updatePubKey(pubkey);
        updatePriKey(prikey);
    }
}
