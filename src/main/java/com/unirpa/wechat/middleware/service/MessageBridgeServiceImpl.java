package com.unirpa.wechat.middleware.service;

import com.alibaba.fastjson.JSONObject;
import com.unirpa.wechat.middleware.domain.response.AIForceBotResponse;
import com.unirpa.wechat.middleware.domain.response.AIForceBotResponseModel.ClarifyModel.RelateInfo;
import com.unirpa.wechat.middleware.domain.response.AIForceBotResponseModel.Info;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Service
public class MessageBridgeServiceImpl implements MessageBridgeService {

    @Override
    public String receive(String wechatMsg) {
        try {
            Document document = DocumentHelper.parseText(wechatMsg);
            Element root = document.getRootElement();
            if (root.element("MsgType").getText().equals("text")) {
                Document replyDocument = DocumentHelper.createDocument();
                Element replyRoot = replyDocument.addElement("xml");
                replyRoot.addElement("MsgType").addCDATA("text");
                replyRoot.addElement("ToUserName").addCDATA(root.element("FromUserName").getText());
                replyRoot.addElement("FromUserName").addCDATA(root.element("ToUserName").getText());
                replyRoot.addElement("CreateTime").addText(System.currentTimeMillis() + "");

                String replyContent = getAIForceBotReply(root.element("Content").getText(), root.element("FromUserName").getText());

                replyRoot.addElement("Content").addCDATA(replyContent);
                System.out.println(replyRoot.element("Content").getText());
                return replyDocument.asXML().split("\n")[1];
            } else {
                return "success";
            }
        } catch (DocumentException e) {
            return "success";
        }
    }

    private String getAIForceBotReply(String wechatReceiveContent, String account) {
        String pubkey = "BX7ayG7IEvy17sYGdRGTzF+FqKS1X82M6+DOKbiGl5g";
        String privateKey = "ad3a5840d276540582145407ce43cade";
        String url = "http://10.3.2.11:8000/common/query";
        String sessionId = "e4e4b9dd-32f9-43bc-a54c-1651f88bd166";
        String question = wechatReceiveContent;
        String pubkeyEncode = URLEncoder.encode(pubkey);
        String questionEncode = URLEncoder.encode(question);

        // String rawStr = "18582310368" + pubkey + question + sessionId + privateKey;
        String rawStr = account + pubkey + question + sessionId + privateKey;
        BASE64Encoder encoder = new BASE64Encoder();
        String base64Str = encoder.encode(rawStr.getBytes());
        base64Str = base64Str.replace("\r", "");
        base64Str = base64Str.replace("\n", "");
        String md5Str = MD5(base64Str);
        String param = "pubkey=" + pubkeyEncode +
                "&question=" + questionEncode +
                "&sign=" + md5Str +
                //"&cid=kf" +
                //"&ip=59.173.240.186"+
                //"&client=-"+
                //"&im=-"+
                "&account=" + account +
                "&sessionId=" + sessionId;
        System.out.println(param);
        String responseStr = sendGet(url, param);
        System.out.println(responseStr);

        JSONObject response = JSONObject.parseObject(responseStr);

        AIForceBotResponse aiForceBotResponse = JSONObject.toJavaObject(response, AIForceBotResponse.class);
        System.out.println(aiForceBotResponse.getMsg());
        System.out.println(aiForceBotResponse.getType());
        System.out.println(aiForceBotResponse.getBizId());
        System.out.println(aiForceBotResponse.getAnswerType());

        System.out.println(aiForceBotResponse.getClarify() != null ? aiForceBotResponse.getClarify().getAnswer() : null);
        if (aiForceBotResponse.getClarify() != null) {
            String answer = aiForceBotResponse.getClarify().getAnswer();
            List<RelateInfo> relateInfoList = aiForceBotResponse.getClarify().getRelateInfo();
            for (RelateInfo relateInfo : relateInfoList) {
                answer += "  " + relateInfo.getIndex() + "：" + relateInfo.getQt();
            }
            return answer;
        }

        System.out.println(aiForceBotResponse.getInfo() != null ? aiForceBotResponse.getInfo().toArray(new Info[0])[0].getAnswer() : null);
        if (aiForceBotResponse.getInfo() != null) {
            return aiForceBotResponse.getInfo().toArray(new Info[0])[0].getAnswer();
        }

        return "AI机器人出错";
    }

    private String sendGet(String url, String param) {
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

    private String MD5(String sourceStr) {
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
