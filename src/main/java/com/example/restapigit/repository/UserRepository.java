package com.example.restapigit.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restapigit.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
