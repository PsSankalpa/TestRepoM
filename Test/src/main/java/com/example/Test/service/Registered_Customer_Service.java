package com.example.Test.service;

import com.example.Test.exception.Registered_Customer_Exception;
import com.example.Test.model.Registered_Customer;
import com.example.Test.repository.Registered_Customer_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Registered_Customer_Service implements Registered_Customer_Sev{

	@Autowired
	private Registered_Customer_Repository RegCusRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public String passwordEncorder(String userName, String password, String accountState) {
		// TODO Auto-generated method stub
		
		String epassword = passwordEncoder.encode(password);
		
		Registered_Customer user = new Registered_Customer();
		
		user.setuserName(userName);
		user.setpassword(epassword);
		user.setaccountState(accountState);
		
		RegCusRepo.save(user);
		System.out.println(epassword);
		return null;
	}

	@Override
	public List<Registered_Customer> getUsers() {
		return RegCusRepo.findAll();
	}

	@Override
	public String deleteUser(int id) {
		RegCusRepo.deleteById(id);
		return "product removed !! " + id;
	}

	@Override
	public Registered_Customer updateUser(Registered_Customer customer) {
		System.out.println(customer.getuserName());
		Registered_Customer existingUser = RegCusRepo.findById(customer.getId());
		existingUser.setuserName(customer.getuserName());
		System.out.println(existingUser.getuserName());
		return RegCusRepo.save(existingUser);
	}

}
