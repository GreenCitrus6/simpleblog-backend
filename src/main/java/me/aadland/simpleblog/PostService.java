package me.aadland.simpleblog;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> allPosts() {
		return postRepository.findAll();
	}
	
	public Optional<Post> getPostById(ObjectId id) {
		return postRepository.findById(id);
	}
	
	public boolean existsByid(ObjectId id) {
		return postRepository.existsById(id);
	}
	
	public Post createPost(Post post) {
		return postRepository.save(post);
	}
	
	public void deleteById(ObjectId id) {
		postRepository.deleteById(id);
	}
	
	public Post update(ObjectId id, Post postDetails) {
		Optional<Post> optionalPost = postRepository.findById(id);
		
		if(optionalPost.isPresent()) {
			Post current = optionalPost.get();
			
			current.setTitle(postDetails.getTitle());
			current.setContent(postDetails.getContent());
			current.setCategory(postDetails.getCategory());
			current.setTags(postDetails.getTags());
			
			return postRepository.save(current);
			
		}
		
		return null;
	}
}
