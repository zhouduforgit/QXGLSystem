package com.duyi.qxgl.action;

import com.duyi.qxgl.domain.Fn;
import com.duyi.qxgl.domain.Page;
import com.duyi.qxgl.domain.Role;
import com.duyi.qxgl.domain.User;
import com.duyi.qxgl.service.RoleService;
import com.duyi.qxgl.service.UserService;
import mymvc.ModelAndView;
import mymvc.RequestParam;
import mymvc.ResponseBody;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用来接收与用户相关的请求
 */
public class UserAction {

    private static final Integer PAGE_ROW = 10 ; //每页显示的记录数
    private UserService service = UserService.getService();
    private RoleService roleService = RoleService.getService();

    /**
     * 用来接收与用户相关的登录请求
     */
    public String login(@RequestParam("uname") String uname , @RequestParam("upass") String upass, HttpServletRequest request){
        //UserService service = new UserService();
        UserService service = UserService.getService();
        User user = service.checkLogin(uname,upass);
        if(user == null){
            //登录失败
            return "index.jsp?status=9" ;
        }else{
            //登录成功,显示主页面，还需要在主页面上显示登录成功的用户信息。
            //如何使得在登录成功时查询出来的用户信息，可以在主页面或其他页面使用呢。
            //需要将此次请求获得的user对象装入session
            HttpSession session = request.getSession() ;
            session.setAttribute("loginUser",user);

            //还需要额外的信息： 登录的这个user用户所具有的权限菜单
            List<Fn> menus = service.findMenuFnByUser(user.getUid());

            List<Fn> buttonList = service.findButtonFnByUser(user.getUid());
            Map<String,Fn> buttons = new HashMap<String,Fn>();
            for(Fn button : buttonList){
                buttons.put(button.getFname(),button);
            }

            session.setAttribute("menus",menus);
            session.setAttribute("buttons",buttons);

            return "main.jsp" ;
        }

    }

    private List<Fn> loadFn(List<Fn> source,Integer pid){
        List<Fn> target = new ArrayList<Fn>();
        for(Fn fn : source){
            if(fn.getPid().equals(pid)){
                //找到了一个符合条件的功能
                target.add(fn) ;
                //但这个fn信息不全，只有自身的信息，缺少子信息
                //还需要找fn的子信息，pid=fn.fid的菜单就是fn的子信息
                //所以需要一fid作为pid条件，在source中寻找对应的数据
                List<Fn> children = loadFn(source,fn.getFid()) ;//找到当前fn的子菜单，其子菜单的pid=fn.fid
                fn.setChildren(children);
            }
        }
        return target ;
    }

    /**
     * 注销，退出
     * 清空session中的数据
     * 重新展示(访问)登录页面
     */
    public String exit(HttpServletRequest request){
        request.getSession().invalidate();//清空session数据
        //转发或重定向访问登录页面
        return "/" ;
    }

    /**
     * 分页查询数据
     * @param page 此次分页查询时的页码
     *             每页显示的数据也可以动态传递，也可以认为规定(默认)
     *  啊拓框架中使用 request.getParemeter("page"); 帮我们获得参数，阿拓老师怎么知道要接收哪个参数呢
     *   使用@RequestParam告诉阿拓老师帮我们获得指定名字的参数
     */
    public ModelAndView list(@RequestParam("page") Integer page){
        //freemarker , thymeleaf 动态网页模板，类似于jsp，比jsp更优秀。
        UserService service = UserService.getService() ;
        Page p = service.find(page,PAGE_ROW) ;
        //查询完毕，需要访问展示jsp页面。同时传递page数据
        //怎么实现 又可以访问页面，又可以传递数据 ： 转发。 。 mvc框架如何实现转发 ，ModelAndView
        ModelAndView mv = new ModelAndView();//又可以指定转发的网页，又可以指定转发携带的数据
        mv.setViewName("user.jsp");
        mv.addAttribute("page",p);
        return mv ;
    }

    public void add(User user,HttpServletResponse resp) throws IOException {
        //这就是保存之前，这就可以实现登录认证，一定需要使用filter
        //保存操作
        UserService service = UserService.getService() ;
        service.save(user);

        //不考虑特殊情况，应该保存成功。
        //页面实现一个保存成功的提示，响应一句话提示语 (直接响应)
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("保存成功");
    }

