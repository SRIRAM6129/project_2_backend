package com.project.two.UserServiceServer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	boolean existsByPhone(Long phone);

	boolean existsByEmail(String email);
}
