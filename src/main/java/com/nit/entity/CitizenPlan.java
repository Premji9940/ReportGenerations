package com.nit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CitizenPlan {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String email;
	private Long mobile;
	private Long ssn;
	private String gender;
	private String plansName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	
	
}
