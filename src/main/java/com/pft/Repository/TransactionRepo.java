package com.pft.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pft.Entity.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long>{

	List<Transaction> findByType(String type);
	List<Transaction> findAll();
	
	List<Transaction> findByDateBetween(LocalDateTime start, LocalDateTime end);
	
	@Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type='EXPENSE'")
	double getTotalExpense();
}
