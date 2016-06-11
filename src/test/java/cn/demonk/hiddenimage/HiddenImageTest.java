package cn.demonk.hiddenimage;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ligs on 6/11/16.
 */
public class HiddenImageTest {
    @Test
    public void hidden() throws Exception {
        String path = "/media/Data/StudyWorkSpace/HiddenImage/doc/test.png";
        String content = "A small cat sleep in the cushion";
        HiddenImage.hidden(path, content);
    }

}