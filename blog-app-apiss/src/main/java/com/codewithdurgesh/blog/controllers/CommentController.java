package com.codewithdurgesh.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.payloads.ApiResponce;
import com.codewithdurgesh.blog.payloads.CommentDto;
import com.codewithdurgesh.blog.services.CommentService;

@RestController("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId)
    {
    	CommentDto createComment = this.commentService.createComment(commentDto, postId);
    	return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
    }
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponce>deleteComment(@PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Comment delete Succesfully",true),HttpStatus.OK);
	}

}
