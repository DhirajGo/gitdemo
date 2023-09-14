package com.codewithdurgesh.blog.controllers;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithdurgesh.blog.config.AppConstant;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.ApiResponce;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponce;
import com.codewithdurgesh.blog.services.FileService;
import com.codewithdurgesh.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private  String path;
	
	
	//CREATE POST
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto>createPost( @RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//GET POST BY USER
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
	{
		List<PostDto>posts=this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	//GET POST BY CATEGORY
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
	{
		List<PostDto>posts=this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//GET ALL POSTS
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponce>getAllPosts(@RequestParam(value="pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false)Integer pageSize,
	       @RequestParam(value="sortBy",defaultValue = AppConstant.SORT_BY,required = false)String sortBy)
	{
		PostResponce postResponce=this.postService.getAllPost(pageNumber, pageSize,sortBy);
		return new ResponseEntity<PostResponce>(postResponce,HttpStatus.OK);
	}
	
	//GET POST DETAILS BY ID
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto>getPostById(@PathVariable Integer postId)
	{
		PostDto postDto=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	//POST DELETE
	@DeleteMapping("/posts/{postId}")
	public ApiResponce deletepost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return new ApiResponce("Post deleted succesfully..",true);
	}
	
	//POST UPDATE
	    @PutMapping("/posts/{postId}")
		public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
		{
		PostDto updatedPost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
		}
	    
	 //SEARCH
	    @GetMapping("/posts/search/{keywords}")
	    public ResponseEntity<List<PostDto>> searchPostByTittle(@PathVariable ("keywords")String keywords)
	    {
	    	List<PostDto>result = this.postService.searchPosts(keywords);
	    	return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	    }
	    
	    //POST UPLOAD IMAGE
	    @PostMapping("/post/image/upload/{postId}")
	    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException
	    {
	    	PostDto postDto = this.postService.getPostById(postId);
	    	String fileName = this.fileService.uploadImage(path, image);
	    	postDto.setImageName(fileName);
	    	PostDto updatePost = this.postService.updatePost(postDto, postId);
	    	return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	   	
	    }

}
