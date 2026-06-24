package me.aadland.simpleblog;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record PostRequest(
	@NotBlank(message = "Post title is required") String title,
	@NotBlank(message = "Post content is required") String content,
	@NotBlank(message = "Post category is required") String category,
	List<String> tags
) {}
