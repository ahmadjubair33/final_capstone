package com.knoldus.UserManagement.controller;

import com.knoldus.UserManagement.Dao.UserDao;
import com.knoldus.UserManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/")
    public String index(Model model){
        List<User> users = (List<User>) userDao.findAll();
        model.addAttribute("users",users);
        return "index";
    }

    @RequestMapping("/register")
    public String UserRegister(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register")
    public String UserSave(User user, Model model){
        userDao.save(user);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String UserEdit(@PathVariable("id") long id, Model model){
        User user = userDao.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid id"+id));
        model.addAttribute("user",user);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String UserUpdate(@PathVariable("id") long id , User user){
        System.out.println(user.getAddress()+"----------------------");
        userDao.save(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String UserDelete(@PathVariable("id") long id , Model model){
        User user = userDao.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid id"+id));
        userDao.delete(user);
        return "redirect:/";
    }
}
