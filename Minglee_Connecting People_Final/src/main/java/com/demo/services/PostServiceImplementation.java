package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Post;
import com.demo.repositories.PostRepository;

@Service
public class PostServiceImplementation implements PostService  {

	@Autowired
	PostRepository repo;

	@Override
	public void createPost(Post post) {
		repo.save(post);
	}

	@Override
	public List<Post> getAllPost() {
		return repo.findAll();
	}
	
	@Override
	public Post getPost(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public void updatePost(Post post) {
		repo.save(post);
	}
}
