package com.pft.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pft.Entity.Budget;
import com.pft.Entity.User;

public interface BudgetRepo extends JpaRepository<Budget, Long>{

	List<Budget> findByUser(User user);
}
