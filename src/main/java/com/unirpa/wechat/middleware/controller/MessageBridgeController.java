package com.unirpa.wechat.middleware.controller;

import com.unirpa.wechat.middleware.service.MessageBridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MessageBridgeController {

    private final MessageBridgeService messageBridgeService;

    @Autowired
    public MessageBridgeController(MessageBridgeService messageBridgeService) {
        this.messageBridgeService = messageBridgeService;
    }

    @PostMapping()
    public String receive(@RequestBody String msg) {
        return messageBridgeService.receive(msg);
    }
}
