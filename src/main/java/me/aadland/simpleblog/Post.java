package me.aadland.simpleblog;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
	@Id
	private ObjectId id;
	private String title;
	private String content;
	private String category;
	private List<String> tags;
	
	//getters
	public ObjectId getObjectId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getCategory() {
		return category;
	}
	public List<String> getTags() {
		return tags;
	}
	
	//setters
	public void setId(ObjectId id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	//constructors
	
}
