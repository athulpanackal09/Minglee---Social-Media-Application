package com.demo.services;

import java.util.List;

import com.demo.entities.Post;

public interface PostService {

	void createPost(Post post);
	
	List<Post> getAllPost();

	Post getPost(Long id);

	void updatePost(Post post);


}
