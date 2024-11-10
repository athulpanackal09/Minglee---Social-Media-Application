package com.demo.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entities.Post;
import com.demo.entities.User;
import com.demo.services.PostService;
import com.demo.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	PostService postService;
	
	@PostMapping("/signUp")
	public String addUser(@ModelAttribute User user, HttpSession session, Model model)
	{
		if(userService.validateUser(user))
		{
			userService.addUser(user);
			session.setAttribute("username", user.getUsername());
			model.addAttribute("session", session);
			return "home";
		}
		else
		{
			return "index";
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username,@RequestParam String password, Model model, HttpSession session)
	{
		if(userService.login(username, password))
		{
			List<Post> allPosts = postService.getAllPost();
			session.setAttribute("username", username);
			model.addAttribute("allPosts", allPosts);
			//Adding session object to model 
			model.addAttribute("session", session);
			return "home";
		}
		else
		{
			return "index";
		}
	}
	

	@PostMapping("/updateProfile")
	public String updateProfile(@RequestParam String dob, @RequestParam String gender, @RequestParam String city, @RequestParam String bio, @RequestParam String college, 
			@RequestParam String linkedIn, @RequestParam String gitHub, @RequestParam MultipartFile profilePic, HttpSession session, Model model) {
		//TODO: process POST request
		String username=(String) session.getAttribute("username");
		//fetch user object using username
		User user=userService.getUser(username);
		//update and save object
		user.setDob(dob);
		user.setGender(gender);
		user.setCity(city);
		user.setBio(bio);
		user.setCollege(college);
		user.setLinkedIn(linkedIn);
		user.setGitHub(gitHub);
		try
		{
			user.setProfilePic(profilePic.getBytes());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		userService.updateUser(user);
		model.addAttribute("user", user);
		List<Post> allPosts = postService.getAllPost();
		model.addAttribute("allPosts", allPosts);
		return "home";
		
		
	}
	

}



