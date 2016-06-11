package cn.demonk.hiddenimage.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by ligs on 16/6/8.
 */
public class StreamUtil {

    public static final void close(Closeable obj)
    {
        if(obj!=null)
        {
            try {
                obj.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
