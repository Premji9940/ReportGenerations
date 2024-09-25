package com.nit.runner;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nit.entity.CitizenPlan;
import com.nit.repo.CitizenPlanRepo;
@Component
public class DataLoader implements CommandLineRunner {
	@Autowired CitizenPlanRepo repo;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("DataLoader.run()");
		repo.deleteAll();
		
		CitizenPlan c1=new CitizenPlan(1, "Prem Kumar", "premkumar@gmail.com", 9133889940l, 962574991808l,"male","cash","approved",LocalDate.now(), LocalDate.now().plusMonths(6));		
		CitizenPlan c2=new CitizenPlan(2, "sudhakar","sudhakar@gmail.com", 9392150021l, 96257441807l, "male", "food", "denied", null, null);
		
		CitizenPlan c3=new CitizenPlan(1, "vijii", "vijji@gmail.com", 6281388143l, 962574991808l,"male","cash","approved",LocalDate.now(), LocalDate.now().plusMonths(6));		
		CitizenPlan c4=new CitizenPlan(2, "kishore","kishore@gmail.com", 7569554553l, 96257441807l, "male", "food", "denied", null, null);
		
		CitizenPlan c5=new CitizenPlan(1, "naganna", "naganna@gmail.com", 8978938478l, 962574991808l,"male","medical","approved",LocalDate.now(), LocalDate.now().plusMonths(6));		
		CitizenPlan c6=new CitizenPlan(2, "srikanth","srikanth@gmail.com", 7569332334l, 96257441807l, "male", "food", "approved", LocalDate.now(), LocalDate.now().plusYears(1));
		CitizenPlan c7=new CitizenPlan(2, "kajol","kajol@gmail.com", 7569332334l, 96257441807l, "female", "medical", "approved", LocalDate.now(), LocalDate.now().plusMonths(9));
		CitizenPlan c8=new CitizenPlan(2, "tamanna","tamanna@gmail.com", 7569332334l, 96257441807l, "female", "cash", "denied", null, null);
	
		List<CitizenPlan> citizen=List.of(c1,c2,c3,c4,c5,c6,c7,c8);
		repo.saveAll(citizen);
		System.out.println("=============================files are insertred==============================");
	}

}
