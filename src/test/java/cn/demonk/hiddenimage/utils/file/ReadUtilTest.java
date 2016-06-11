package cn.demonk.hiddenimage.utils.file;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ligs on 6/11/16.
 */
public class ReadUtilTest {
    @Test
    public void readFile() throws Exception {
        String file = "/media/Data/StudyWorkSpace/HiddenImage/doc/test.txt";
        System.out.println(ReadUtil.readFile(file));
    }

}