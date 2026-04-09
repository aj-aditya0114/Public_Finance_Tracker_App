package com.pft.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pft.Entity.Transaction;
import com.pft.Repository.TransactionRepo;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepo tRepo;
	
	public Transaction parseAndSave(String sms) {
		double amount = extractAmount(sms);
		String type = sms.contains("credited")? "INCOME" : "EXPENSE";
		
		String category = categorize(sms);
		
		Transaction txn = new Transaction();
		txn.setAmount(amount);
		txn.setType(type);
		txn.setCategory(category);
		txn.setDescription(sms);
		
		return tRepo.save(txn);
	}
	
	
	public List<Transaction> getAll(){
		return tRepo.findAll();
	}
	
	
	
	public double extractAmount(String sms) {
		Pattern pattern = Pattern.compile("\\s?(\\d+(\\.\\d{1,2})?)");
		
		Matcher matcher = pattern.matcher(sms);
		
		if(matcher.find()) {
			return Double.parseDouble(matcher.group(1));
		}
		return 0;
	}
	
	
	
	public String categorize(String sms) {
		sms = sms.toLowerCase();
		
		if(sms.contains("zomato") || sms.contains("swiggy")) {
			return "FOOD";
		}else if(sms.contains("uber") || sms.contains("ola")) {
			return "TRAVEL";
		}else if(sms.contains("amazon") || sms.contains("flipcart")) {
			return "SHOPPING";
		}else if(sms.contains("bill") || sms.contains("electricity") || sms.contains("gas") || sms.contains("emi")) {
			return "BILLS";
		}
			
			return "OTHER";
	}
	
	
	
	public boolean isOverSpending() {
		double total = tRepo.findAll()
									.stream()
									.filter(t -> t.getType().equals("EXPENSE"))
									.mapToDouble(Transaction :: getAmount)
									.sum();
		
		return total > 10000;
	}
	
	
	
}
