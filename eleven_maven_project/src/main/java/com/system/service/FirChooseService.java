package com.system.service;

import com.system.po.SelectedTopic;

import java.util.List;

public interface FirChooseService {

    //获取所有表单信息
    boolean findAll();

    //根据topiciD获取学生记录
    List<SelectedTopic> findByTopicId(Integer topicId,Integer toPageNo) throws  Exception;

    //根据topicId选择的学生总数
    int getCountByTopicId(Integer topicId) throws Exception;

}
