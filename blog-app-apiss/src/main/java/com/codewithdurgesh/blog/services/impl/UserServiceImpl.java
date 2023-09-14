package com.codewithdurgesh.blog.services.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User userSaved=this.userRepo.save(user);
		return null;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user1=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		user1.setName(userDto.getName());
		user1.setEmail(userDto.getEmail());
		user1.setPassword(userDto.getPassword());
		user1.setAbout(userDto.getAbout());
		User updatedUser= this.userRepo.save(user1);
		UserDto userDto1= this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User>users= this.userRepo.findAll();
		List<UserDto>userDtos= users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
	User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);
		
	}
	private User dtoToUser(UserDto userDto)
	{
		User user=this.modelMapper.map(userDto, User.class);       //by using modelmapper
		
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());     this is manually
//		user.setAbout(userDto.getAbout());
		return  user;
	}
	
	private UserDto userToDto(User user)
	{
		UserDto userDto=this.modelMapper.map(user, UserDto.class);      //by using modelmapper
		return userDto;
	}

}
