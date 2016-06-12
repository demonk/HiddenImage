package cn.demonk.hiddenimage.utils.security.rsa;

import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

import static org.junit.Assert.*;

/**
 * Created by ligs on 6/11/16.
 */
public class RSAKeyTest {
    private PrivateKey priKey;
    private PublicKey pubKey;

    private static final String prikeyPath = "/media/Data/StudyWorkSpace/HiddenImage/doc/pri.key";
    private static final String pubkeyPath = "/media/Data/StudyWorkSpace/HiddenImage/doc/pub.key";


    @Before
    public void setUp() throws Exception {
        genKey();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getPrivateKey() throws Exception {

    }

    @Test
    public void getPublicKey() throws Exception {

    }

    public void genKey() throws Exception {
        Pair<PrivateKey, PublicKey> keys = RSAKey.genKey();

        assertNotEquals(keys, null);

        priKey = keys.getKey();
        pubKey = keys.getValue();

        assertNotEquals(priKey, null);
        assertNotEquals(pubKey, null);

        byte[] a1 = priKey.getEncoded();
        PrivateKey a2 = RSAKey.getPrivateKey(a1);
        assertArrayEquals(a1, a2.getEncoded());

        byte[] b1 = pubKey.getEncoded();
        PublicKey b2 = RSAKey.getPublicKey(b1);
        assertArrayEquals(b1, b2.getEncoded());
    }

    @Test
    public void saveKey() throws Exception {
        RSAKey.saveKey(prikeyPath, priKey);
        RSAKey.saveKey(pubkeyPath, pubKey);

        restoreKey();
    }

    public void restoreKey() throws Exception {
//        String text = "helloworld";
//
//
//        byte[] pribytes = RSAKey.restoreKey(prikeyPath);
//        byte[] pubytes = RSAKey.restoreKey(pubkeyPath);
//
//        assertArrayEquals(pribytes, priKey.getEncoded());
//        assertArrayEquals(pubytes, pubKey.getEncoded());
//
//        PrivateKey prk = RSAKey.getPrivateKey(pribytes);
//        PublicKey pbk = RSAKey.getPublicKey(pubytes);
//
//        assertArrayEquals(prk.getEncoded(), priKey.getEncoded());
//        assertArrayEquals(pbk.getEncoded(), pubKey.getEncoded());
//
//        RSACipher.instance().update(pubKey, prk);
//        byte[] a1 = RSACipher.instance().encryptWithPubKey(text.getBytes());
//        byte[] a2 = RSACipher.instance().decryptWithPriKey(a1);
//
//        assertArrayEquals(a2, text.getBytes());
    }
}