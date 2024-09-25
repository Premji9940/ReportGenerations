package com.nit.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
	
	private String planName;
	private String planStatus;
	private String gender;
	private LocalDate startDate;
	private LocalDate endDate;
	
	

}