    public String delete(@RequestParam("uid") Integer uid) {
        //UserService service = new UserService();
        UserService service = UserService.getService();
        service.delete(uid);
        //不考虑特殊情况，删除成功
        //此次操作完成，不想直接返回成功提示，想直接重新显示列表，列表中没有数据了就算是删除成功了。
        //想显示数据列表，数据列表是由list方法处理的
        return "redirect:UserAction.do?method=list" ;
    }

    public ModelAndView edit(@RequestParam("uid") Integer uid){
        //所谓的编辑请求，就是根据uid，查找要编辑的原始数据。
        User user = service.edit(uid);

        //获得了编辑用户时的原始数据。
        //需要将这个原始数据带到页面去拼装：转发 ， mvc框架如何实现转发
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userEdit.jsp");
        mv.addAttribute("user",user);

        return mv ;
    }

    public String update(User user){
        //修改
        service.update(user);
        return "redirect:UserAction.do?method=list" ;
    }

    @ResponseBody
    public String saves(HttpServletRequest req) throws FileUploadException, IOException {
        //获得上传的excel文件
        DiskFileItemFactory factory = new DiskFileItemFactory() ; //工厂负责将上传到信息重新组装FileItem对象
        ServletFileUpload upload = new ServletFileUpload(factory) ; //工具，获得上传的信息
        List<FileItem> fis = upload.parseRequest(req); //upload工具会使用内部的factory处理request携带的数据
        /*
        原来请求传递的参数都在request中
        现在请求传递的参数都在fis集合中
        再需要获取参数就去集合中获取 (可以循环获取)
        但本次的操作比较有特点，就传递了1个文件参数。
         */
        FileItem fi = fis.get(0) ;
        InputStream is = fi.getInputStream() ;
        //就获得了上传文件的输入流，可以通过输入流获取文件内容。

        //根据is输入流执行的excel文件，读取，处理，创建出一个jvm版本的excel
        //File -> 文件
        //Workbook -> excel
        Workbook book = WorkbookFactory.create(is) ;
        //excel文件中获取数据表
        Sheet sheet = book.getSheetAt(0) ;
        //数据表中获取数据行 (循环)
        //从下标为1的第二行开始获取，获取到最后一行。
        for(int i=1;i<=sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i) ;
            //数据行中获取单元
            Cell c1 = row.getCell(0);
            Cell c2 = row.getCell(1);
            Cell c3 = row.getCell(2);
            Cell c4 = row.getCell(3);
            Cell c5 = row.getCell(4);

            /*
            获取单元数据时注意：
            可以使用cell.toString获得单元中的数据值
            如果单元中的数据是数字形式，获得的字符串是浮点形式的
             */
            String uname = c1.toString() ;
            String upass = c2.toString().replace(".0","") ;// "123.0"
            String truename = c3.toString() ;
            String sex = c4.toString() ;
            Integer age = Integer.parseInt(c5.toString().replace(".0","")) ;  //"18.0"

            User user = new User();
            user.setUname(uname);
            user.setUpass(upass);
            user.setTruename(truename);
            user.setSex(sex);
            user.setAge(age);
            //读取excel中的一行记录，并组成对应的user对象，保存。
            service.save(user);
        }
        //完成了批量保存操作
        return "导入成功" ;
        //return "redirect:UserAction.do?method=list" ;
    }


    public void down(HttpServletResponse resp) throws IOException {
        //读取要下载的文件
        //简单的理解成读取src目录下的文件
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("users.xlsx");
        //根据is输入流执行的文件，读取文件内容，装入byte[]
        byte[] bs = IOUtils.toByteArray(is);

        //将文件内容bs响应给浏览器
        //response对象可以实现响应
        resp.setHeader("content-disposition","attachment;filename=users.xlsx");
        resp.getOutputStream().write(bs);
        resp.getOutputStream().flush();
    }


    public ModelAndView toSetRole(@RequestParam("uid") Integer uid,@RequestParam("truename") String truename){
        //查询获得所有的角色列表
        List<Role> roles = roleService.list() ;
        //还需要额外查询获得uid这个用户上一次分配的角色信息
        List<Integer> rids = service.findRidsByUser(uid) ;
        ModelAndView mv = new ModelAndView();
        mv.setViewName("setRole.jsp");
        mv.addAttribute("roles",roles);
        mv.addAttribute("rids",rids);
        //mv.addAttribute("uid",uid);
        //mv.addAttribute("truename",truename);
        //AOP
        return mv ;
    }

    @ResponseBody
    public String setRoles(@RequestParam("uid") Integer uid, HttpServletRequest request){
        String[] ridArray = request.getParameterValues("rid") ;
        service.setRoles(uid,ridArray);
        return "角色分配成功" ;
    }
}
