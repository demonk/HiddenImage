package cn.demonk.hiddenimage;

import cn.demonk.hiddenimage.utils.security.rsa.RSAKey;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

import static org.junit.Assert.*;

/**
 * Created by ligs on 6/12/16.
 */
public class MainTest {

    String priKey = "/media/Data/StudyWorkspace/HiddenImage/doc/private.key";
    String pubKey = "/media/Data/StudyWorkspace/HiddenImage/doc/public.key";
    String hiddenImg = "/media/Data/StudyWorkspace/HiddenImage/doc/ida.png";
    String showImg = "/media/Data/StudyWorkspace/HiddenImage/doc/sec_ida.png";
    String content = "A small cat sleep in the cushion";


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void mainTest() throws Exception {
        saveKey();
        hidden();
        show();
    }

    public void saveKey() throws Exception {
        Pair<PrivateKey, PublicKey> keys = RSAKey.genKey();

        RSAKey.saveKey(pubKey, keys.getValue());
        RSAKey.saveKey(priKey, keys.getKey());

        assertTrue(new File(priKey).exists());
        assertTrue(new File(pubKey).exists());
    }

    public void hidden() throws Exception {
        HiddenImage.hidden(hiddenImg, pubKey, content);

        File secFile = new File(showImg);
        assertTrue(secFile.exists());
    }

    public void show() throws Exception {
        byte[] data = ShowImage.show(showImg, priKey);

        String content = new String(data);

        assertEquals(content, this.content);
        System.out.println("content="+content);
    }
}
