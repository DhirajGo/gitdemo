package com.codewithdurgesh.blog.services;


import com.codewithdurgesh.blog.payloads.CommentDto;

public interface CommentService {
	
	public CommentDto createComment(CommentDto CommentDto,Integer postID);
	public void deleteComment(Integer commentId);

}
