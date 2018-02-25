package com.system.mapper;

import com.system.po.SelectedTopic;
import com.system.po.SelectedTopicCustom;
import com.system.po.SelectedtopicExample;

import java.util.List;

/**
 * @Author: 莫林立
 * @Date: Created in 2018/1/5
 */
public interface SelectedtopicMapper {

    int insert(SelectedTopic record);

    //查找初选表里是否重复topicID和stuID的条目 弃用
    List<SelectedTopic> selectByExample(SelectedtopicExample example);

    //查找初选表里是否重复topicID和stuID的条目
    List<SelectedTopic> selectByStuIDAndTopicID(SelectedTopic selectedTopic);

    //删除firchoose条目
    int deleteByCustom(SelectedTopic SelectedTopic);

    //指定topicId获得总数
    int getCountByTopicId(Integer topicId);

    //根据页面大小选择当前课题
    List<SelectedTopic> findByTopicIdWithPage(Integer topicId,Integer toPageNo,Integer pageSize);
}
