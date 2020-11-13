package com.unirpa.wechat.middleware.domain.response;

import com.unirpa.wechat.middleware.domain.response.AIForceBotResponseModel.Clarify;
import com.unirpa.wechat.middleware.domain.response.AIForceBotResponseModel.Info;

import java.util.List;

public class AIForceBotResponse {

    private String answerType;

    private Integer bizId;

    private Clarify clarify;

    private List<Info> info;

    private String msg;

    private Integer type;

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public Clarify getClarify() {
        return clarify;
    }

    public void setClarify(Clarify clarify) {
        this.clarify = clarify;
    }

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
