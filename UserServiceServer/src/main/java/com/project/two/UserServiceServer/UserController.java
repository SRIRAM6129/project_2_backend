package com.project.two.UserServiceServer;

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


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userservice;



	@GetMapping("/")
	public String greet(){
		return "hello form the user";
	}

	@GetMapping("/show/all")
	public ResponseEntity<?> showAllUser() {
		try {
			List<UserModel> users = userservice.showAllUser();
			return new ResponseEntity<>(users,HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("An error occured while fetching the data",HttpStatus.BAD_GATEWAY);
		}
	}

	@GetMapping("/show/{id}")
	public ResponseEntity<?> showUserById(@PathVariable("id") Long id){
		try {
			UserModel user = userservice.showUserById(id);
			return new ResponseEntity<>(user,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("ERROR : "+e.getMessage(),HttpStatus.BAD_GATEWAY);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<?> addUser(@RequestBody UserModel user){
		try {
			userservice.addUser(user);
			return new ResponseEntity<>("User added successfully",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("ERROR : "+e.getMessage(),HttpStatus.BAD_GATEWAY);
		}
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Long id,@RequestBody UserModel user){

		try {
			userservice.updateUser(id, user);
			return new ResponseEntity<>("The User updated Successfully",HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("ERROR : "+e.getMessage(),HttpStatus.BAD_GATEWAY);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){

		try {
			userservice.deleteUser(id);
		return new ResponseEntity<>("The User has been deleted successfully",HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("ERROR : "+e.getMessage(),HttpStatus.BAD_GATEWAY);
		}
	}
}

