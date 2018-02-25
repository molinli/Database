package com.system.service.impl;

import com.system.mapper.SelectedtopicMapper;
import com.system.mapper.StudentMapper;
import com.system.po.*;
import com.system.service.SelectedTopicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 莫林立
 * @Date: Created in 2018/1/5
 */
@Service
public class SelectedTopicServiceImpl implements SelectedTopicService{

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SelectedtopicMapper selectedtopicMapper;


    public SelectedTopicCustom findOne(SelectedTopic selectedTopic) throws Exception {
//        SelectedtopicExample example = new SelectedtopicExample();
//        SelectedtopicExample.Criteria criteria = example.createCriteria();
//
//        //获取topicID和studentID查找
//        criteria.andtopic_idEqualTo(selectedTopicCustom.getTopicid());
//        criteria.andStudentidEqualTo(selectedTopicCustom.getStudentid());

        //这里有问题！
        //List<SelectedTopic> list = selectedtopicMapper.selectByExample(example);

        //查看是否有重复的记录
        List<SelectedTopic> list=selectedtopicMapper.selectByStuIDAndTopicID(selectedTopic);

        System.out.println("这里有问题吗？");

        if (list.size() > 0) {
            SelectedTopicCustom sc = new SelectedTopicCustom();
            BeanUtils.copyProperties(list.get(0), sc);

            Student student = studentMapper.selectByPrimaryKey(selectedTopic.getStudentid());

            StudentCustom studentCustom = new StudentCustom();

            BeanUtils.copyProperties(student, studentCustom);

            sc.setStudentCustom(studentCustom);

            return sc;
        }

        return null;
    }

    public void save(SelectedTopicCustom selectedTopicCustom) throws Exception {
        selectedtopicMapper.insert(selectedTopicCustom);
    }


    public void remove(SelectedTopic selectedTopic) throws Exception {
//        SelectedtopicExample example = new SelectedtopicExample();
//
//        SelectedtopicExample.Criteria criteria = example.createCriteria();
//
//        criteria.andtopic_idEqualTo(selectedTopicCustom.getTopicid());
//
//        criteria.andStudentidEqualTo(selectedTopicCustom.getStudentid());

        selectedtopicMapper.deleteByCustom(selectedTopic);
    }


}
