package com.system.po;

/**
 * @Author: 莫林立
 * @Date: Created in 2018/1/8
 */
public class TopicAndStuTeaInfo extends Topic {
    private String studentName;

    private String teacherName;

    public String getStudentName() {
        return studentName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "TopicAndStuTeaInfo{" +
                "studentName='" + studentName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
