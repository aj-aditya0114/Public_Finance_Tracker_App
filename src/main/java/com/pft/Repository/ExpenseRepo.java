package com.pft.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pft.Entity.Expense;
import com.pft.Entity.User;



public interface ExpenseRepo extends JpaRepository<Expense, Long>{
	List<Expense> findByUser(User user);
}
