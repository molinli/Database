package com.system.mapper;

import com.system.po.PagingVO;
import com.system.po.StudentCustom;

import java.util.List;


public interface StudentMapperCustom {

    //分页查询学生信息
    List<StudentCustom> findByPaging(PagingVO pagingVO) throws Exception;

    //查询学生信息，和其选课信息
    StudentCustom findStudentAndSelectCourseListById(Integer id) throws Exception;

    //查询学生信息，和其选题信息
    StudentCustom findStudentAndSelectTopicListById(Integer id) throws Exception;

}
