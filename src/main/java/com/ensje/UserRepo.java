
package com.ensje;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class UserRepo {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private static final String INSERTSQL = "INSERT INTO sb_user (id,name) VALUES (?,?)";


    @Autowired
    protected JdbcTemplate jdbc;

    public User getUser(int id) {
        return jdbc.queryForObject("SELECT * FROM sb_user WHERE id=?", userMapper, id);
    }

    public void initTable()
    {
       jdbc.execute("CREATE TABLE sb_user(ID INT PRIMARY KEY NOT NULL,NAME TEXT)");
    }

    public void addUser(int userId,String name)
    {
        Object[] params = new Object[] { userId, name };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR};

        //jdbc.execute("INSERT INTO sb_user (ID, NAME) VALUES (" + userId + ", " + name + ");")
        int row = jdbc.update(INSERTSQL, params, types);
        System.out.println(row + " inserted");

    }

    public List<User> getUsers(long[] ids) {
        String inIds = StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(ids));
        return jdbc.query("SELECT * FROM sb_user WHERE id IN (" + inIds + ")", userMapper);
    }

    private static final RowMapper<User> userMapper = new RowMapper<User>() {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getLong("id"), rs.getString("name"));
            return user;
        }
    };

}