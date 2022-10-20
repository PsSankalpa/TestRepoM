
package com.example.Test.controller;


import com.example.Test.exception.Registered_Customer_Exception;
import com.example.Test.model.Registered_Customer;
import com.example.Test.repository.Registered_Customer_Repository;
import com.example.Test.service.Registered_Customer_Sev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//using cross-origin annotation to communicate with react.js and spring

@RestController
@CrossOrigin (origins = "http://localhost:3000")
public class Registered_Customer_Controller {

	@Autowired
	private Registered_Customer_Sev RegCusServ;
	
	@Autowired
	private Registered_Customer_Repository RegCusRepo;
	
//	for login??
//	@GetMapping("/Foodify/{Username}/{Password}")
//	public ResponseEntity<?> Login(@PathVariable("Username") String Username, @PathVariable("Password") String Password ) throws Registered_Customer_Exception {
//
//		return new ResponseEntity<>(RegCusServ.Login(Username, Password), HttpStatus.OK);
//
//	}

//	for creating the user
	@PostMapping("/Test1/Register/Signupuser")
	public ResponseEntity<?> createUser(@RequestBody Registered_Customer registeredCustomer) {

//		RegCusRepo.save(registeredCustomer);

		String userName = registeredCustomer.getuserName();
		String password = registeredCustomer.getpassword();
		String accountState = registeredCustomer.getaccountState();


//		--------------------sending data to db if there is no errors--------------------------------------------
		RegCusServ.passwordEncorder(userName, password, accountState);

		return null;
	}

//	display users
	@GetMapping("/Test1/users")
	public List<Registered_Customer> findAllUsers() {
	return RegCusServ.getUsers();
}
//	update user
	@PutMapping("/Test1/update")
	public Registered_Customer updateProduct(@RequestBody Registered_Customer product) {
	return RegCusServ.updateUser(product);
}
	//	delete user
	@DeleteMapping("/Test1/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return RegCusServ.deleteUser(id);
	}
}