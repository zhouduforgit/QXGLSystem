package com.duyi.qxgl.service;

import com.duyi.qxgl.comm.SqlFactoryUtil;
import com.duyi.qxgl.dao.FnDao;
import com.duyi.qxgl.domain.Fn;

import java.util.ArrayList;
import java.util.List;

public class FnService {

    private FnService(){}
    private static FnService service = new FnService();
    public static FnService getService(){
        return service ;
    }

    // FnDao dao = new FnDao();
    private FnDao dao = SqlFactoryUtil.getSession().createDaoImpl(FnDao.class) ;

    public List<Fn> findAll(){
        List<Fn> list = dao.findAll() ;
        //首先从数据库中查询出来的功能信息，每条是独立存在的。
        //但是按照功能管理分析，这些数据之间是存在子父关系
        //解析需要将没有关系摆放的功能信息，按照子父关系重新摆放(重置数据结构)
        /*
            分析一波
            首先需要在所有的菜单中找根菜单 , 也就是找pid=-1的菜单
            然后再从所有的菜单中找当前根菜单(fid)的子菜单, 也就是找pid=根菜单fid的菜单
            进一步分析就是
            根据pid找第一波菜单(根菜单)
            再根据另一个pid找第二波菜单(子菜单)
            找第二波菜单和找第一波菜单是一样的事。
            最终分析：
                使用递归实现。(方法自身调用)
            注意：不能使用循环
                * 循环的每次操作都是独立的，并且第一次完事，才循环第二次。
                * 但这个菜单组装的操作是第一次循环只找到了父菜单的基本信息，父菜单并没有完事，还需要找子菜单
                * 只有子菜单找完了，父级菜单才算组装完事。
         */
        List<Fn> newFn = loadFn(list,-1) ;
        return newFn ;
    }
    /*
        重新组装fn
        传入一组原生数据(零散，未组装)
        传入一个查找条件
        返回一组新数据(组装后)
     */
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

    public void save(Fn fn){
        dao.save(fn);
    }
}
