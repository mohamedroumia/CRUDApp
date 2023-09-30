package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserController() {
	}

	@PostMapping("/createUser")
	User createUser(@RequestBody User user) {
		return this.userRepository.save(user);	
	}
	
	@GetMapping("/getUser/{id}")
	User getUser(@PathVariable Long id) throws Exception {
		return this.userRepository.findById(id).get();
	}

	@GetMapping("/getUser/{name}/{surname}")
	User getUserBySurname(@PathVariable String name, @PathVariable String surname) throws Exception {
		return this.userRepository.findByNameAndSurname(name, surname).get();
	}
	
	@GetMapping("/getUsers")
	Iterable<User> getUsers() {
		return this.userRepository.findAll();
	}
	
	@PutMapping("/updateUser/{id}")
	User updateUser(@PathVariable Long id, @RequestBody User newUser) {
		User user = this.userRepository.findById(id).get();
		user.setUsername(newUser.getUsername());
		user.setAddress(newUser.getAddress());
		return this.userRepository.save(user);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	void deleteUser(@PathVariable Long id) {
		this.userRepository.deleteById(id);
	}
	@PostMapping("/createUserFromUploadCSV")
	ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			try (
					InputStreamReader reader = new InputStreamReader(file.getInputStream());
		            BufferedReader bufferedReader = new BufferedReader(reader)) {
		            String line;
		            while ((line = bufferedReader.readLine()) != null) {
		                String[] data = line.split(";");
		                User user = new User();
		                user.setName(data[0]);
		                user.setSurname(data[1]);
		                user.setUsername(data[2]);
		                user.setEmail(data[3]);
		                user.setAddress(data[4]);
		                this.userRepository.save(user);
		            }
		        }
			return ResponseEntity.status(HttpStatus.OK)
					.body("CSV file uploaded and user data loaded into the database.");
        } else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Please upload a CSV file.");
        }
        
	 }
	

}
