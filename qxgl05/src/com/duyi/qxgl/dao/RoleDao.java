package com.duyi.qxgl.dao;

import com.duyi.qxgl.comm.SqlFactoryUtil;
import com.duyi.qxgl.domain.Role;
import jdbc.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleDao {
    private RoleDao(){}

    private static RoleDao dao = new RoleDao() ;
    public static RoleDao getDao(){
        return dao ;
    }


    public long total(){
        String sql = "select count(*) from t_role where del = 1" ;
        SqlSession session = SqlFactoryUtil.getSession() ;
        return session.selectOne(sql,long.class) ;
    }

    public List<Role> find(int start , int length){
        String sql = "select * from t_role where del = 1  order by createtime limit #{start},#{length}";
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("start",start) ;
        param.put("length",length) ;
        SqlSession session = SqlFactoryUtil.getSession() ;
        return session.selectList(sql,param,Role.class) ;
    }

    public List<Role> findAll(){
        String sql = "select * from t_role where del = 1" ;
        SqlSession session = SqlFactoryUtil.getSession() ;
        return session.selectList(sql,Role.class);
    }

    public void deleteRelationship(Integer rid){
        String sql = "delete from t_role_fn where rid = #{rid}" ;
        SqlSession session = SqlFactoryUtil.getSession();
        session.delete(sql,rid) ;
    }

    public void addRelationship(Integer rid , Integer fid){
        String sql = "insert into t_role_fn values(#{rid},#{fid})";
        SqlSession session = SqlFactoryUtil.getSession();
        Map<String,Integer> params = new HashMap<String,Integer>();
        params.put("rid",rid) ;
        params.put("fid",fid) ;
        session.insert(sql,params);
    }

    public List<Integer> findFidsByRole(Integer rid){
        String sql = "select fid from t_role_fn where rid = #{rid}" ;
        SqlSession session = SqlFactoryUtil.getSession();

        return  session.selectList(sql,rid,Integer.class);
    }
}
