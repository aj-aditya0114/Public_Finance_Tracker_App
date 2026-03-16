package com.pft.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pft.Entity.Budget;
import com.pft.Entity.User;
import com.pft.Repository.BudgetRepo;

@Service
public class BudgetService {

	@Autowired
	private BudgetRepo repo;
	
	public void addBudget(Budget bgt) {
		repo.save(bgt);
	}
	
	public List<Budget> getAllBudget(User user){
		return repo.findByUser(user);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
