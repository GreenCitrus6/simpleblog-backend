package me.aadland.simpleblog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> allPosts() {
		return postRepository.findAll();
	}
}
