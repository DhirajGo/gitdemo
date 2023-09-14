package com.codewithdurgesh.blog.payloads;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	
	@Id
	private int id;
	
	private String content;

}
