package com.gqz.bookcity.controller;

import com.github.pagehelper.PageInfo;
import com.gqz.bookcity.entity.Result;
import com.gqz.bookcity.po.User;
import com.gqz.bookcity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    @ResponseBody
    public Result<User> add(@RequestBody User user){
        Result<User> result = new Result<>();
        try {
            userService.add(user);
            result.success("添加用户成功");
        }catch (Exception e){
            result.failure("添加用户失败");
        }
        return result;
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user_add";
    }

    @RequestMapping("/list")
    //cpage 当前页 第几页
    public String list(Integer pageNum,Integer pageSize,HttpServletRequest request, HttpServletResponse response){
//        cpage = cpage == null ? 1 : cpage;
//        Integer pageSize = 3;
//        Integer start = (cpage - 1) * pageSize;
//        Map<String,Object> map = new HashMap<>();
//        map.put("start",start);
//        map.put("pageSize",pageSize);
        if (pageSize == null) {
            pageSize=5;
        }
        if (pageNum==null){
            pageNum=1;
        }
        PageInfo info = userService.getUserList(pageNum,pageSize);
        //求总条数
//        Integer totalCount = userService.getListCount();
        //求总页数
//        Integer totalPage = (totalCount / pageSize) + (totalCount % pageSize ==0 ? 0 : 1);


        request.setAttribute("ulist",info.getList());
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("totalPage",info.getPages());
        return "user_list";
    }

    @RequestMapping("/deleteUserById")
    public String deleteUserById(Integer id){
        //根据用户id删除用户信息
        userService.deleteUserById(id);
        return "redirect:/user/list";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model,HttpServletRequest request, HttpServletResponse response){
        //1.根据id查询对应的user信息
        User u = userService.getUserById(id);
        //2.将查到的user放入作用域
        model.addAttribute("u",u);

        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


        return "user_update";
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody User user){
        Result result = new Result();
        try {
            userService.update(user);
            result.success("更新成功");
        }catch (Exception e){
            result.failure("更新失败");
        }
        return result;
    }





}
