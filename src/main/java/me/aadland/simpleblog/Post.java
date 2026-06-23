package me.aadland.simpleblog;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
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
}
