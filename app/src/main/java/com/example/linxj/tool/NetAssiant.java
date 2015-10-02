package com.example.linxj.tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * Created by lvjia on 2015/8/31.
 */
public class NetAssiant {
    private static StringBuilder sb;
    private String url ;
    public NetAssiant(String url){
        this.url = url;
    }
    public String get(){

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(
                    url)
                    .openConnection();
            conn.setConnectTimeout(3000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
			/*updateBookInfoView("无法获取图书信息。错误编号："
					+ conn.getResponseCode());*/
                return " ";
            }else{
                GZIPInputStream gzis = (GZIPInputStream)conn.getContent();
                InputStreamReader reader = new InputStreamReader(gzis);
                BufferedReader in = new BufferedReader(reader);
                sb = new StringBuilder();
                String readed;
                while ((readed = in.readLine()) != null) {
                    sb.append(readed);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
