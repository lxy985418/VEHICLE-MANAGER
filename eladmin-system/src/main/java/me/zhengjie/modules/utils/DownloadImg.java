package me.zhengjie.modules.utils;/*
 *@author Lee
 *@date 2021/4/1 17:09
 */

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @program: eladmin
 * @description:
 * @author: Lee
 * @create: 2021-04-01 17:09
 **/

public class DownloadImg {
    public String getImg(String str){
        URL url = null;
        InputStream is = null;
        ByteArrayOutputStream outStream = null;
        HttpURLConnection httpUrl = null;
        try {
            url = new URL(str);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            is = httpUrl.getInputStream();
            outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = is.read(buffer)) != -1) {
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            byte[] temp = outStream.toByteArray();
            String encode = Base64Util.encode(temp);
            return encode;
        }catch (Exception e){
            System.err.println(e);
            return null;

        }
    }
}
