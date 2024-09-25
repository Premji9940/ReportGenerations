package com.nit.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nit.entity.CitizenPlan;

public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer> {

	//To retrive all unique planNames
	@Query("select distinct(plansName) from CitizenPlan")
	public List<String> getUniquePlanNames();
	//To retrieve all unique planStatus
	@Query("select distinct(planStatus) from CitizenPlan")
	public List<String> getUniquePlanStatus();
	
/*	
	@Query(" from CitizenPlan where plansName=:planname")
	public List<CitizenPlan> searchByPlanNames(String planname);
	
	@Query(" from CitizenPlan where planStatus=:planstatus")
	public List<CitizenPlan> searchByPlanStatus(String planstatus);
	@Query(" from CitizenPlan where gender=:gender")
	public List<CitizenPlan> searchByGender(String gender);
	
	@Query(" from CitizenPlan where planStartDate=:startDate")
	public List<CitizenPlan> searchByStartDate(LocalDate startDate);
	
	//To get below end date values
//	@Query(" from CitizenPlan where planEndDate <= :endDate")
	//To get particular end date details
	@Query(" from CitizenPlan where planEndDate = :endDate")
	public List<CitizenPlan> searchByEndDate(LocalDate endDate);

	

	@Query(" from CitizenPlan where planStartDate=:startDate and planEndDate=:endDate")
	public List<CitizenPlan> searchByStartDateAndEndDate(LocalDate startDate,LocalDate endDate);

	@Query(" from CitizenPlan where plansName=:planName and planStatus=:planStatus and gender=:gender and  planStartDate=:startDate and planEndDate=:endDate")
	public List<CitizenPlan> searchByAll(String planName,String planStatus,String gender ,LocalDate startDate,LocalDate endDate);

	//To get records based on planname,planstatus
	@Query(" from CitizenPlan where plansName=:plan and planStatus=:status")
	public List<CitizenPlan> searchByPlansNameAndPlanStatus(String plan,String status);
	//To get records based on planname,planstatus,gender
	@Query(" from CitizenPlan where plansName=:plan and planStatus=:status and gender=:gender")
	public List<CitizenPlan> searchByPlansNameAndPlanStatusAndGender(String plan,String status,String gender);

	//To get records based on planname,planstatus,gender,startDate
		@Query(" from CitizenPlan where plansName=:plan and planStatus=:status and gender=:gender and planStartDate=:startDate")
		public List<CitizenPlan> searchByPlansNameAndPlanStatusAndGenderAndStartDate(String plan,String status,String gender,LocalDate startDate);

		//To get records based on planstatus,gender,
			@Query(" from CitizenPlan where planStatus=:status and gender=:gender")
			public List<CitizenPlan> searchByStatusAndGender(String status,String gender);

	*/
	}
