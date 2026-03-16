package com.pft.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pft.Entity.Income;
import com.pft.Entity.User;

public interface IncomeRepo extends JpaRepository<Income, Long>{
	
	List<Income> findByUser(User user);
}
