package com.system.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
import java.util.ArrayList;
import java.util.List;


@SessionAttributes("currentStatus")
@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;

    @Resource(name="topicServiceImpl")
    private TopicService topicService;

    @Resource(name="firChooseServiceImpl")
    private FirChooseService firChooseService;

    @Resource(name="studentServiceImpl")
    private StudentService studentService;

    @Resource(name="scoreServiceImpl")
    private ScoreService scoreService;

    @Resource(name = "collegeServiceImpl")
    private CollegeService collegeService;

    @Resource(name="statusServiceImpl")
    private StatusService statusService;

    //表示当前状态量
    private Status statusSingleTon;

    // 显示我的课程
    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model) throws Exception {
        //获取当前用户信息
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        List<CourseCustom> list = courseService.findByTeacherID(Integer.parseInt(username));
        model.addAttribute("courseList", list);

        return "teacher/showCourse";
    }

    //显示我的课题
    @RequestMapping(value = "/showTopic")
    public String stuTopicShow(Model model, Integer page) throws Exception{
        //表示当前状态量
         statusSingleTon= statusService.find();

        //获取当前用户信息
        Subject subject = SecurityUtils.getSubject();
        //获取用户登录名
        String username = (String) subject.getPrincipal();

        //获取到该老师的topiclist
        List<Topic> list=null;

        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数--这里要修改为当前老师的topic数量
        pagingVO.setTotalCount(topicService.getCountTopicByTea(Integer.parseInt(username)));

        if (page == null || page == 0) {

            pagingVO.setToPageNo(1);
            //设置页数
            list = topicService.findByTeacherID(Integer.parseInt(username),1);
        } else {

            pagingVO.setToPageNo(page);

            list = topicService.findByTeacherID(Integer.parseInt(username),page);
        }

        //当前request存放map键值对
        model.addAttribute("topicList", list);

        model.addAttribute("pagingVO", pagingVO);

        model.addAttribute("currentStatus",statusService.find());


        int ss=statusService.find().getStatus();

        model.addAttribute("currentStatusInfo",ss);

        return "teacher/showTopic";
    }

    //老师要出题 添加课题信息到topic表
    @RequestMapping(value = "/addTopic",method = {RequestMethod.GET})
    public String addTopicUI(Model model)throws Exception{
        //获取当前用户信息
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        List<College> collegeList = collegeService.finAll();

        model.addAttribute("teachaerId",Integer.parseInt(username));

        model.addAttribute("collegeList", collegeList);

        //返回逻辑即视图
        return "teacher/addTopic";
    }

    //
    @RequestMapping(value = "/addTopic" ,method = {RequestMethod.POST})
    public String addTopic(Model model,Topic topic)throws Exception{
        //在此处进行添加，如果topic_id冲突提示报错

        System.out.println(topic);
        Boolean result=topicService.save(topic);
        if (!result){       //保存失败，主键冲突
            model.addAttribute("message", "课题号重复，添加失败！请换一个课题号。");

            return "error";
        }

        //重定向到课题表
        return "redirect:/teacher/showTopic";
    }

    //老师课题的模糊搜索
    @RequestMapping(value = "/selectTopic",method = {RequestMethod.POST})
    public String selectTopic(Model model,String findByName,Integer page)throws Exception{
        //获取当前用户信息
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        List<Topic> list=null;

        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数--这里要修改为当前老师的topic数量并且模糊搜索
        pagingVO.setTotalCount(topicService.getCountTopicByTeaAndName(Integer.parseInt(username),findByName));

        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);

            //获取到该老师的topiclist
            list=topicService.findByTeacherIDAndTopicName(Integer.parseInt(username),findByName,1);
        }else {

            pagingVO.setToPageNo(page);

            list = topicService.findByTeacherIDAndTopicName(Integer.parseInt(username),findByName,page);

        }

        model.addAttribute("topicList", list);

        model.addAttribute("pagingVO", pagingVO);

        int ss=statusService.find().getStatus();

        model.addAttribute("statusInfo",ss);

        return "teacher/showNameTopic";
    }

    //老师要更新课题
    @RequestMapping(value = "/updateTopic",method = {RequestMethod.GET})
    public String updateTopic(Model model,int id)throws Exception{
        Topic topic=topicService.findByTopicId(id);

        //获取当前用户信息
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        List<College> collegeList = collegeService.finAll();

        model.addAttribute("topicInfo",topic);

        model.addAttribute("collegeList", collegeList);

        model.addAttribute("teachaerId",Integer.parseInt(username));

        return "teacher/updateTopic";
    }

    //老师更新题目
    @RequestMapping(value = "/updateTopic",method = {RequestMethod.POST})
    public String updateTopicInfo(Model model,Topic topic)throws Exception{
        topic.setTopicMajor(collegeService.findById(Integer.parseInt(topic.getTopicMajor())));
        topicService.update(topic);
        return "redirect:/teacher/showTopic";
    }

    //老师要定题
    @RequestMapping(value = "/decideTopic")
    public String decideTopic(Model model,int id,Integer page )throws Exception{

        List<StudentCustom> list=null;

        System.out.println("进入decideTopic");

        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数--这里要修改为当前老师的topic数量并且模糊搜索
        pagingVO.setTotalCount(firChooseService.getCountByTopicId(id));

        System.out.println(pagingVO.getTotalCount());

        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);

            //返回的是一个list<studentCunstom>
            List<SelectedTopic> firchoose=firChooseService.findByTopicId(id,1);

            list=new ArrayList<StudentCustom>();

            //对每一个stu_id，获取相应的stu信息和学院信息封装交付前端
            for (SelectedTopic s:firchoose) {

                StudentCustom ss=studentService.findStudentAndCollegeById(s.getStudentid());

                list.add(ss);
            }
        }else {

            pagingVO.setToPageNo(page);

            //返回的是一个list<studentCunstom>
            List<SelectedTopic> firchoose=firChooseService.findByTopicId(id,page);

            list=new ArrayList<StudentCustom>();

            //对每一个stu_id，获取相应的stu信息和学院信息封装交付前端
            for (SelectedTopic s:firchoose) {

                StudentCustom ss=studentService.findStudentAndCollegeById(s.getStudentid());

                list.add(ss);
            }
        }

        model.addAttribute("studentList",list);

        model.addAttribute("pagingVO",pagingVO);

        model.addAttribute("topicID",id);

        return "teacher/decideTopic";
    }

    //老师选择学生
    @RequestMapping(value = "/selectStudent")
    public String selectStudent(Model model,int studentId,int topicId)throws Exception{


        //把学生信息插入到topic表对应的记录中
        Topic topic=topicService.findByTopicId(topicId);

        topic.setStuId(studentId);

        //和修改课题的方法产生了歧义
        topicService.updateForStu(topic);

        System.out.println("update没有错");
        //重定向回显示列表
        return "redirect:/teacher/showTopic";
    }

    //老师要给分
    @RequestMapping(value = "/Score")
    public String giveScore(Model model,int id)throws Exception{

        //取学生信息
        StudentCustom studentCustom=studentService.findById(topicService.findByTopicId(id).getStuId());

        //如果学生成绩不为空
        if(scoreService.findScoreById(studentCustom.getUserid())!=null){
            System.out.println("进入showScore方法");

            Student student=studentService.findById(studentCustom.getUserid());

            Integer findscore=scoreService.findScoreById(student.getUserid());

            model.addAttribute("student",student);

            model.addAttribute("studentScore",findscore);

            return "/teacher/showScore";
        }

        model.addAttribute("studentCustom",studentCustom);

        return "teacher/giveScore";
    }

    //老师给课题打分,且学生成绩肯定为空
    @RequestMapping(value = "/giveScore",method = {RequestMethod.POST})
    public String giveScore(Model model,Integer studentid,Integer mark)throws Exception{
        Score score=new Score();

        score.setScore(mark);
        score.setStuid(studentid);

        //谨防插入失败，主键冲突
        teacherService.insertScore(score);

        return "redirect:/teacher/showTopic";
    }
//
//    // 显示成绩
//    @RequestMapping(value = "/gradeCourse")
//    public String gradeCourse(Integer id, Model model) throws Exception {
//        if (id == null) {
//
//            return "";
//        }
//        List<SelectedCourseCustom> list = selectedCourseService.findByCourseID(id);
//        model.addAttribute("selectedCourseList", list);
//        return "teacher/showGrade";
//    }

//    // 课程打分
//    @RequestMapping(value = "/mark", method = {RequestMethod.GET})
//    public String markUI(SelectedCourseCustom scc, Model model) throws Exception {
//
//        SelectedCourseCustom selectedCourseCustom = selectedCourseService.findOne(scc);
//
//        model.addAttribute("selectedCourse", selectedCourseCustom);
//
//        return "teacher/mark";
//    }

//    // 课程打分
//    @RequestMapping(value = "/mark", method = {RequestMethod.POST})
//    public String mark(SelectedCourseCustom scc) throws Exception {
//
//        selectedCourseService.updataOne(scc);
//
//        return "redirect:/teacher/gradeCourse?id="+scc.getCourseid();
//    }

    //修改密码
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "teacher/passwordRest";
    }

}
