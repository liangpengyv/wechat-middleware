package com.unirpa.wechat.middleware.domain.response.AIForceBotResponseModel;

import com.unirpa.wechat.middleware.domain.response.AIForceBotResponseModel.ClarifyModel.RelateInfo;

import java.util.List;

public class Clarify {

    private String answer;

    private String answerType;

    private String currentNodeId;

    private String currentNodeName;

    private String question;

    private List<String> relate;

    private List<RelateInfo> relateInfo;

    private String speechType;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getCurrentNodeId() {
        return currentNodeId;
    }

    public void setCurrentNodeId(String currentNodeId) {
        this.currentNodeId = currentNodeId;
    }

    public String getCurrentNodeName() {
        return currentNodeName;
    }

    public void setCurrentNodeName(String currentNodeName) {
        this.currentNodeName = currentNodeName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getRelate() {
        return relate;
    }

    public void setRelate(List<String> relate) {
        this.relate = relate;
    }

    public List<RelateInfo> getRelateInfo() {
        return relateInfo;
    }

    public void setRelateInfo(List<RelateInfo> relateInfo) {
        this.relateInfo = relateInfo;
    }

    public String getSpeechType() {
        return speechType;
    }

    public void setSpeechType(String speechType) {
        this.speechType = speechType;
    }
}
