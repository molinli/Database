package com.system.service;

import com.system.po.SelectedTopic;
import com.system.po.SelectedTopicCustom;

/**
 * @Author: 莫林立
 * @Date: Created in 2018/1/5
 */
public interface SelectedTopicService {

    //查询指定学生成绩
    SelectedTopicCustom findOne(SelectedTopic selectedTopic) throws Exception;

    //选课
    void save(SelectedTopicCustom selectedTopicCustom) throws Exception;

    //退选课题
    void remove(SelectedTopic selectedTopic) throws  Exception;

}
