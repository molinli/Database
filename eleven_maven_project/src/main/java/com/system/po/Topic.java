package com.system.po;

/**
 * 课题
 */
public class Topic {

    private int topicId;

    private int teaId;

    private int stuId;

    private String topicType;

    private String topicTitle;

    private String topicMajor;

    private String topicContent;

    private String topicRequest;

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public void setTeaId(int teaId) {
        this.teaId = teaId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public void setTopicMajor(String topicMajor) {
        this.topicMajor = topicMajor;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public void setTopicRequest(String topicRequest) {
        this.topicRequest = topicRequest;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getTeaId() {
        return teaId;
    }

    public int getStuId() {
        return stuId;
    }

    public String getTopicType() {
        return topicType;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public String getTopicMajor() {
        return topicMajor;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public String getTopicRequest() {
        return topicRequest;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", teaId=" + teaId +
                ", stuId=" + stuId +
                ", topicType='" + topicType + '\'' +
                ", topicTitle='" + topicTitle + '\'' +
                ", topicMajor='" + topicMajor + '\'' +
                ", topicContent='" + topicContent + '\'' +
                ", topicRequest='" + topicRequest + '\'' +
                '}';
    }
}
