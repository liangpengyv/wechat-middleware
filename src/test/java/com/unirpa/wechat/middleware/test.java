package com.unirpa.wechat.middleware;

import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

public class test {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // BX7ayG7IEvy17sYGdRGTzF+FqKS1X82M6+DOKbiGl5g
        String pubkey = "BX7ayG7IEvy17sYGdRGTzF+FqKS1X82M6+DOKbiGl5g";
        // ad3a5840d276540582145407ce43cade
        String privateKey = "ad3a5840d276540582145407ce43cade";
        String url = "http://10.3.2.11:8000/common/query";
        String sessionId = "e4e4b9dd-32f9-43bc-a54c-1651f88bd166";
        String question = "再见";
        String pubkeyEncode = URLEncoder.encode(pubkey);
        String questionEncode = URLEncoder.encode(question);

        String rawStr = "18582310368" + pubkey + question + sessionId + privateKey;
        BASE64Encoder encoder = new BASE64Encoder();
        String base64Str = encoder.encode(rawStr.getBytes());
        base64Str = base64Str.replace("\r", "");
        base64Str = base64Str.replace("\n", "");
        System.out.println(base64Str);
        String md5Str = MD5(base64Str);
        System.out.println(md5Str);
        String param = "pubkey=" + pubkeyEncode +
                "&question=" + questionEncode +
                "&sign=" + md5Str +
                //"&cid=kf" +
                //"&ip=59.173.240.186"+
                //"&client=-"+
                //"&im=-"+
                "&account=18582310368" +
                "&sessionId=" + sessionId;
        System.out.println(param);
        System.out.println(sendGet(url, param));
    }

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("【Get失败】" + e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    private static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
}
