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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/blog")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping
	public ResponseEntity<List<Post>> allPosts() {
		return new ResponseEntity<List<Post>>(postService.allPosts(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Post>> getPostById(@PathVariable ObjectId id) {
		return new ResponseEntity<Optional<Post>>(postService.getPostById(id), HttpStatus.OK);
	}
	
	//delete endpoint
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable ObjectId id) {
		//check if resource exists
		if (!postService.existsByid(id)) {
			return ResponseEntity.notFound().build();
		}
		
		postService.deleteById(id);
		
		return ResponseEntity.noContent().build();
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
	
	//put endpoint
	
}
