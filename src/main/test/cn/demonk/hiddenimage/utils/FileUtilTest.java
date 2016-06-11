package cn.demonk.hiddenimage.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ligs on 16/6/7.
 */
public class FileUtilTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testReadFile() throws Exception {
        String filePath="/Users/ligs/MyWorks/HiddenImage/doc/test.txt";
        String content=FileUtil.readFile(filePath,5);
        System.out.println(content);
    }
}