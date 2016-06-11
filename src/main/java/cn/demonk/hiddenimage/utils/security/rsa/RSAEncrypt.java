package cn.demonk.hiddenimage.utils.security.rsa;

import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

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
        EncodedKeySpec encodedKeySpec = null;
//        PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(key);
//        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);

        Key customKey = null;

        /* Add PKCS#8 formatting */
//        ASN1EncodableVector v = new ASN1EncodableVector();
//        v.add(new ASN1Integer(0));
//        ASN1EncodableVector v2 = new ASN1EncodableVector();
//        v2.add(new ASN1ObjectIdentifier(PKCSObjectIdentifiers.rsaEncryption.getId()));
//        v2.add(DERNull.INSTANCE);
//        v.add(new DERSequence(v2));
//        v.add(new DEROctetString(key));
//        ASN1Sequence seq = new DERSequence(v);
//        byte[] privKey = seq.getEncoded("DER");

//        if (ENCRYPT_MODE_PUBLIC == mode) {
//            encodedKeySpec = new X509EncodedKeySpec(privKey);
//            customKey = keyFactory.generatePublic(encodedKeySpec);
//        } else if (ENCRYPT_MODE_PRIVATE == mode) {
//            encodedKeySpec = new PKCS8EncodedKeySpec(privKey);
//            customKey = keyFactory.generatePrivate(encodedKeySpec);
//        }

        Cipher cipher = Cipher.getInstance(PADDING_METHOD);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(data);
    }
}
