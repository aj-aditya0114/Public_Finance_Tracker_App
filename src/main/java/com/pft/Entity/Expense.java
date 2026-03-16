package com.pft.Entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Expense {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String category;
	
	private double amount;
	
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
}
