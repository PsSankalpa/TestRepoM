package com.example.Test.repository;



import com.example.Test.model.Registered_Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Registered_Customer, String> {

	Registered_Customer findByuserName(String userName);

};