package com.system.controller;

import com.system.exception.CustomException;
import com.system.po.*;
import com.system.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SessionAttributes("currentStatus")
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Resource(name = "topicServiceImpl")
    private TopicService topicService;

    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;

    @Resource(name = "selectedTopicServiceImpl")
    private SelectedTopicService selectedTopicService;

    @Resource(name="teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name="scoreServiceImpl")
    private ScoreService scoreService;

    @Resource(name="statusServiceImpl")
    private StatusService statusService;

    //表示当前状态量
    private Status statusSingleTon;

    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model, Integer page) throws Exception {

        List<CourseCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(courseService.getCountCouse());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = courseService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = courseService.findByPaging(page);
        }

        model.addAttribute("courseList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "student/showCourse";
    }

    //显示课题信息
    @RequestMapping(value = "/showTopic")
    public String showTopic(Model model, Integer page) throws Exception{

        //表示当前状态量
        statusSingleTon= statusService.find();

        List<Topic> list = null;

        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(topicService.getCountTopic());

        if (page == null || page == 0) {

            pagingVO.setToPageNo(1);

            list = topicService.findByPaging(1);
        } else {

            pagingVO.setToPageNo(page);

            list = topicService.findByPaging(page);
        }

        List<TopicAndStuTeaInfo> listAll=new ArrayList<TopicAndStuTeaInfo>();

        for (Topic t :list) {
            TopicAndStuTeaInfo tt=new TopicAndStuTeaInfo();

            org.springframework.beans.BeanUtils.copyProperties(t, tt);

            tt.setTeacherName(teacherService.findById(t.getTeaId()).getUsername());

            listAll.add(tt);
        }

        //当前request存放map键值对
        model.addAttribute("topicList", listAll);

        model.addAttribute("pagingVO", pagingVO);

        model.addAttribute("currentStatus",statusService.find());

        return "student/showTopic";
    }

    /*
    选题操作 没有问题
     */
    @RequestMapping(value = "/stuSelectedTopic")
    public String stuSelectedTopic(int id) throws Exception {
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        //获取到
        SelectedTopicCustom selectedTopicCustom=new SelectedTopicCustom();
        //设置常量
        selectedTopicCustom.setTopicid(id);
        selectedTopicCustom.setStudentid(Integer.parseInt(username));
        selectedTopicCustom.setDatetime(new Date());

        System.out.println("输出当前用户信息");
        System.out.println("id"+id);
        System.out.println("nameID"+selectedTopicCustom.getStudentid());
        System.out.println("date"+selectedTopicCustom.getDatetime());

        SelectedTopic selectedTopic=new SelectedTopic();
        selectedTopic.setTopicid(id);
        selectedTopic.setStudentid(Integer.parseInt(username));
        //添加对应的业务逻辑
        SelectedTopicCustom s=selectedTopicService.findOne(selectedTopic);

        System.out.println("执行到SelectedTopicCustom");

        if(s==null){     //如果没有记录，则插入
            //
            selectedTopicService.save(selectedTopicCustom);
        }else {
            throw new CustomException("该课题你已经选了，不能再选");
        }

        //重定向到学生已经选题记录模块
        return "redirect:/student/selectedTopic";
    }


    //学生查看初次选题记录
    @RequestMapping(value = "/selectedTopic")
    public String selectedTopic(Model model) throws Exception{
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();

        List<SelectedTopicCustom> list =null;

       StudentCustom studentCustom = studentService.findStudentAndSelectTopicListByName((String) subject.getPrincipal());

       if(studentCustom!=null){
           //这里由于是初选，所以一个学生可以对应多条选择记录也可能没有记录
           System.out.println("List不为空");

           list= studentCustom.getSelectedTopicList();

           for (SelectedTopicCustom s:list) {
               s.setTeacherName(teacherService.findById(s.getTopicCustom().getTeaId()).getUsername());
           }
       }

//        for (SelectedTopicCustom t:list) {
//           System.out.println(t);
//        }

        model.addAttribute("selectedTopicList", list);

        return "student/selectTopic";
    }

    //学生退选课题,删除操作
    @RequestMapping(value = "/outTopic")
    public String outTopic(int id) throws Exception{
        //获取当前登录的用户
        Subject subject = SecurityUtils.getSubject();

        String username = (String) subject.getPrincipal();

        SelectedTopic selectedTopic = new SelectedTopic();
        //获取课题号
        selectedTopic.setTopicid(id);
        //获取学号
        selectedTopic.setStudentid(Integer.parseInt(username));
        //从记录中删除
        selectedTopicService.remove(selectedTopic);

        return "redirect:/student/selectedTopic";
    }

    //学生查看毕设成绩
    @RequestMapping(value = "/overTopic")
    public String overTopic(Model model) throws Exception{
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        //获取当前用户ID
        String username = (String) subject.getPrincipal();

        int userID=Integer.parseInt(username);
        //根据学号ID获取topic表里的对应记录
        Topic topic=topicService.findByStuID(userID);


        List<TopicAndStuTeaInfo> list=null;

        if(topic!=null){
            list=new ArrayList<TopicAndStuTeaInfo>();

            TopicAndStuTeaInfo t=new TopicAndStuTeaInfo();

            org.springframework.beans.BeanUtils.copyProperties(topic, t);

            t.setTeacherName(teacherService.findById(topic.getTeaId()).getUsername());

            list.add(t);
        }

        Integer score=scoreService.findScoreById(userID);

        System.out.println("执行到这里topicService");

        model.addAttribute("selectedTopicList", list);

        model.addAttribute("score",score);

        return "student/overTopic";
    }

    // 选课操作
    @RequestMapping(value = "/stuSelectedCourse")
    public String stuSelectedCourse(int id) throws Exception {
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(id);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));

        SelectedCourseCustom s = selectedCourseService.findOne(selectedCourseCustom);

        if (s == null) {
            selectedCourseService.save(selectedCourseCustom);
        } else {
            throw new CustomException("该门课程你已经选了，不能再选");
        }

        return "redirect:/student/selectedCourse";
    }

    // 退课操作
    @RequestMapping(value = "/outCourse")
    public String outCourse(int id) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(id);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));

        selectedCourseService.remove(selectedCourseCustom);

        return "redirect:/student/selectedCourse";
    }

    // 已选课程
    @RequestMapping(value = "/selectedCourse")
    public String selectedCourse(Model model) throws Exception {
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());

        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();

        model.addAttribute("selectedCourseList", list);

        return "student/selectCourse";
    }

    // 已修课程
    @RequestMapping(value = "/overCourse")
    public String overCourse(Model model) throws Exception {

        System.out.println("进入overCourse");
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());

        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();

        model.addAttribute("selectedCourseList", list);

        return "student/overCourse";
    }

    //搜索课题-->返回选题
    @RequestMapping(value = "selectTopic",method = {RequestMethod.POST})
    public String selectToipc(String findByName, Model model) throws Exception {
        System.out.println("进入selectTopic阶段");
        //业务逻辑层添加相应的操作
        List<Topic> list=topicService.findByName(findByName);

        model.addAttribute("topicList",list);

        //获取当前登录用户的信息

        //重定向当前资源到show界面
        return "student/showNameTopic";
    }

    //修改密码
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "student/passwordRest";
    }

}
