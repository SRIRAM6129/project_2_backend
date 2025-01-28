package com.project.two.UserServiceServer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	public final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	public UserRepository userrepo;

	public List<UserModel> showAllUser() {
		try {
			List<UserModel> users = userrepo.findAll();
			if (users.isEmpty()) {
				LOGGER.info("The User data is Empty");
			}
			LOGGER.info("ALL USER DATA HAS BEEN FETCHED");
			return users;

		} catch (Exception e) {
			LOGGER.error("Error while fetching the User data", e);
			throw new RuntimeException("Unable to fetch the User data", e);
		}

	}

	public UserModel showUserById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("The id cannot be null");
		}
		if (id < 0) {
			throw new IllegalArgumentException("The Id cannot be an negative value");
		}
		try {
			LOGGER.info("THE USER "+id+" has fetched");
			return userrepo.findById(id).orElse(null);

		} catch (Exception e) {
			LOGGER.error("Error while fetching the User data", e);
			throw new RuntimeException("Unable to fetch the User data", e);
		}
	}

	public UserModel addUser(UserModel user) {

		if (user.equals(null)) {
			throw new IllegalArgumentException("The User cannot be null");
		}

		if (user.getName().trim().isEmpty() || user.getName() == null) {
			throw new IllegalArgumentException("User name is missing");
		}

		if (user.getEmail().trim().isEmpty() || user.getEmail() == null) {
			throw new IllegalArgumentException("User Email is missing");

		}
		if (user.getPhone() == null) {
			throw new IllegalArgumentException("User phone is missing");
		}
		if (user.getLocation().trim().isEmpty() || user.getLocation() == null) {
			throw new IllegalArgumentException("User location is missing");
		}

		if (userrepo.existsByEmail(user.getEmail())) {
			throw new IllegalArgumentException("Email already exists");
		}
		if (userrepo.existsByPhone(user.getPhone())) {
			throw new IllegalArgumentException("Phone Number already exists");
		}
		if (!user.getEmail().contains("@gmail.com")) {
			throw new IllegalArgumentException("the email is not valid");
		}

		try {
			LOGGER.info("The User added successfully");
			return userrepo.save(user);
		} catch (Exception e) {
			LOGGER.error("Error while adding the User", e);
			throw new RuntimeException("Unable to add User", e);
		}
	}

	public void updateUser(Long id, UserModel user) {

		if (id == null) {
			throw new IllegalArgumentException("The id cannot be null");
		}
		if (id < 0) {
			throw new IllegalArgumentException("The id cannot be an negative value");
		}
		if (user == null) {
			throw new IllegalArgumentException("The user cannot be null");
		}
		try {
			UserModel usr = userrepo.findById(id)
					.orElseThrow(() -> new RuntimeException("The user is not found "));

			usr.setName(user.getName());
			usr.setEmail(user.getEmail());
			usr.setPhone(user.getPhone());
			usr.setAccount(user.getAccount());
			usr.setLocation(user.getLocation());
			LOGGER.info("The user with "+id+" has updated");
			userrepo.save(usr);

		} catch (Exception e) {

			e.printStackTrace();
			LOGGER.error("Error while Updating the User", e);
			throw new RuntimeException("Unable to Update User", e);
		}
	}

	public void deleteUser(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("The id cannot be null");
		}
		if (id < 0) {
			throw new IllegalArgumentException("The id cannot be an negative value");
		}
		if (userrepo.findById(id).orElse(null) == null) {
			throw new IllegalArgumentException("the user is not found");
		}
		try {
			LOGGER.info("The User has been deleted successfully");
			userrepo.deleteById(id);

		} catch (Exception e) {
			LOGGER.error("Error while Deleting the User", e);
			throw new RuntimeException("Unable to delete User", e);
		}
	}

}
