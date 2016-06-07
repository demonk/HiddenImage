package cn.demonk.hiddenimage.utils;

import java.io.*;

/**
 * Created by ligs on 16/6/7.
 */
public class FileUtil {

    /**
     * read file content
     * @param file
     * @param maxLine
     *      max line num about file for read
     * @return
     */
    public static String readFile(String file,int maxLine){
        File fileObj=new File(file);

        if(!fileObj.exists()){
            return "";
        }
        StringBuilder content=new StringBuilder();

        try {
            BufferedReader reader=new BufferedReader(new FileReader(fileObj));
            String curLine;
            while(maxLine-->0&&!StringUtil.isEmpty(curLine=reader.readLine())) {
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
