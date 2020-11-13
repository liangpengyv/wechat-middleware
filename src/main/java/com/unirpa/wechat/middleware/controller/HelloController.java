package com.unirpa.wechat.middleware.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping(value = "/")
public class HelloController {

    @GetMapping
    public String hello(String signature,
                        String timestamp,
                        String nonce,
                        String echostr) {
        // 公众号-开发-基础配置中的 Token 信息
        String token = "unirpawechat2020";

        // token，timestamp，nonce 字典序排序得字符串 list
        ArrayList<String> list = new ArrayList<>();
        list.add(token);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);

        // 哈希算法加密 list 得到 hashcode
        String generateSignature = DigestUtils.shaHex(list.get(0) + list.get(1) + list.get(2));

        if (signature.equals(generateSignature)) {
            return echostr;
        }

        return "";
    }
}
