package cn.demonk.hiddenimage.utils.file;

import cn.demonk.hiddenimage.utils.StringUtil;

import java.io.*;

/**
 * Created by ligs on 6/11/16.
 */
public class ReadUtil {

    public static String readFile(String file) {
        return readFile(file, -1);
    }

    /**
     * read file content
     *
     * @param file
     * @param maxLine max line num about file for read
     * @return
     */
    public static String readFile(String file, int maxLine) {
        File fileObj = new File(file);

        if (!fileObj.exists()) {
            return "";
        }
        StringBuilder content = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileObj));
            String curLine;
            while ((maxLine == -1 || maxLine-- > 0) && !StringUtil.isEmpty(curLine = reader.readLine())) {
                content.append(curLine);
                content.append("\n");
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
