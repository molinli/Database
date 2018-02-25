package com.system.service.impl;

import com.system.mapper.CollegeMapper;
import com.system.mapper.TopicMapper;
import com.system.mapper.TopicMapperCustom;
import com.system.po.Topic;
import com.system.po.TopicExample;
import com.system.po.PagingVO;
import com.system.po.TopicCustom;
import com.system.service.TopicService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicServiceImpl implements TopicService{

    @Autowired
    private TopicMapperCustom topicMapperCustom;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    public boolean findAll() throws Exception {

        return false;
    }

    //分页查询课题信息
    public List<Topic> findByPaging(Integer toPageNo) throws Exception{
        PagingVO pagingVO = new PagingVO();

        pagingVO.setToPageNo(toPageNo);

        List<Topic> list = topicMapperCustom.findByPaging(pagingVO);

        return list;
    }

    public TopicCustom findById(Integer id) throws Exception {

        Topic topic=topicMapper.selectByPrimaryKey(id);

        TopicCustom topicCustom=null;

        if(topic!=null){
            topicCustom=new TopicCustom();

            BeanUtils.copyProperties(topicCustom,topic);
        }

        return topicCustom;
    }


    //获取课题总数
    public int getCountTopic() throws Exception {
        //自定义查询对象
        TopicExample topicExample = new TopicExample();
        //通过criteria构造查询条件
        TopicExample.Criteria criteria = topicExample.createCriteria();

        criteria.andTopicnameIsNotNull();

        return topicMapper.countByExample(topicExample);
    }

    //查看topic表里stu_id字段不为空的集合
    public List<Topic> findByPagingStuNoNull(Integer toPageNo) throws Exception {

        PagingVO pagingVO = new PagingVO();

        pagingVO.setToPageNo(toPageNo);

        List<Topic> list = topicMapperCustom.findByPagingStuNoNull(pagingVO);

        return list;
    }

    //获取stu_id字段不为空的总数
    public int getCountByStuNotNull() throws Exception {
        //自定义查询对象
        TopicExample topicExample = new TopicExample();
        //通过criteria构造查询条件
        TopicExample.Criteria criteria = topicExample.createCriteria();

        criteria.andTopicnameIsNotNull();

        return topicMapper.countByStuNotNull(topicExample);

    }

    //根据名字进行模糊查询
    public List<Topic> findByName(String name) throws Exception {

        List<Topic> list = topicMapper.selectByName("%"+name+"%");

        List<Topic> courseCustomList = null;

        if (list != null) {

            courseCustomList = new ArrayList<Topic>();

            for (Topic t : list) {
                Topic topic = new Topic();
                //类拷贝
                org.springframework.beans.BeanUtils.copyProperties(t, topic);
                //获取课程名

                courseCustomList.add(topic);
            }
        }

        return courseCustomList;
    }

    public Topic findByStuID(Integer id) throws Exception {


        Topic topic=topicMapperCustom.findByStuID(id);
        System.out.println("这个findByStuID");
        return topic;
    }

    //根据教师ID查询
    public List<Topic> findByTeacherID(Integer teacherId,Integer toPageNo) throws Exception {

        PagingVO pagingVO=new PagingVO();

        pagingVO.setToPageNo(toPageNo);

        List<Topic> list=topicMapper.selectByTeaId(teacherId,pagingVO.getTopageNo(),pagingVO.getPageSize());

        return list;
    }

    //根据教师ID和课题名字进行模糊查询
    public List<Topic> findByTeacherIDAndTopicName(Integer techerId, String findByName,Integer toPageNo) throws Exception {

        PagingVO pagingVO=new PagingVO();

        pagingVO.setToPageNo(toPageNo);

        List<Topic> list=topicMapper.selectByTeaIdAndName( techerId,  findByName,pagingVO.getTopageNo(),pagingVO.getPageSize());

        return list;
    }


    public int getCountTopicByTea(Integer teacherId) throws Exception {
        Integer count=0;

        count=topicMapper.countByTeaId(teacherId);

        return count;
    }

    public int getCountTopicByTeaAndName(Integer teacherId, String name) throws Exception {
        Integer count=0;

        count=topicMapper.countByTeaIdAndName(teacherId,name);

        return count;
    }

    public Topic findByTopicId(Integer topicId) throws Exception {
        return  topicMapper.selectByTopicId(topicId);
    }

    public void update(Topic topic) throws Exception {
//        topicMapper.updateTopic(topic.getTeaId(),
//                topic.getStuId(),
//                topic.getTopicType(),
//                topic.getTopicTitle(),
//                topic.getTopicMajor(),
//                topic.getTopicContent(),
//                topic.getTopicRequest(),
//                topic.getTopicId());
        topicMapper.updateTopicInfo(topic);
        System.out.println("update方法结束");
    }

    public Boolean save(Topic topic) throws Exception {
        Topic topic1=topicMapper.selectByPrimaryKey(topic.getTopicId());

        if(topic1==null){
            topicMapper.insert(topic.getTopicId(),topic.getTeaId(),
                    topic.getTopicType(),topic.getTopicTitle(),collegeMapper.selectNameById(Integer.parseInt(topic.getTopicMajor())),
                    topic.getTopicContent(),topic.getTopicRequest());
            return true;
        }
        return false;
    }

    public void updateForStu(Topic topic) throws Exception {

        topicMapper.updateTopic(topic);

        System.out.println("更新topic并添加student信息成功");
    }

    public void delete(Integer topicId) throws Exception {
        topicMapper.deleteByPrimaryKey(topicId);
    }

    public List<Integer> findByStuNullnot() throws Exception {
        return topicMapper.selectByStuNull();
    }

    public List<Topic> findByStuNull() throws Exception {
        return topicMapper.findByStuNull();
    }

    public void updateStu(Topic topic) throws Exception {
        topicMapper.updateTopic(topic);
    }
}
