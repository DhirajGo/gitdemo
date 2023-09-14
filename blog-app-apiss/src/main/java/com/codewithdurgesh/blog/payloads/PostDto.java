package com.codewithdurgesh.blog.payloads;
import java.util.ArrayList;
import java.util.List;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Comment;
import com.codewithdurgesh.blog.entities.User;

import lombok.Data;


@Data
public class PostDto {
	
	private Integer postId;
	
	private String tittle;
	
	private String content;
	
	private String imageName;
	
	private String addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	List<Comment>comments=new ArrayList<>();

}
