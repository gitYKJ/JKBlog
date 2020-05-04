package com.jkblog.server.websocketchat;

import java.sql.SQLException;
import java.util.List;

public class testSql {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<String> list=RelUserChannels.selectLableUser("python");
        System.out.println(list);


    }
}
