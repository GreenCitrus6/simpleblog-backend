package me.aadland.simpleblog;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/blog")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping
	public ResponseEntity<List<Post>> allPosts(
			@RequestParam(required = false) String term
			) {
		
		if (!term.isBlank()) {
			return new ResponseEntity<List<Post>>(postService.searchPostByTerm(term), HttpStatus.OK);
		}
		
		return new ResponseEntity<List<Post>>(postService.allPosts(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Post>> getPostById(@PathVariable ObjectId id) {
		return new ResponseEntity<Optional<Post>>(postService.getPostById(id), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable ObjectId id) {
		//check if resource exists
		if (!postService.existsByid(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		postService.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Post> createPost(@RequestBody PostRequest request) {
		
		//if valid, create Post and register to MongoDB
		Post post = new Post();
		post.setTitle(request.title());
		post.setContent(request.content());
		post.setCategory(request.category());
		post.setTags(request.tags());
		
		Post savedPost = postService.createPost(post);
		
		return new ResponseEntity<Post>(savedPost, HttpStatus.OK);
		
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable ObjectId id, @RequestBody Post postDetails) {
		Post updatedPost = postService.update(id, postDetails);
		
		if (updatedPost == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Post>(updatedPost, HttpStatus.OK);
		
	}
	
}
