package com.unirpa.wechat.middleware.domain.response.AIForceBotResponseModel;

import java.util.List;

public class Info {

    private String answer;

    private String answerType;

    private Integer class1Id;

    private String class1Name;

    private Integer class2Id;

    private String class2Name;

    private String confidence;

    private Integer bizType;

    private String docType;

    private Integer id;

    private Integer index;

    private String link;

    private String question;

    private List<String> relate;

    private String validBeginTime;

    private String validEndTime;

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

    public Integer getClass1Id() {
        return class1Id;
    }

    public void setClass1Id(Integer class1Id) {
        this.class1Id = class1Id;
    }

    public String getClass1Name() {
        return class1Name;
    }

    public void setClass1Name(String class1Name) {
        this.class1Name = class1Name;
    }

    public Integer getClass2Id() {
        return class2Id;
    }

    public void setClass2Id(Integer class2Id) {
        this.class2Id = class2Id;
    }

    public String getClass2Name() {
        return class2Name;
    }

    public void setClass2Name(String class2Name) {
        this.class2Name = class2Name;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getValidBeginTime() {
        return validBeginTime;
    }

    public void setValidBeginTime(String validBeginTime) {
        this.validBeginTime = validBeginTime;
    }

    public String getValidEndTime() {
        return validEndTime;
    }

    public void setValidEndTime(String validEndTime) {
        this.validEndTime = validEndTime;
    }
}
