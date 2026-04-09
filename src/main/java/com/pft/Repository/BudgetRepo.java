package com.pft.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.pft.Entity.Budget;
import com.pft.Entity.User;

public interface BudgetRepo extends JpaRepository<Budget, Long>{

	List<Budget> findByUser(User user);
	
	//@Query("SELECT b FROM Budget b WHERE LOWER(b.category) = LOWER(:category) AND b.user = :user")
	//Budget findByCategoryAndUser(@Param("category") String category,
	                            // @Param("user") User user);
	
	Budget findByCategoryAndUser(String category, User user);
}
