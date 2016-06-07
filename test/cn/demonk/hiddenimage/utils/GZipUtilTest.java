package cn.demonk.hiddenimage.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ligs on 16/6/8.
 */
public class GZipUtilTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCompressStr() throws Exception {
        String testStr="abcdefghijklmnopqrstuvwxyz";
        String gzipStr=GZipUtil.compressStr(testStr);

        System.out.println(gzipStr);

        System.out.println(GZipUtil.decompressStr(gzipStr));
    }

    @Test
    public void testCompress() throws Exception {

    }

    @Test
    public void testCompress1() throws Exception {

    }

    @Test
    public void testDecompress() throws Exception {

    }
}