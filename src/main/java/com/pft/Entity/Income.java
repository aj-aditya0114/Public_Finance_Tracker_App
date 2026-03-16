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
public class Income {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String source;
	
	private double amount;
	
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
}
