package com.gqz.bookcity.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.gqz.bookcity.constant.StatusCode;
import com.gqz.bookcity.entity.Result;
import com.gqz.bookcity.po.User;
import com.gqz.bookcity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/add")
    public Result<User> add(@RequestBody User user) {

        Result<User> result = new Result<>();
        try {
            userService.add(user);
            result.success("添加用户成功");
        } catch (Exception e) {
            result.failure("添加用户失败");
        }
        return result;
    }

    @RequestMapping("/list")
    //cpage 当前页 第几页
    public String list(Integer pageNum, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        if (pageSize == null) {
            pageSize = 5;
        }
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo info = userService.getUserList(pageNum, pageSize);

        request.setAttribute("ulist", info.getList());
        request.setAttribute("pageNum", pageNum);
        request.setAttribute("totalPage", info.getPages());
        return "user_list";
    }

    @RequestMapping("/deleteUserById")
    public String deleteUserById(Integer id) {
        //根据用户id删除用户信息
        userService.deleteUserById(id);
        return "redirect:/user/list";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
        //1.根据id查询对应的user信息
        User u = userService.getUserById(id);
        //2.将查到的user放入作用域
        model.addAttribute("u", u);

        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";


        return "user_update";
    }

    @RequestMapping("/update")
    public Result update(@RequestBody User user) {
        Result result = new Result();
        try {
            userService.update(user);
            result.success("更新成功");
        } catch (Exception e) {
            result.failure("更新失败");
        }
        return result;
    }

    @GetMapping("findUserByUsername")
    public Result findUserByUsername(String username) {

        Boolean b = userService.findUserByUsername(username);
        if (b) {
            return new Result(true, StatusCode.OK, "该用户名不存在", b);
        } else {
            return new Result(false, StatusCode.ERROR, "该用户名已存在", b);
        }
    }

    //    @PreAuthorize("permitAll()")
    @PostMapping("signIn")
    public Result signIn(@RequestBody User user) {
        User signInUser = userService.signIn(user);
        redisTemplate.opsForValue().set("loginUser", JSON.toJSONString(signInUser));
        return new Result(true, StatusCode.OK, "登陆成功", null);
    }

    @GetMapping("signOut")
    public Result signOut(HttpServletRequest request) {
        Boolean aBoolean = redisTemplate.delete("loginUser");
        if (!aBoolean) {
            throw new RuntimeException("注销失败");
        }
        return new Result(true, StatusCode.OK, "注销成功", null);

    }
}
