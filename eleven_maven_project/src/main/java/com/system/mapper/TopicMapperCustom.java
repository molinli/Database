package com.system.mapper;

import com.system.po.PagingVO;
import com.system.po.TopicCustom;
import com.system.po.Topic;

import java.util.List;

public interface TopicMapperCustom {

    //分页查询课题信息
    List<Topic> findByPaging(PagingVO pagingVO) throws Exception;

    //获取分页查询课题信息并且stu!=null
    List<Topic> findByPagingStuNoNull(PagingVO pagingVO)throws Exception;

    //指定stuid获取到topic表里的记录
    Topic findByStuID( Integer topicId) throws Exception;

}
