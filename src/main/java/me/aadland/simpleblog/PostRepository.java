package me.aadland.simpleblog;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, ObjectId>{
	
	@Query("{ '$or': [ " +
			" { 'title': { $regex: ?0, $options: 'i' } }, " +
			" { 'content': { $regex: ?0, $options: 'i' } } " +
			" { 'category': { $regex: ?0, $options: 'i' } } " +
			" { 'tags': { $regex: ?0, $options: 'i' } } " +
			"] }")
	List<Post> findByWildcard(String regexPattern);
}
