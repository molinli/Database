package com.system.po;
/**
 * 表明当前系统处于的可选择状态
 *     0--老师出题阶段
        1--学生选题阶段
         2--老师定题
            3--补选阶段
                4--给分阶段
 * @Author: 莫林立
 * @Date: Created in 2018/1/8
 */
public class Status {
    //状态量
    private Integer status;
    private String content;
    private Integer flag;

    public Integer getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Status{" +
                "status=" + status +
                ", content='" + content + '\'' +
                '}';
    }
}
