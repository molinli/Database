package com.system.po;

import java.util.ArrayList;
import java.util.List;
import com.system.po.Status;

/**
 * @Author: 莫林立
 * @Date: Created in 2018/1/9
 */
public class StatusSingleTon {

    private static StatusSingleTon statusSingleTon=new StatusSingleTon();

    //管理员修改这个状态，系统共享这个状态
    private Status statusInfo;

    private  List<Status> list;

    private  StatusSingleTon(){

        System.out.println("singleTon构造完毕");
    }

    static {

        List<Status> list1=new ArrayList<Status>();

        Status s1=new Status();
        Status s2=new Status();
        Status s3=new Status();
        Status s4=new Status();
        Status s5=new Status();

        s1.setStatus(0);
        s1.setContent("现在是老师出题阶段，请童鞋们耐心等待~");
        s2.setStatus(1);
        s2.setContent("现在是学生选题阶段，请同学们抓紧选题!");
        s3.setStatus(2);
        s3.setContent("现在是老师定题阶段，同学们请耐心等待~");
        s4.setStatus(3);
        s4.setContent("现在是补选阶段，系统随机分配");
        s5.setStatus(4);
        s5.setContent("现在是老师打分阶段，高分的同学请低分的同学吃饭~");

        list1.add(s1);
        list1.add(s2);
        list1.add(s3);
        list1.add(s4);
        list1.add(s5);

        System.out.println("list创建完毕");

        statusSingleTon.setList(list1);

        System.out.println("list赋值完毕");
        statusSingleTon.setStatusInfo(s1);
        System.out.println("satus赋值完毕");
    }

    public  static StatusSingleTon getStatusSingleTon() {

        return statusSingleTon;
    }

    public  List<Status> getList() {
        return list;
    }

    public void setList(List<Status> list) {
        this.list = list;
    }

    public Status getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(Status statusInfo) {
        this.statusInfo = statusInfo;
    }
}
