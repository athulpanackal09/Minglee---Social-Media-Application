package com.demo.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entities.Post;
import com.demo.entities.User;
import com.demo.services.PostService;
import com.demo.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavigationController {
	
	@Autowired
	PostService postService;
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String index(){
		return "index";
	}
	
	@GetMapping("/openSignUp")
	public String openSignUp(){
		return "signUp";
	}
	
	@GetMapping("/openCreatePost")
	public String openCreatePost(HttpSession session){
		if(session.getAttribute("username")!=null)
		{
			return "createPost";
		}
		else
		{
			return "index";
		}
	}
	
	@GetMapping("/goHome")
	public String login(Model model, HttpSession session)	{
		if(session.getAttribute("username")!=null)
		{
			List<Post> allPosts = postService.getAllPost();
			model.addAttribute("allPosts", allPosts);
			model.addAttribute("session", session);
			return "home";
		}
		else
		{
			return "index";
		}
	}
	
	@GetMapping("/goProfile")
	public String profile(Model model, HttpSession session)	{
		if(session.getAttribute("username")!=null)
		{
			String username=(String) session.getAttribute("username");
			User user=userService.getUser(username);
			model.addAttribute("user", user);
			List<Post> posts=user.getPosts();
			model.addAttribute("posts", posts);
			return "profile";
		}
		else
		{
			return "ind";
		}
	}

	@GetMapping("/openEditProfile")
	public String openEditProfile(HttpSession session)	{
			if(session.getAttribute("username")!=null)
			{
				return "editProfile";
			}
			else
			{
				return "index";
			}
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session)	{
			session.invalidate();
			return "index";
	}
	
	
	//  working on clik on user image to get user profile
	@GetMapping("/userProfile")
    public String getUserProfile(@RequestParam String username, Model model, HttpSession session) {
        // Fetch user details from the service/repository
        User user = userService.getUser(username);
        
        // Add user details to the model
        model.addAttribute("userdetails", user);
        model.addAttribute("session", session);
        
        // Return the view name for the user profile page
        return "getUserProfile"; // Make sure you have a corresponding template for this
    }
}


