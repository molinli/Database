package com.system.service;

import com.system.po.TopicCustom;
import com.system.po.Topic;
import java.util.List;

public interface TopicService {

    boolean findAll() throws  Exception;

    //获取课题总数
    int getCountTopic() throws Exception;

    //获取指定老师的课题总数
    int getCountTopicByTea(Integer teacherId) throws Exception;

    //获取指定老师和课题名称的课题总数
    int getCountTopicByTeaAndName(Integer teacherId,String name) throws Exception;

    //获取stu_id字段不为空的总数
    int getCountByStuNotNull() throws  Exception;

    //获取分页查询课题信息
    List<Topic> findByPaging(Integer toPageNo) throws Exception;

    //获取分页查询课题信息并且stu!=null
    List<Topic> findByPagingStuNoNull(Integer toPageNo)throws Exception;

    Topic findByStuID(Integer id) throws Exception;

    //根据名字查询
    List<Topic> findByName(String name) throws Exception;

    //根据教师ID查询
    List<Topic> findByTeacherID(Integer teacherId,Integer toPageNo) throws Exception;

    //根据教师ID和课题名字进行模糊查询
    List<Topic> findByTeacherIDAndTopicName(Integer techerId,String findByName,Integer toPageNo) throws Exception;

    //根据课题号找到topic
    Topic findByTopicId(Integer topicId) throws Exception;

    //更新课题记录
    void update(Topic topic) throws Exception;

    //把学生的信息更新进入topic
    void updateForStu(Topic topic) throws Exception;

    Boolean save(Topic topic)throws Exception;

    void delete(Integer topicId)throws Exception;

    //找到topic表里stu_id不为空的记录
    List<Integer> findByStuNullnot()throws Exception;

    List<Topic> findByStuNull()throws Exception;

    //更新学生学号
    void updateStu(Topic topic)throws   Exception;
}
