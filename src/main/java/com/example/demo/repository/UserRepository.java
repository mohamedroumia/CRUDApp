package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

	Optional<User> findByName(String name);
	Optional<User> findBySurname(String surname);
	Optional<User> findByNameAndSurname(String name, String surname);

}