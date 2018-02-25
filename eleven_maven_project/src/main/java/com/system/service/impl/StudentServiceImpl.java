package com.system.service.impl;

import com.system.mapper.CollegeMapper;
import com.system.mapper.ScoreMapper;
import com.system.mapper.StudentMapper;
import com.system.mapper.StudentMapperCustom;
import com.system.po.*;
import com.system.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Student
 */
@Service
public class StudentServiceImpl implements StudentService {

    //使用spring 自动注入 bytype注入 创建实例
    @Autowired
    private StudentMapperCustom studentMapperCustom;
    //使用spring 注入的同时并创建该对象，内部的方法实现和mapping.xml方法配对
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    public void updataById(Integer id, StudentCustom studentCustom) throws Exception {
        studentMapper.updateByPrimaryKey(studentCustom);
    }

    public void removeById(Integer id) throws Exception {
        studentMapper.deleteByPrimaryKey(id);
    }

    public List<StudentCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<StudentCustom> list = studentMapperCustom.findByPaging(pagingVO);

        return list;
    }

    public Boolean save(StudentCustom studentCustoms) throws Exception {
        Student stu = studentMapper.selectByPrimaryKey(studentCustoms.getUserid());
        if (stu == null) {
            studentMapper.insert(studentCustoms);
            return true;
        }

        return false;
    }

    //返回学生总数
    public int getCountStudent() throws Exception {
        //自定义查询对象
        StudentExample studentExample = new StudentExample();
        //通过criteria构造查询条件
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andUseridIsNotNull();

        System.out.println(studentMapper.countByExample(studentExample));

        return studentMapper.countByExample(studentExample);
    }

    public StudentCustom findById(Integer id) throws Exception {

        Student student  = studentMapper.selectByPrimaryKey(id);
        StudentCustom studentCustom = null;
        if (student != null) {
            studentCustom = new StudentCustom();
            //类拷贝
            BeanUtils.copyProperties(student, studentCustom);
        }

        return studentCustom;
    }

    //模糊查询
    public List<StudentCustom> findByName(String name) throws Exception {

        StudentExample studentExample = new StudentExample();
        //自定义查询条件
        StudentExample.Criteria criteria = studentExample.createCriteria();

        criteria.andUsernameLike("%" + name + "%");

        List<Student> list = studentMapper.selectByExample(studentExample);

        List<StudentCustom> studentCustomList = null;

        if (list != null) {
            studentCustomList = new ArrayList<StudentCustom>();
            for (Student s : list) {
                StudentCustom studentCustom = new StudentCustom();
                //类拷贝
                BeanUtils.copyProperties(s, studentCustom);
                //获取课程名
                College college = collegeMapper.selectByPrimaryKey(s.getCollegeid());
                studentCustom.setcollegeName(college.getCollegename());

                studentCustomList.add(studentCustom);
            }
        }

        return studentCustomList;
    }


    public StudentCustom findStudentAndSelectCourseListByName(String name) throws Exception {

        StudentCustom studentCustom = studentMapperCustom.findStudentAndSelectCourseListById(Integer.parseInt(name));

        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();

        // 判断该课程是否修完
        for (SelectedCourseCustom s : list) {
            if (s.getMark() != null) {
                s.setOver(true);
            }
        }
        return studentCustom;
    }


    //初选使用
    public StudentCustom findStudentAndSelectTopicListByName(String name) throws Exception {

        StudentCustom studentCustom = studentMapperCustom.findStudentAndSelectTopicListById(Integer.parseInt(name));

        if(studentCustom==null){
            return null;
        }

        List<SelectedTopicCustom> list = studentCustom.getSelectedTopicList();

        // 判断该课题是否修完
        for (SelectedTopicCustom s : list) {
            //查询score表，如果学生的成绩不为空则说明学生完成毕设
            if(s.getStudentid()!=null){
                if(scoreMapper.findByStuId(s.getStudentid())!=null)
                    s.setOver(true);
            }
        }

        return studentCustom;
    }


    public StudentCustom findStudentAndCollegeById(Integer stuId) throws Exception {

         Student student=studentMapper.selectByPrimaryKey(stuId);

         StudentCustom studentCustom=new StudentCustom();

         //拷贝
        BeanUtils.copyProperties(student, studentCustom);

        studentCustom.setcollegeName(collegeMapper.selectNameById(student.getCollegeid()));

        return studentCustom;

    }

    public List<Integer> findAllStuId() throws Exception {
        return studentMapper.findAllStuId();
    }

}
