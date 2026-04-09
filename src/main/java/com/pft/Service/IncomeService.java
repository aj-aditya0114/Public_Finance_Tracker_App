package com.pft.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pft.Entity.Income;
import com.pft.Entity.User;
import com.pft.Repository.IncomeRepo;

@Service
public class IncomeService {

	@Autowired
	private IncomeRepo repo;
	
	public void saveIncome(Income income) {
		repo.save(income);
	}
	
	public List<Income> getAllIncome(User user){
		return repo.findByUser(user);
	}
	
	public void deleteIncome(Long id) {
		repo.deleteById(id);
	}
}
