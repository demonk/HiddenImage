package cn.demonk.hiddenimage.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ligs on 16/6/8.
 */
public class Base64UtilTest {

    @Test
    public void testEncode() throws Exception {
        String testStr="Helloworld";
        String btest=Base64Util.encode(testStr.getBytes());

        System.out.println(btest);
        System.out.println(new String(Base64Util.decode(btest)));


    }

    @Test
    public void testDecode() throws Exception {

    }
}