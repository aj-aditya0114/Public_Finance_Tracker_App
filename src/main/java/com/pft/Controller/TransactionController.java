package com.pft.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pft.Entity.User;
import com.pft.Service.ExpenseService;
import com.pft.Service.IncomeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TransactionController {

	@Autowired
	private IncomeService iService;
	
	@Autowired
	private ExpenseService eService;
	
	@GetMapping("/transaction")
	public String showTransactions(Model model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		
		model.addAttribute("incomeList", iService.getAllIncome(user));
		model.addAttribute("expenseList", eService.getAllExpense(user));
		
		return "transaction";
	}
}
