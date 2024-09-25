package com.nit.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lowagie.text.DocumentException;
import com.nit.entity.EmailDetails;
import com.nit.entity.SearchCriteria;
import com.nit.service.CitizenPlanService;
import com.nit.service.EmailService;
import com.nit.service.EmailServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CitizenPlanController {
	
	@Autowired private CitizenPlanService service;
	
	@Autowired private JavaMailSender javaMailSender;

	public void loadingIndex(Model m) {
		//loading planNames in the plan names dropdown 
		m.addAttribute("planNames", service.getPlanNames());
		//loading planStatus in the planStatus drop down
		m.addAttribute("planStatus", service.getPlanStatus());
		m.addAttribute("data", "");	
	}
	@GetMapping("/")
	public String index(@ModelAttribute("search") SearchCriteria criteria,Model m) {
		System.out.println("CitizenPlanController.index()"+service.getPlanNames());
		//this method loads all drop downs 
		loadingIndex(m);
			return "home";
	}
	@PostMapping("/search")
	public String search(@ModelAttribute("search") SearchCriteria criteria,Model model) {
		System.out.println(criteria);
		loadingIndex(model);
		model.addAttribute("data", service.getCitizenInfo(criteria));
	//	model.addAttribute("data", "");

		return "home";

	}
	@GetMapping("/excel")
	public void downloadExcel(HttpServletResponse res) throws Exception {
		System.out.println("java mail"+javaMailSender);
		//System.out.println(MediaType.APPLICATION_OCTET_STREAM+"\t"+MediaType.APPLICATION_OCTET_STREAM_VALUE);
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String date=sd.format(new Date());
		System.out.println(date);
		
		String headerName="Content-Disposition";
		String headerValue="attachment;filename=Citizen_"+date+".xls";
		res.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

		res.setHeader(headerName, headerValue);

		service.generateExcel(res);
		
	}
	@GetMapping("/pdf")	
	public void downloadPdf(HttpServletResponse res) throws Exception {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String date=sd.format(new Date());
		
		String headerName="Content-Disposition";
		String headerValue="attachment;filename=Citizen_"+date+".pdf";
		res.setContentType(MediaType.APPLICATION_PDF_VALUE);
		res.setHeader(headerName, headerValue);
		service.generatePdf(res);
			
	}
	@GetMapping("/mail")
	public String sendToEmail(@ModelAttribute("search") SearchCriteria criteria,Model m) throws DocumentException, IOException {
		
		
		String file_name=new Random().nextInt(10000, 50000)+".pdf";
		//service.sendingPdfToEmail();
		service. sendPdftoEmailasAttachment();
		loadingIndex(m);
		

		return "home";
		
		
	}

}
