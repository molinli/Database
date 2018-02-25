package com.system.service.impl;

import com.system.mapper.SelectedtopicMapper;
import com.system.po.PagingVO;
import com.system.po.SelectedTopic;
import com.system.service.FirChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirChooseServiceImpl implements FirChooseService{

    @Autowired
    private SelectedtopicMapper selectedtopicMapper;

    public boolean findAll() {

        return false;
    }

    public List<SelectedTopic> findByTopicId(Integer topicId,Integer toPageNo) throws Exception {

        PagingVO pagingVO=new PagingVO();

        pagingVO.setToPageNo(toPageNo);

        List<SelectedTopic> list=selectedtopicMapper.findByTopicIdWithPage(topicId,pagingVO.getTopageNo(),pagingVO.getPageSize());

        return list;
    }

    public int getCountByTopicId(Integer topicId) throws Exception {
        Integer count=selectedtopicMapper.getCountByTopicId(topicId);

        return count;
    }
}
