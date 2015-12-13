package com.test.security;

import org.junit.Test;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/12/12.
 */
public class MyUserDetailsService extends HibernateDaoSupport implements UserDetailsService{
    private  Connection conn;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select U.username as username,U.password as password,U.accountEnabled AS 'enabled' from user U where U.username=?";
            String sql2 = "select c.username,r.name as authority from role r,(select * from user u,user_role ur where u.id = ur.user_id) c where c.role_id = r.id and c.username = ?";
            PreparedStatement psmt1 = conn.prepareStatement(sql);
            PreparedStatement psmt2 = conn.prepareStatement(sql2);
            psmt1.setString(1, username);
            psmt2.setString(1, username);
            ResultSet resultSet1 = psmt1.executeQuery();
            ResultSet resultSet2 = psmt2.executeQuery();
            while(resultSet1.next()){
                String password = resultSet1.getNString("password");
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                Set set = new HashSet();
                while(resultSet2.next()){
                    String authority = resultSet2.getNString("authority");
                    Role role = new Role();
                    role.setName(authority);
                    set.add(role);
                }
                user.setRoles(set);
                return user;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Test
    public void test(){
        User user = (User) loadUserByUsername("tanqiao");
        System.out.println(user.getUsername());
    }

}
