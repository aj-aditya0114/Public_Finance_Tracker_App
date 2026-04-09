package com.pft.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pft.Entity.Transaction;
import com.pft.Entity.User;
import com.pft.Service.ExpenseService;
import com.pft.Service.IncomeService;
import com.pft.Service.TransactionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TransactionController {

	@Autowired
	private IncomeService iService;
	
	@Autowired
	private ExpenseService eService;
	
	@Autowired
	private TransactionService tService;
	
	@GetMapping("/transaction")
	public String showTransactions(Model model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		
		model.addAttribute("incomeList", iService.getAllIncome(user));
		model.addAttribute("expenseList", eService.getAllExpense(user));
		
		return "transaction";
	}
	
	@PostMapping("/process-sms")
	public String processSMS(@RequestParam("smsText") String sms, Model model) {
		Transaction txn = tService.parseAndSave(sms);
		
		model.addAttribute("txn", txn);
		model.addAttribute("message", "Transaction Added Successfully");
		
		return "redirect:/dashboard";
	}
	
	

}
