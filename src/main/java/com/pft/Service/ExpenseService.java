package com.pft.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pft.Entity.Budget;
import com.pft.Entity.Expense;
import com.pft.Entity.User;
import com.pft.Repository.BudgetRepo;
import com.pft.Repository.ExpenseRepo;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepo repo;
	
	@Autowired
	private BudgetRepo budgetRepo;
	
	public String addExpense(Expense e) {
		String category = e.getCategory().trim();
				
		Budget budget = budgetRepo.findByCategoryAndUser(category, e.getUser());
		if(budget == null) {
			return "No budget set for this category !";
		}
		
		Double total = repo.getTotalExpenseByCategoryAndUser(category, e.getUser());
		
		if(total == null) {
			total = 0.0;
		}
		
		double newTotal = total + e.getAmount();
		
		System.out.print("Budget = " + budget.getLimitAmount());
		System.out.print("Current Total = " + total);
		System.out.print("New Total = " + newTotal);
		System.out.println("Category = " + category);
		System.out.println("Userin service = " + e.getUser());
		
		
		if(budget != null) {
		    if(newTotal > budget.getLimitAmount()) {
		        return "Budget exceeded for " + e.getCategory();
		    }
		}
		repo.save(e);
		double remaining = budget.getLimitAmount() - newTotal;
		return "Expense Added Successfully for " + category+ " Remaining Budget : " + remaining;
	}
	
	
	
	public List<Expense> getAllExpense(User user){
		return repo.findByUser(user);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public List<Expense> getExpenseByCategory(User user) {
		List<Expense> list = repo.findAllByCategory(user);
		return list;
	}
}
