package com.nit.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;

import com.lowagie.text.DocumentException;
import com.nit.entity.CitizenPlan;
import com.nit.entity.SearchCriteria;

import jakarta.servlet.http.HttpServletResponse;

public interface CitizenPlanService {

	public List<String> getPlanNames();
	public List<String> getPlanStatus();
	public List<CitizenPlan> getCitizenInfo(SearchCriteria criteria);
	public void generateExcel(HttpServletResponse response) throws Exception;
	public void generatePdf(HttpServletResponse response)throws Exception;
	public String sendingPdfToEmail() throws DocumentException, FileNotFoundException, IOException;
	public void sendPdftoEmailasAttachment() throws FileNotFoundException, IOException;
}
