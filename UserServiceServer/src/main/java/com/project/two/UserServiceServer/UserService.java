package com.project.two.UserServiceServer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


	@Autowired
	private UserRepository userrepo;


	private List<UserModel> showALlUser(){
		return userrepo.findAll();

	}

	private UserModel showUserById(Long id){
		return userrepo.findById(id).orElse(null);
	}

	private UserModel addUser(UserModel user){
		return userrepo.save(user);
	}



}
