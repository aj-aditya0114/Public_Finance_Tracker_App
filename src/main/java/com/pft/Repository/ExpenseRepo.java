package com.pft.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pft.Entity.Expense;
import com.pft.Entity.User;



public interface ExpenseRepo extends JpaRepository<Expense, Long>{
	List<Expense> findByUser(User user);
	List<Expense> findAllByCategory(User user);
	
//	@Query("SELECT SUM(e.amount) FROM Expense e WHERE e.category = :category AND e.user = :user")
//	Double getTotalExpenseByCategoryAndUser(String category, User user);
//	
	@Query("SELECT SUM(e.amount) FROM Expense e WHERE e.category = :category AND e.user = :user")
	Double getTotalExpenseByCategoryAndUser(@Param("category") String category,
	                                        @Param("user") User user);
//	@Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE LOWER(e.category) = LOWER(:category) AND e.user = :user")
//	Double getTotalExpenseByCategoryAndUser(String category, User user);
	                                      
}
