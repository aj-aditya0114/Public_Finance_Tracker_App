package com.pft.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pft.Entity.Expense;
import com.pft.Entity.User;
import com.pft.Repository.ExpenseRepo;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepo repo;
	
	public void addExpense(Expense e) {
		repo.save(e);
	}
	
	public List<Expense> getAllExpense(User user){
		return repo.findByUser(user);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
