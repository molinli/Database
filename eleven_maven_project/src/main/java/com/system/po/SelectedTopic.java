package com.system.po;

import java.sql.Timestamp;
import java.util.Date;

/**
 * firchoose表
 *
 * @Author: 莫林立
 * @Date: Created in 2018/1/4
 */
public class SelectedTopic {
    private Integer topicid;

    private Integer studentid;

    private Date fctime;

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }


    public Integer getTopicid() {

        return topicid;
    }

    public Integer getStudentid() {
        return studentid;
    }

        public void setDatetime(Date datetime) {
        this.fctime = datetime;
    }

    public Date getDatetime() {

        return fctime;
    }

}
