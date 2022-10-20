
package com.example.Test.repository;

import com.example.Test.model.Registered_Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Registered_Customer_Repository extends JpaRepository<Registered_Customer,Integer> {
	
	Registered_Customer findByuserName(String userName);

	Registered_Customer findById(int id);
}



