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
    private Pair<PrivateKey, PublicKey> mKey;

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
            return RSAEncrypt.encryptWithPubKey(mKey.getValue(), data);
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
            return RSADecrypt.decryptWithPriKey(mKey.getKey(), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(byte[] pubkey, byte[] prikey) {
        PublicKey pubKey = RSAKey.getPublicKey(pubkey);
        PrivateKey priKey = RSAKey.getPrivateKey(prikey);
        update(pubKey, priKey);
    }

    public void update(PublicKey pubkey, PrivateKey prikey) {
        mKey = new Pair<PrivateKey, PublicKey>(prikey, pubkey);
    }

}
