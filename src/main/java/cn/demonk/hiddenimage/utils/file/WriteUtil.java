package cn.demonk.hiddenimage.utils.file;

import cn.demonk.hiddenimage.utils.StreamUtil;
import cn.demonk.hiddenimage.utils.StringUtil;

import java.io.*;

/**
 * Created by ligs on 6/11/16.
 */
public class WriteUtil {
    /**
     * 将流保存到指定位置
     *
     * @param path
     * @param input
     */
    public static void saveFile(String path, InputStream input) {

        File saveFile = new File(path);
        if (saveFile.exists())
            saveFile.delete();

        try {
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(saveFile));

            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = input.read(buffer)) != -1) {
                output.write(buffer, 0, count);
            }

            output.flush();
            StreamUtil.close(output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将content输出到path文件
     *
     * @param path
     * @param content
     */
    public static void saveFile(String path, String content) {

        if (StringUtil.isEmpty(content))
            return;

        ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes());
        saveFile(path, input);
    }
}
