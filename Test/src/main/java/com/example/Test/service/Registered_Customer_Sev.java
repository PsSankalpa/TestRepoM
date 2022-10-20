package com.example.Test.service;

import com.example.Test.exception.Registered_Customer_Exception;
import com.example.Test.model.Registered_Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface Registered_Customer_Sev {

	public String passwordEncorder(String userName,String password,String accountState);

	public List<Registered_Customer> getUsers();

	public String deleteUser(int id);

	public Registered_Customer updateUser(Registered_Customer customer);

}
