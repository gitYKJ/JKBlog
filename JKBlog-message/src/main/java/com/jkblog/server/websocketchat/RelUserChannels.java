package com.jkblog.server.websocketchat;

import com.jkblog.compojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelUserChannels {
    final static String url = "jdbc:mysql://127.0.0.1/jkblog";
    final static String name = "com.mysql.jdbc.Driver";
    final static String user = "root";
    final static String password = "125800";
    public static List<String> selectLableUser(String lable) throws ClassNotFoundException, SQLException {
        List<String> lable_user=new ArrayList<>();
        String password = "125800";
        ResultSet rs = null;
        PreparedStatement pre=null;
        Statement ps=null;
        Connection conn=null;
        Class.forName(name);
        conn= DriverManager.getConnection(url,user,password);
        String lable_sql="select username from user RIGHT JOIN (select userId from tag_user, (select id from tag where lable='python') b where tagId=b.id) a\n" +
                "               on user.id=a.userId";
        if (conn!=null) {
            System.out.println("获取连接成功");
            ps= conn.createStatement();
            //pre=conn.prepareStatement(lable_sql);
            rs=ps.executeQuery(lable_sql);
            ResultSetMetaData metaData=rs.getMetaData();
            while(rs.next()){
                String username=null;
               for (int i=1;i<=metaData.getColumnCount();i++){
                   username= rs.getObject(i,String.class);
               }
               lable_user.add(username);
            }
        }else {
            System.out.println("获取连接失败");
        }
        //查询数据库获取用户信息
        return lable_user;
    }
    public static List<User> selectRelUser(int username) throws ClassNotFoundException, SQLException {

        ResultSet rs = null;
        PreparedStatement ps=null;
        Connection conn=null;
        Class.forName(name);
        conn= DriverManager.getConnection(url,user,password);
        //查询数据库获取用户信息
        String rel_sql="select username from user RIGHT JOIN (select rel_user_id from rel_user, (select id from user where username='admin') b where user_id=b.id) a\n" +
                "on user.id=a.rel_user_id";
        return null;
    }
}
