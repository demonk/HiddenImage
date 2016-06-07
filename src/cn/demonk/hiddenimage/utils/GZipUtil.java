package cn.demonk.hiddenimage.utils;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by ligs on 16/6/7.
 */
public class GZipUtil {

    private static final int MAX_BUFFER=1024;

    public static String compressStr(String srcStr)
    {
        byte[] b=compress(srcStr.getBytes());
        return Base64Util.encode(b);
    }

    public static byte[] compress(byte[] srcByte)
    {
        ByteArrayInputStream in=new ByteArrayInputStream(srcByte);
        ByteArrayOutputStream out=new ByteArrayOutputStream();

        compress(in,out);

        byte[] outByte=out.toByteArray();

        StreamUtil.close(out);
        StreamUtil.close(in);

        return outByte;
    }

    public static void compress(InputStream in, OutputStream out)
    {
        try {
            GZIPOutputStream output=new GZIPOutputStream(out);
            byte[] buffer=new byte[MAX_BUFFER];
            int count=0;
            while((count=in.read(buffer))!=-1)
            {
                output.write(buffer,0,count);
            }
            output.finish();
            output.flush();;
            StreamUtil.close(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String decompressStr(String str)
    {
        byte[] srcByte=Base64Util.decode(str);
        srcByte=decompress(srcByte);
        return new String(srcByte);
    }

    public static byte[] decompress(byte[] src)
    {
       ByteArrayInputStream  in=new ByteArrayInputStream(src);
        ByteArrayOutputStream out=new ByteArrayOutputStream();

        decompress(in,out);

       byte[] outByte=out.toByteArray();

        StreamUtil.close(out);
        StreamUtil.close(in);

        return outByte;
    }
    public static void decompress(InputStream in,OutputStream out)
    {
        try {
            GZIPInputStream input=new GZIPInputStream(in);

            byte[] buffer=new byte[MAX_BUFFER];
            int count=0;
            while((count=input.read(buffer))!=-1)
            {
                out.write(buffer,0,count);
            }
            StreamUtil.close(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
