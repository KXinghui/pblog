package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;

import pojo.User;
import service.UserService;
import util.Page;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("admin/user")
	public String list(Model model, Page page) {
		
		PageHelper.startPage(page.getPageFirst(), page.getPageRecord());
		List<User> us = userService.list();
		return "admin/list/listUser";
	}

}
