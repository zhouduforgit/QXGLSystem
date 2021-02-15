package com.duyi.qxgl.comm;

import com.duyi.qxgl.domain.Fn;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Map;

//is - a  extends/implements
/**
    <duyi:a href="url" label="新建用户"  authority="用户-新建" />
    authority 属性是用来实现权限查找的。
    href + label ==> <a href="url">新建用户</a>
 */
public class ATag extends TagSupport {

    private String href ;
    private String label ;
    private String authority ;

    public void setHref(String href) {
        this.href = href;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public int doStartTag() throws JspException {

        //当jsp引擎(web服务器)，读取标签时执行的代码
        //希望标签代替的哪部分java代码，就写在这个方法中。
        //权限控制
        //从session获得所有的按钮
        //判断此次按钮是否存在
        //    如何知道此次的按钮是什么呢,通过标签的属性设置 和 标签对象的属性set方法
        //    如何获得session
        //    jsp9个内置对象之一：pageContext 获得其他8个内置对象。
        Map<String, Fn> buttons = (Map<String, Fn>) super.pageContext.getSession().getAttribute("buttons");
        Fn button = buttons.get(authority);
        if(button != null){
            //有这个按钮权限
            //显示按钮标签，将java中的数据输出在网页上，使用out对象
            JspWriter out = super.pageContext.getOut();
            try {
                out.write("<a href='"+href+"' >"+label+"</a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.doStartTag();//暂时认为固定。
    }
}
