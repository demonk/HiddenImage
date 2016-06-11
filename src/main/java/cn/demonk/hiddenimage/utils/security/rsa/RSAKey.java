package cn.demonk.hiddenimage.utils.security.rsa;

import cn.demonk.hiddenimage.utils.HashWrapper;
import cn.demonk.hiddenimage.utils.file.ReadUtil;
import cn.demonk.hiddenimage.utils.file.WriteUtil;
import javafx.util.Pair;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by ligs on 6/11/16.
 */
public class RSAKey {
    private static final String ALGORITHM = "RSA";
    private static final int DEFAULT_SIZE = 1024;

    public static PrivateKey getPrivateKey(byte[] prikey) {
        PrivateKey privateKey = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM,
                    new BouncyCastleProvider());
            PKCS8EncodedKeySpec pkcs8Spec = new PKCS8EncodedKeySpec(prikey);

            privateKey = keyFactory.generatePrivate(pkcs8Spec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static PublicKey getPublicKey(byte[] pubkey) {
        PublicKey publicKey = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM,
                    new BouncyCastleProvider());
            X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(pubkey);

            publicKey = keyFactory.generatePublic(x509Spec);

            return publicKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    /**
     * 自动生成一对密钥
     *
     * @return
     */
    public static Pair<PrivateKey, PublicKey> genKey() {
        return genKey(DEFAULT_SIZE);
    }

    /**
     * 自动生成一对密钥
     *
     * @param keysize
     * @return
     */
    public static Pair<PrivateKey, PublicKey> genKey(int keysize) {
        KeyPairGenerator keyPairGenerator = null;
        Pair<PrivateKey, PublicKey> rsaKeyPair = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(keysize, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            rsaKeyPair = new Pair<PrivateKey, PublicKey>(keyPair.getPrivate(), keyPair.getPublic());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return rsaKeyPair;
    }

    public static void saveKey(String path, Key key) {
        if (key != null) {
            String hashkey = HashWrapper.toHexString(key.getEncoded());
            WriteUtil.saveFile(path, hashkey);
        }
    }

    public static byte[] restoreKey(String path) {
        String content = ReadUtil.readFile(path);
        return HashWrapper.toBytes(content);
    }
}
