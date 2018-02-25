package com.system.po;

/**
 * @Author: 莫林立
 * @Date: Created in 2018/1/4
 */
public class TopicForSelect extends Topic{

    private String stuName;
    private String teaName;

    private Integer score;

    public String getStuName() {
        return stuName;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "TopicForSelect{" +
                "stuName='" + stuName + '\'' +
                ", teaName='" + teaName + '\'' +
                '}';
    }
}
