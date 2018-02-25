package com.system.mapper;

import com.system.po.*;

import java.util.List;
import java.util.Map;

public interface TopicMapper {

    //计算课题总数
    int countByExample(TopicExample topicExample) ;

    //计算stu_id字段不为空的总数
    int countByStuNotNull(TopicExample topicExample);

    //计算指定老师ID的topic总数
    int countByTeaId(Integer teacherId);

    //计算指定老师ID和课题Nameo总数
    int countByTeaIdAndName(Integer teacherId,String name);

    //选择所有课题
    List<Topic> selectByExample(TopicExample topicExample) ;

    //根据主键选择课题
    Topic selectByPrimaryKey(Integer topicId);

    //选择topic表里stu不为空的集合
    List<Topic> selectByStuNotNull(TopicExample topicExample);

    //根据名字进行模糊查询
    List<Topic> selectByName(String name);

    //根据教师ID进行查询
    List<Topic> selectByTeaId(Integer teacherId,Integer toPageNo,Integer pageSize);

    //根据教师ID和课题名字进行查询
    List<Topic> selectByTeaIdAndName(Integer teacherId,String name,Integer toPageNo,Integer pageSize);

    Topic selectByTopicId(Integer topicId);

    //更新学生ID
    int updateTopic(Topic topic);

    int updateTopicInfo(Topic topic);

    int insert(Integer topicId,Integer teaId,
               String type,String title,String major,
               String content,String request);

    int deleteByPrimaryKey(Integer topicId);

    List<Integer> selectByStuNull();

    List<Topic> findByStuNull();

 }
