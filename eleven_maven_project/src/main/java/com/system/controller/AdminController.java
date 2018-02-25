package com.system.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.system.exception.CustomException;
import com.system.po.*;
import com.system.service.*;
import jdk.net.SocketFlow;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/admin")
public class AdminController {
    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "collegeServiceImpl")
    private CollegeService collegeService;

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    @Resource(name = "topicServiceImpl")
    private TopicService topicService;

    @Resource(name="scoreServiceImpl")
    private ScoreService scoreService;

    @Resource(name="statusServiceImpl")
    private StatusService statusService;

    //表示当前状态量
    private Status statusSingleTon;

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<课题操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    @RequestMapping("/showTopic")
    public String showTopic(Model model, Integer page) throws Exception{

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

        //当前request存放map键值对
        model.addAttribute("topicList", list);

        model.addAttribute("pagingVO", pagingVO);

        return "admin/showTopic";
    }

    /*
    显示topic表中stu字段不为空的集合，并取出stu和tea信息
     */
    @RequestMapping("showSelect")
    public String showSelect(Model model, Integer page) throws Exception{
        List<Topic> list = null;

        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(topicService.getCountByStuNotNull());

        if (page == null || page == 0) {

            pagingVO.setToPageNo(1);

            list = topicService.findByPagingStuNoNull(1);
        } else {

            pagingVO.setToPageNo(page);

            list = topicService.findByPagingStuNoNull(page);
        }

        List<TopicForSelect> list2=new ArrayList<TopicForSelect>();

        //如果List有值的话，使用topic对象内的stuid和teaid查询学生姓名和教师姓名
        if(!list.equals(null)){
            System.out.println("list isn't null!");

            for (Topic t:list) {
                TopicForSelect topicForSelect=new TopicForSelect();
                //类拷贝
                BeanUtils.copyProperties(t, topicForSelect);

                String stuName=studentService.findById(t.getStuId()).getUsername();

                String teaName=teacherService.findById(t.getTeaId()).getUsername();

                topicForSelect.setStuName(stuName);

                topicForSelect.setTeaName(teaName);

                topicForSelect.setScore( scoreService.findScoreById(t.getStuId()));

                list2.add(topicForSelect);
            }
        }

        System.out.println("list2 size"+list2.size());
        //当前request存放map键值对
        model.addAttribute("topicStuNotNullList", list2);

        model.addAttribute("pagingVO", pagingVO);

        return "admin/showSelect";
    }

    //管理员要更新课题
    @RequestMapping(value = "/updateTopic",method = {RequestMethod.GET})
    public String updateTopic(Model model,int id)throws Exception{
        Topic topic=topicService.findByTopicId(id);

        //获取课题所属老师信息
        Teacher teacher=teacherService.findById(topic.getTeaId());

        List<College> collegeList = collegeService.finAll();

        model.addAttribute("topicInfo",topic);

        model.addAttribute("collegeList", collegeList);

        model.addAttribute("teachaerId",teacher.getUserid());

        return "admin/updateTopic";
    }

    //管理员更新题目
    @RequestMapping(value = "/updateTopic",method = {RequestMethod.POST})
    public String updateTopicInfo(Model model,Topic topic)throws Exception{
        topic.setTopicMajor(collegeService.findById(Integer.parseInt(topic.getTopicMajor())));
        topicService.update(topic);
        return "redirect:/admin/showTopic";
    }

    //管理员删除课题
    @RequestMapping(value = "/deleteTopic")
    public String deleteTopic(Model model,Integer id)throws Exception{

        topicService.delete(id);

        System.out.println("delete方法结束");

        return "redirect:/admin/showTopic";
    }

    //管理员修改当前阶段
    @RequestMapping(value = "/modifyStatus")
    public String modifyStatus(Model model)throws Exception{

        System.out.println("进入modify");

        model.addAttribute("status",statusSingleTon);

        List<Status> statusList=StatusSingleTon.getStatusSingleTon().getList();

        model.addAttribute("statusList",statusList);

        return "admin/modifyStatus";
    }

    @RequestMapping(value = "/modifiedStatus",method = {RequestMethod.POST})
    public String modifiedStatus(Model model,Integer status)throws Exception{

        System.out.println("当前状态"+status);

//        statusSingleTon.getStatusInfo().setStatus(status);
        List<Status> statusList=StatusSingleTon.getStatusSingleTon().getList();

        //更新数据库状态
        statusService.update(statusList.get(status));

//        statusSingleTon.getStatusInfo().setContent(StatusSingleTon.getStatusSingleTon().getList().get(status).getContent());

        System.out.println("修改后的状态"+statusService.find());

        return "redirect:/admin/showStudent";
    }

    //随机进行分配
    @RequestMapping(value = "/secondSelect")
    public String secondSelect(Model model)throws Exception{

        System.out.println("进入secondSelect");
        //找到topic表里stu_id不为空的记录抽取出stu_id
        List<Integer> list=topicService.findByStuNullnot();
        //找到student表里所有的stu_id
        List<Integer> listForStudent=studentService.findAllStuId();
        //两个集合做差,存储的都是学生的学号
        listForStudent.removeAll(list);
        //找到topic表里stu_id为空的记录
        List<Topic> topicForStuNull=topicService.findByStuNull();
        //
        if(listForStudent.size()<=topicForStuNull.size()){
            //把差集字段插入到topic记录中
            for(int i=0;i<listForStudent.size();i++){
                Topic topic=topicForStuNull.get(i);

                topic.setStuId(listForStudent.get(i));

                topicService.updateStu(topic);
            }
        }

        //重定向回选课记录页面
        return "redirect:/admin/showSelect";

    }

    //搜索课题
    @RequestMapping(value = "selectToipc",method = {RequestMethod.POST})
    public String selectTopc(String findByName, Model model) throws Exception {
        //业务逻辑层添加相应的操作
        List<Topic> list=topicService.findByName(findByName);

        model.addAttribute("topicList",list);

        return "admin/showNameTopic";
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<学生操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    //  学生信息显示
    @RequestMapping("/showStudent")
    public String showStudent(Model model, Integer page) throws Exception {

        //表示当前状态量
        statusSingleTon= statusService.find();

        List<StudentCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(studentService.getCountStudent());

        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = studentService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = studentService.findByPaging(page);
        }

        //当前request存放map键值对
        model.addAttribute("studentList", list);

        model.addAttribute("pagingVO", pagingVO);

        model.addAttribute("currentStatus",statusService.find());

        return "admin/showStudent";

    }

    //  添加学生信息页面显示--展示页面信息
    @RequestMapping(value = "/addStudent", method = {RequestMethod.GET})
    public String addStudentUI(Model model) throws Exception {

        List<College> list = collegeService.finAll();
        //系别传参到前端
        model.addAttribute("collegeList", list);

        return "admin/addStudent";
    }

     // 添加学生信息操作
    @RequestMapping(value = "/addStudent", method = {RequestMethod.POST})
    public String addStudent(StudentCustom studentCustom, Model model) throws Exception {

        Boolean result = studentService.save(studentCustom);

        if (!result) {
            model.addAttribute("message", "学号重复");
            return "error";
        }
        //添加成功后，也添加到登录表

        Userlogin userlogin = new Userlogin();

        userlogin.setUsername(studentCustom.getUserid().toString());
        //默认密码
        userlogin.setPassword("123");

        userlogin.setRole(2);

        userloginService.save(userlogin);

        //重定向 再次刷新首页
        return "redirect:/admin/showStudent";
    }

    // 修改学生信息页面显示
    @RequestMapping(value = "/editStudent", method = {RequestMethod.GET})
    public String editStudentUI(Integer id, Model model) throws Exception {
        if (id == null) {
            //假如没有带学生id就进来的话就返回学生显示页面
            return "redirect:/admin/showStudent";
        }
        StudentCustom studentCustom = studentService.findById(id);
        if (studentCustom == null) {
            throw new CustomException("未找到该名学生");
        }
        //遍历student表并显示
        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);
        //向前端传递'student'
        model.addAttribute("student", studentCustom);

        return "admin/editStudent";
    }

    // form表单提交 修改学生信息处理
    @RequestMapping(value = "/editStudent", method = {RequestMethod.POST})
    public String editStudent(StudentCustom studentCustom) throws Exception {

        studentService.updataById(studentCustom.getUserid(), studentCustom);

        //重定向 controller之间跳转
        return "redirect:/admin/showStudent";
    }

    // 删除学生
    @RequestMapping(value = "/removeStudent", method = {RequestMethod.GET} )
    private String removeStudent(Integer id) throws Exception {
        if (id == null) {
            //加入没有带学生id就进来的话就返回学生显示页面
            return "admin/showStudent";
        }
        studentService.removeById(id);
        userloginService.removeByName(id.toString());

        return "redirect:/admin/showStudent";
    }

    // 搜索学生
    @RequestMapping(value = "selectStudent", method = {RequestMethod.POST})
    private String selectStudent(String findByName, Model model) throws Exception {

        List<StudentCustom> list = studentService.findByName(findByName);

        model.addAttribute("studentList", list);
        return "admin/showStudent";
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<教师操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    // 教师页面显示
    @RequestMapping("/showTeacher")
    public String showTeacher(Model model, Integer page) throws Exception {

        List<TeacherCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(teacherService.getCountTeacher());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = teacherService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = teacherService.findByPaging(page);
        }

        model.addAttribute("teacherList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showTeacher";

    }

    // 添加教师信息
    @RequestMapping(value = "/addTeacher", method = {RequestMethod.GET})
    public String addTeacherUI(Model model) throws Exception {

        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);

        return "admin/addTeacher";
    }

    // 添加教师信息处理
    @RequestMapping(value = "/addTeacher", method = {RequestMethod.POST})
    public String addTeacher(TeacherCustom teacherCustom, Model model) throws Exception {

        Boolean result = teacherService.save(teacherCustom);

        if (!result) {
            model.addAttribute("message", "工号重复");
            return "error";
        }
        //添加成功后，也添加到登录表
        Userlogin userlogin = new Userlogin();
        userlogin.setUsername(teacherCustom.getUserid().toString());
        userlogin.setPassword("123");
        userlogin.setRole(1);
        userloginService.save(userlogin);

        //重定向
        return "redirect:/admin/showTeacher";
    }

    // 修改教师信息页面显示
    @RequestMapping(value = "/editTeacher", method = {RequestMethod.GET})
    public String editTeacherUI(Integer id, Model model) throws Exception {
        if (id == null) {
            return "redirect:/admin/showTeacher";
        }
        TeacherCustom teacherCustom = teacherService.findById(id);
        if (teacherCustom == null) {
            throw new CustomException("未找到该名学生");
        }
        List<College> list = collegeService.finAll();

        model.addAttribute("collegeList", list);
        model.addAttribute("teacher", teacherCustom);


        return "admin/editTeacher";
    }

    // 修改教师信息页面处理
    @RequestMapping(value = "/editTeacher", method = {RequestMethod.POST})
    public String editTeacher(TeacherCustom teacherCustom) throws Exception {

        teacherService.updateById(teacherCustom.getUserid(), teacherCustom);

        //重定向
        return "redirect:/admin/showTeacher";
    }

    //删除教师
    @RequestMapping("/removeTeacher")
    public String removeTeacher(Integer id) throws Exception {
        if (id == null) {
            //加入没有带教师id就进来的话就返回教师显示页面
            return "admin/showTeacher";
        }
        teacherService.removeById(id);
        userloginService.removeByName(id.toString());

        return "redirect:/admin/showTeacher";
    }

    //搜索教师
    @RequestMapping(value = "selectTeacher", method = {RequestMethod.POST})
    private String selectTeacher(String findByName, Model model) throws Exception {

        List<TeacherCustom> list = teacherService.findByName(findByName);

        model.addAttribute("teacherList", list);
        return "admin/showTeacher";
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<课程操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

//    // 课程信息显示
//    @RequestMapping("/showCourse")
//    public String showCourse(Model model, Integer page) throws Exception {
//
//        List<CourseCustom> list = null;
//        //页码对象
//        PagingVO pagingVO = new PagingVO();
//        //设置总页数
//        pagingVO.setTotalCount(courseService.getCountCouse());
//        if (page == null || page == 0) {
//            pagingVO.setToPageNo(1);
//            list = courseService.findByPaging(1);
//        } else {
//            pagingVO.setToPageNo(page);
//            list = courseService.findByPaging(page);
//        }
//
//        model.addAttribute("courseList", list);
//        model.addAttribute("pagingVO", pagingVO);
//
//        return "admin/showCourse";
//
//    }
//
//    //添加课程
//    @RequestMapping(value = "/addCourse", method = {RequestMethod.GET})
//    public String addCourseUI(Model model) throws Exception {
//
//        List<TeacherCustom> list = teacherService.findAll();
//        List<College> collegeList = collegeService.finAll();
//
//        model.addAttribute("collegeList", collegeList);
//        model.addAttribute("teacherList", list);
//
//        return "admin/addCourse";
//    }
//
//    // 添加课程信息处理
//    @RequestMapping(value = "/addCourse", method = {RequestMethod.POST})
//    public String addCourse(CourseCustom courseCustom, Model model) throws Exception {
//
//        Boolean result = courseService.save(courseCustom);
//
//        if (!result) {
//            model.addAttribute("message", "课程号重复");
//            return "error";
//        }
//
//        //重定向
//        return "redirect:/admin/showCourse";
//    }
//
//    // 修改教师信息页面显示
//    @RequestMapping(value = "/editCourse", method = {RequestMethod.GET})
//    public String editCourseUI(Integer id, Model model) throws Exception {
//        if (id == null) {
//            return "redirect:/admin/showCourse";
//        }
//        CourseCustom courseCustom = courseService.findById(id);
//        if (courseCustom == null) {
//            throw new CustomException("未找到该课程");
//        }
//        List<TeacherCustom> list = teacherService.findAll();
//        List<College> collegeList = collegeService.finAll();
//
//        model.addAttribute("teacherList", list);
//        model.addAttribute("collegeList", collegeList);
//        model.addAttribute("course", courseCustom);
//
//        return "admin/editCourse";
//    }
//
//    // 修改教师信息页面处理
//    @RequestMapping(value = "/editCourse", method = {RequestMethod.POST})
//    public String editCourse(CourseCustom courseCustom) throws Exception {
//
//        courseService.upadteById(courseCustom.getCourseid(), courseCustom);
//
//        //重定向
//        return "redirect:/admin/showCourse";
//    }
//
//    // 删除课程信息
//    @RequestMapping("/removeCourse")
//    public String removeCourse(Integer id) throws Exception {
//        if (id == null) {
//            //加入没有带教师id就进来的话就返回教师显示页面
//            return "admin/showCourse";
//        }
//        courseService.removeById(id);
//
//        return "redirect:/admin/showCourse";
//    }
//
//    //搜索课程
//    @RequestMapping(value = "selectCourse", method = {RequestMethod.POST})
//    private String selectCourse(String findByName, Model model) throws Exception {
//
//        List<CourseCustom> list = courseService.findByName(findByName);
//
//        model.addAttribute("courseList", list);
//        return "admin/showCourse";
//    }



    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<其他操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    // 普通用户账号密码重置
    @RequestMapping("/userPasswordRest")
    public String userPasswordRestUI() throws Exception {
        return "admin/userPasswordRest";
    }

    // 普通用户账号密码重置处理
    @RequestMapping(value = "/userPasswordRest", method = {RequestMethod.POST})
    public String userPasswordRest(Userlogin userlogin) throws Exception {

        Userlogin u = userloginService.findByName(userlogin.getUsername());

        if (u != null) {
            if (u.getRole() == 0) {
                throw new CustomException("该账户为管理员账户，没法修改");
            }
            u.setPassword(userlogin.getPassword());
            userloginService.updateByName(userlogin.getUsername(), u);
        } else {
            throw new CustomException("没找到该用户");
        }

        return "admin/userPasswordRest";
    }

    // 本账户密码重置
    @RequestMapping("/passwordRest")
    public String passwordRestUI() throws Exception {
        return "admin/passwordRest";
    }

}
