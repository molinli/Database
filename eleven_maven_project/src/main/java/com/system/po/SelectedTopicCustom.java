package com.system.po;

import java.sql.Timestamp;

/**
 * @Author: 莫林立
 * @Date: Created in 2018/1/4
 */
public class SelectedTopicCustom extends SelectedTopic{
    //添加课题额外字段
    private TopicCustom topicCustom;

    //添加学生额外字段
    private StudentCustom studentCustom;
    //判断该学生是否已经完成该课题
    private Boolean over=false;

    private String teacherName;

    public void setTopicCustom(TopicCustom topicCustom) {
        this.topicCustom = topicCustom;
    }

    public void setStudentCustom(StudentCustom studentCustom) {
        this.studentCustom = studentCustom;
    }

    public void setOver(Boolean over) {
        this.over = over;
    }

    public TopicCustom getTopicCustom() {

        return topicCustom;
    }

    public StudentCustom getStudentCustom() {
        return studentCustom;
    }

    public Boolean getOver() {
        return over;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}

