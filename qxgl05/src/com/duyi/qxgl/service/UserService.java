package com.duyi.qxgl.service;

import com.duyi.qxgl.comm.SqlFactoryUtil;
import com.duyi.qxgl.dao.FnDao;
import com.duyi.qxgl.dao.UserDao;
import com.duyi.qxgl.domain.Fn;
import com.duyi.qxgl.domain.Page;
import com.duyi.qxgl.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserService(){}
    private static UserService service = new UserService() ; //饿汉式
    public static UserService getService(){
        return service ;
    }

    private UserDao dao = UserDao.getDao() ;
    private FnDao fnDao = SqlFactoryUtil.getSession().createDaoImpl(FnDao.class);

    /**
     * 登录验证
     * return 验证通过的user对象 或 验证失败null
     */
    public User checkLogin(String uname , String upass){
        //需要与数据库交互，根据uname和upass查询，查到数据通过，未查到数据失败
        //dao负责与数据库交互
        //UserDao dao = new UserDao();
        UserDao dao = UserDao.getDao();
        return dao.findByNameAndPass(uname,upass) ;
    }

    /**
     *
     * @param page 页码
     * @param rows 记录数
     */
    public Page find(Integer page , Integer rows){
        //如果没有指定页码，默认查询第1页
        if(page == null || page < 1){
            page = 1 ;
        }

        //判断是否超出上限(最大页)，最大页需要计算出来。
        //需要通过dao查询记录总数
        UserDao dao = UserDao.getDao() ;
        long total = dao.total() ;
        int max = (int) Math.ceil(1.0*total/rows); //向上取整
        if(page > max){
            page = max ;
        }

        //此时的page一定时有效的。
        //查询page页的rows条记录。   查询第2页的10条记录
        //思考mysql如何实现查询第2页的10条记录呢
        /*
            mysql使用limit start , rows语句实现分页查找 (分批次查找)
            第1页    从0开始     查询10条记录
            第2页    从10开始    查询10条记录
            第3页    从20开始    查询10条记录
            ...
            第n页    从(page-1)*rows开始     查询rows条记录。  //通项公式
         */
        int start = (page-1)*rows ;
        int length = rows ;
        List<User> users = dao.find(start,length);

        //完成了分页查找，需要返回数据 usres,max,page
        return new Page(users,max,page);
    }

    public void save(User user){
        UserDao dao = UserDao.getDao() ;
        dao.save(user);
    }

    public void delete(Integer uid){
        UserDao dao = UserDao.getDao() ;
        dao.delete(uid);
    }

    public User edit(Integer uid){
        return dao.findById(uid);
    }

    public void update(User user){
        dao.update(user);
    }

    public void setRoles(Integer uid , String[] ridArray){
        //保存新的关系之前，先删除原来的关系
        dao.deleteRelationship(uid);

        //uid = 1
        //ridArray = {1,2,3}
        //最终需要在数据库中存储3个数据  1,1 ； 1,2 ； 1,3
        for(String rid : ridArray){
            //uid,rid
            dao.saveRelationship(uid,Integer.parseInt(rid));
        }
    }

    /**
     * 查询获得某一个用户上一次分配的角色编号
     * @param uid
     * @return
     */
    public List<Integer> findRidsByUser(Integer uid){
        return dao.findRidsByUser(uid);
    }

    //查找用户权限分配的菜单功能
    public List<Fn> findMenuFnByUser(Integer uid){
        //每个菜单信息独立
        List<Fn> fns =  fnDao.findMenuFnByUser(uid);
        //需要按照子父关系重新组装
        return loadFn(fns,-1);
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

    //查找用户权限分配的菜单功能
    public List<Fn> findButtonFnByUser(Integer uid){
        return fnDao.findButtonFnByUser(uid);
    }
}
