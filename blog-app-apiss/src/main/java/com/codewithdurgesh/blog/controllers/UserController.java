package com.codewithdurgesh.blog.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.payloads.ApiResponce;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.services.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@jakarta.validation.Valid @RequestBody UserDto userDto)
	{
	 UserDto createUserDto=	this.userService.createUser(userDto);
	 return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@jakarta.validation.Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid)
	{
		UserDto updateUserDto= this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updateUserDto);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponce> deleteUser(@PathVariable("userId") Integer uid)
	{
		this.userService.deleteUser(uid);
		return new ResponseEntity<ApiResponce>(new ApiResponce("User deleted Succesfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		 return  ResponseEntity.ok( this.userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
		public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
		{
		   return ResponseEntity.ok(this.userService.getUserById(userId));
		}
	

}
