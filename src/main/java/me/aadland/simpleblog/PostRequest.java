package me.aadland.simpleblog;

import java.util.List;

public record PostRequest(
	String title,
	String content,
	String category,
	List<String> tags
) {}
