package com.duyi.qxgl.dao;

import com.duyi.qxgl.comm.SqlFactoryUtil;
import com.duyi.qxgl.domain.User;
import jdbc.JdbcFactory;
import jdbc.JdbcUtil;
import jdbc.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {
    public static final String s = new String("dmc");
    private UserDao(){}

    private static volatile UserDao dao ;

    //懒汉式
    public static UserDao getDao(){
        //假设10个线程就这么巧，同时访问执行了20行判断，都发现dao为null，都去创建对象(多个)
        if(dao == null){ //判断是否需要等待
            //所以发现dao为null准备创建时，需要上锁,保证每次只能有一个线程执行(创建)
            synchronized (s){
                //线程获得锁的之后，准备创建对象时，还需要再判断一次，看看之前是否页也有创建过
                if(dao == null){//判断是否需要创建。
                    dao =new UserDao();//假设 第1个线程再创建dao对象的同时，第11个线程又来了
                }
            }
        }
        return dao ;
    }
    public User findByNameAndPass(String uname, String upass){
        //根据uname和upass 从数据库查找对应的数据
        //要想查询数据，需要提供select语句
        String sql = "select * from t_user where uname = #{uname} and upass = #{upass}" ;
        //需要使用jdbc，将sql传递给数据库执行
        //原来自己写jdbc
        //现在使用封装的jdbc框架(orm)实现jdbc过程

        //JdbcFactory factory = new JdbcFactory("mysql.properties");
        //SqlSession session = factoryactory.getSession() ;
        SqlSession session = SqlFactoryUtil.getSession();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("uname",uname);
        param.put("upass",upass);
        //sql执行的语句
        //param sql语句中#{key}对应的参数名
        //User.class 查询语句的查询结果组装对象类型，查询结果组成User类型的对象。
        return session.selectOne(sql,param,User.class);
    }

    public long total(){
        String sql = "select count(*) from t_user where del = 1";
        //JdbcFactory factory = new JdbcFactory("mysql.properties") ;
        SqlSession session = SqlFactoryUtil.getSession();
        return session.selectOne(sql,Long.class);
    }

    public List<User> find(int start , int length){
        String sql = "select * from t_user where del = 1 limit #{start},#{length}" ;
        //JdbcFactory factory = new JdbcFactory("mysql.properties") ;
        //SqlSession session = factory.getSession();
        SqlSession session = SqlFactoryUtil.getSession();
        Map<String,Integer> params = new HashMap<String,Integer>();
        params.put("start",start);
        params.put("length",length) ;
        return session.selectList(sql,params,User.class);
    }

    public void save(User user){
        System.out.println(user.getSex());
        String sql = "insert into t_user values(null,#{uname},#{upass},#{truename},#{sex},#{age},1,now(),#{yl1},#{yl2})" ;
        //原来需要自己写jdbc
        //现在使用orm框架实现jdbc
        SqlSession session = SqlFactoryUtil.getSession() ;//确保单实例工厂产生session
        session.insert(sql,user) ;
    }

    public void delete(Integer uid){
        //我们的数据库设计使用的是加删除
        String sql = "update t_user set del = 2 where uid = #{uid}" ;
        SqlSession session = SqlFactoryUtil.getSession();
        session.update(sql,uid) ;
    }

    public User findById(Integer uid){
        String sql = "select * from t_user where uid = #{uid}" ;
        SqlSession session = SqlFactoryUtil.getSession() ;
        return session.selectOne(sql,uid,User.class) ;
    }

    public void update(User user){
        String sql = "update t_user set uname=#{uname},truename=#{truename},sex=#{sex},age=#{age} where uid=#{uid}" ;
        SqlSession session = SqlFactoryUtil.getSession() ;
        session.update(sql,user) ;
    }

    public List<User> findByName(String uname){
        String sql = "select * from t_user where uname = #{uname}" ;
        return null ;
    }

    public void saveRelationship(Integer uid,Integer rid){
        String sql = "insert into t_user_role values(#{uid},#{rid})" ;
        SqlSession session = SqlFactoryUtil.getSession() ;
        Map<String , Integer> param = new HashMap<String,Integer>();
        param.put("uid",uid);
        param.put("rid",rid);
        session.insert(sql,param);
    }

    public void deleteRelationship(Integer uid){
        String sql = "delete from t_user_role where uid = #{uid}" ;
        SqlSession session = SqlFactoryUtil.getSession() ;
        session.delete(sql,uid);
    }

    public List<Integer> findRidsByUser(Integer uid){
        String sql = "select rid from t_user_role where uid = #{uid}" ;
        SqlSession session = SqlFactoryUtil.getSession() ;
        return session.selectList(sql,uid,Integer.class) ;
    }
}
