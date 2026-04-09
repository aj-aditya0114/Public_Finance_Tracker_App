package com.pft.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pft.Entity.Income;
import com.pft.Entity.User;
import com.pft.Service.IncomeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class IncomeController {

	@Autowired
	private IncomeService service;
	
	
	@GetMapping("/income")
    public String openIncome(Model model, HttpSession session) {
    	User user = (User)session.getAttribute("user"); 
    	
    	model.addAttribute("income", new Income());
    	List<Income> incomes = service.getAllIncome(user);
    	model.addAttribute("incomelist", incomes);
    
    	
        return "income";
    }
	
	
	@PostMapping("/income")
	public String addIncome(@ModelAttribute Income income, HttpSession session, RedirectAttributes re) {
		User user = (User) session.getAttribute("user");
		
		income.setUser(user);
		
		service.saveIncome(income);
		re.addFlashAttribute("success", "Income added Successfully");
		
		return "redirect:/income";
	}
	
	@GetMapping("/deleteIncome/{id}")
	public String deleteIncome(@PathVariable Long id) {
		service.deleteIncome(id);
		return "redirect:/income";
	}
}
