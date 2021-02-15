package com.duyi.qxgl.domain;


import java.io.Serializable;
import java.util.List;

public class Fn implements Serializable {
    //基本属性，存储当前功能自己的信息
    private Integer fid ;   // 主键2
    private String fname ;  // 用户管理
    private String fhref ;  // UserAction.do?method=list&page=1
    private Integer flag ;  // 1 菜单， 2 按钮
    private Integer pid ;   // 父菜单主键1
    private String yl1 ;
    private String yl2 ;

    //面向具体编程ArrayList list ,LinkedList list;  面向接口编程List list ;
    private List<Fn> children ; //关系属性，表示该属性对应的数据属于当前Fn的子功能信息

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFhref() {
        return fhref;
    }

    public void setFhref(String fhref) {
        this.fhref = fhref;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getYl1() {
        return yl1;
    }

    public void setYl1(String yl1) {
        this.yl1 = yl1;
    }

    public String getYl2() {
        return yl2;
    }

    public void setYl2(String yl2) {
        this.yl2 = yl2;
    }

    public List<Fn> getChildren() {
        return children;
    }

    public void setChildren(List<Fn> children) {
        this.children = children;
    }

    public Fn() {
    }

    public Fn(Integer fid, String fname, String fhref, Integer flag, Integer pid, String yl1, String yl2, List<Fn> children) {
        this.fid = fid;
        this.fname = fname;
        this.fhref = fhref;
        this.flag = flag;
        this.pid = pid;
        this.yl1 = yl1;
        this.yl2 = yl2;
        this.children = children;
    }
}
