package com.ensje;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepo users;

    @RequestMapping("test")
    public String test() {
        log.info("Test");
        return "OK";
    }


    @RequestMapping("init")
    public String init()
    {
        users.initTable();
        return "Table created!";
    }

    @RequestMapping("new")
    public String getUser(@RequestParam("id") int id,@RequestParam("name") String name)
    {
        users.addUser(id,name);
        return "User added!";
    }

    @RequestMapping("user")
    public User getUser(@RequestParam("id") int id)
    {
        log.info("Get user");
        return users.getUser(id);
    }

    @RequestMapping("users")
    public List<User> getUsers(@RequestParam("ids") long[] ids)
    {
        log.info("Get users");
        return users.getUsers(ids);
    }
}