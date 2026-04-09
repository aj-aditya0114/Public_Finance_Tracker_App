package com.pft.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
@Table
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double amount;
	private String type;
	private String category;
	@Column(length = 500)
	private String description;
	private LocalDateTime date;
	private String source;
	
	public Transaction(){
		this.date = LocalDateTime.now();
	}
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
}
