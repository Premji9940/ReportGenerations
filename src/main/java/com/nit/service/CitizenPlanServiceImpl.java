package com.nit.service;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nit.entity.CitizenPlan;
import com.nit.entity.SearchCriteria;
import com.nit.repo.CitizenPlanRepo;
import com.nit.utils.EmailUtils;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {

	@Autowired private CitizenPlanRepo repo;
	@Autowired private EmailUtils utils;
	
	@Override
	public List<String> getPlanNames() {
		// TODO Auto-generated method stub
		return repo.getUniquePlanNames(); //repo.getUniquePlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		// TODO Auto-generated method stub
		return repo.getUniquePlanStatus() ;//repo.getUniquePlanStatus();
	}

/*	@Override
	public List<CitizenPlan> getCitizenInfo(SearchCriteria criteria) {
		if(criteria.getPlanName().isEmpty() && criteria.getPlanStatus().isEmpty()&& criteria.getGender().isEmpty()&&criteria.getStartDate()==null&&criteria.getEndDate()==null)
		{System.out.println("CitizenPlanServiceImpl.getCitizenInfo()1");
			
			
			return repo.findAll();
		} else 		if(criteria.getPlanName()!=null && criteria.getPlanStatus().isEmpty() && criteria.getGender().isEmpty() &&criteria.getStartDate()==null&&criteria.getEndDate()==null)
		{
			System.out.println("getring element by plannames");
			return repo.searchByPlanNames(criteria.getPlanName());
		} else if( criteria.getPlanStatus()!=null &&criteria.getPlanName().isEmpty() && criteria.getGender().isEmpty() && criteria.getStartDate()==null &&criteria.getEndDate()==null)
		{
			System.out.println("getring element by planStatus");

			return repo.searchByPlanStatus(criteria.getPlanStatus());
		} else if(criteria.getGender()!=null &&  criteria.getPlanStatus().isEmpty()&&criteria.getPlanName().isEmpty() && criteria.getStartDate()==null &&criteria.getEndDate()==null)
	{
			System.out.println("getring element by Gender");

		return repo.searchByGender(criteria.getGender());
	} else if(criteria.getGender().isEmpty() &&  criteria.getPlanStatus().isEmpty()&&criteria.getPlanName().isEmpty() && criteria.getStartDate()!=null &&criteria.getEndDate()==null)
	{
		System.out.println("getring element by startDate");

		return repo.searchByStartDate(criteria.getStartDate());
	} else if(criteria.getGender().isEmpty() &&  criteria.getPlanStatus().isEmpty()&&criteria.getPlanName().isEmpty() && criteria.getStartDate()==null &&criteria.getEndDate()!=null)
	{
		System.out.println("getring element by endDate");

		return repo.searchByEndDate(criteria.getEndDate());
	} else if(criteria.getGender()!=null &&  criteria.getPlanStatus()!=null&&criteria.getPlanName()!=null && criteria.getStartDate()!=null &&criteria.getEndDate()!=null)
	{
		System.out.println("getting by all fields");
		return repo.searchByAll(criteria.getPlanName(), criteria.getPlanStatus(), criteria.getGender(), criteria.getStartDate(), criteria.getEndDate());
	}	 else if(criteria.getGender().isEmpty() &&  criteria.getPlanStatus()!=null&&criteria.getPlanName()!=null && criteria.getStartDate()==null &&criteria.getEndDate()==null)
	{
		System.out.println("getring element by plannames and status");

		return repo.searchByPlansNameAndPlanStatus(criteria.getPlanName(),  criteria.getPlanStatus());
	} else if(criteria.getGender()!=null &&  criteria.getPlanStatus()!=null&&!criteria.getPlanName().isBlank() && criteria.getStartDate()==null &&criteria.getEndDate()==null)
	{
		System.out.println("getring element by plannames and gender and status=>"+criteria.getGender()+"\t"+criteria.getPlanStatus()+"\t"+criteria.getPlanName()+"=>"+!criteria.getPlanName().isBlank());

		return repo.searchByPlansNameAndPlanStatusAndGender(criteria.getPlanName(),  criteria.getPlanStatus(),criteria.getGender());
	}else if(criteria.getGender()!=null &&  criteria.getPlanStatus()!=null&&criteria.getPlanName()!=null && criteria.getStartDate()!=null &&criteria.getEndDate()==null)
		{
		System.out.println("getring element by plannames and status,gender,start date");

			return repo. searchByPlansNameAndPlanStatusAndGenderAndStartDate(criteria.getPlanName(),  criteria.getPlanStatus(),criteria.getGender(),criteria.getStartDate());
	}
		if(criteria.getGender()!=null &&  criteria.getPlanStatus()!=null&&criteria.getPlanName().isEmpty() && criteria.getStartDate()==null &&criteria.getEndDate()==null)
		{
			System.out.println("getring element by planstatus and gender");

		//To Retrive Element based on plan status and gender
			return repo.searchByStatusAndGender
					(criteria.getPlanStatus(),criteria.getGender());
		}					
				
		
		return repo.findAll();
	
	}*/
	//This above code is very complex to filter the data to over come this proble  we woriking with Example Objbect
	
	@Override
	public List<CitizenPlan> getCitizenInfo(SearchCriteria criteria) {
		CitizenPlan plan=new CitizenPlan();
		if(StringUtil.isNotBlank(criteria.getPlanName())) {
			//here we injecting plan name to entity object because with SearchCriteria we cann't do curd  operations becoz it is no enity object
			plan.setPlansName(criteria.getPlanName());
			
		}
		if(StringUtil.isNotBlank(criteria.getPlanStatus())){
			plan.setPlanStatus(criteria.getPlanStatus());

		}
		
		if(StringUtil.isNotBlank(criteria.getGender())) {
			plan.setGender(criteria.getGender());
	
		}
		
		if(criteria.getStartDate()!=null) {
			plan.setPlanStartDate(criteria.getStartDate());
		}
		if(criteria.getEndDate()!=null) {
			plan.setPlanEndDate(criteria.getEndDate());
		}
		System.out.println(plan);
		Example<CitizenPlan> ex=Example.of(plan);
		return repo.findAll(ex);
	}
	@Override
	public void generateExcel(HttpServletResponse response) throws Exception{
	//creating Work book For creating sheets
		HSSFWorkbook book=new HSSFWorkbook();
		
		//creating sheet to write data in rows and columns
		HSSFSheet sheet = book.createSheet("user Data");
		
		//creating header row in the sheet ,Row index and column index must starts with Zero index
		HSSFRow headerRow = sheet.createRow(0);
	
		//creating cell styles
		CellStyle cellstyle=book.createCellStyle();
		cellstyle.setBorderTop(BorderStyle.MEDIUM);
		cellstyle.setBorderLeft(BorderStyle.MEDIUM);
		cellstyle.setBorderRight(BorderStyle.MEDIUM);
		cellstyle.setBorderBottom(BorderStyle.MEDIUM);
		cellstyle.setAlignment(HorizontalAlignment.CENTER);
		
		//It is only for header rows
		HSSFFont f=book.createFont();
		//setting font size 
		f.setBold(true);
		//adding font to cells
		cellstyle.setFont(f);
		
	
		String columns[]= {"S.NO","Name",	"Plan",	"Status","Gender","Mobil","SSN"};
		//creating header row for sheet
		createWorkBookHeaderRow(0, columns,headerRow,cellstyle);
		//getting all citizens from data base
		List<CitizenPlan> citizens=repo.findAll();
		
		//creating font for data row
		HSSFFont dataFont = book.createFont();
		//setting font size
		dataFont.setBold(false);
		cellstyle.setFont(dataFont);
		
		//creating data row for sheet
		createDataRowForWorkBook(sheet, cellstyle, citizens);
		/*
		//creating cells for header row
		HSSFCell c1=headerRow.createCell(0);
		c1.setCellValue("Sno");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("PlanStatus");
		headerRow.createCell(4).setCellValue("gender");
		headerRow.createCell(5).setCellValue("mobile");
		headerRow.createCell(6).setCellValue("ssn");
		
		//creating data rows
		List<CitizenPlan> data=repo.findAll();
		//index starts with zero but aleready we create header value with zero so we have to start with index 1
		int index=1;
		for(CitizenPlan user:data) {
			//creating data rows
			HSSFRow headerRow1 = sheet.createRow(index);
			headerRow1.createCell(0).setCellValue(user.getId());
			headerRow1.createCell(1).setCellValue(user.getName());
			headerRow1.createCell(2).setCellValue(user.getPlansName());
			headerRow1.createCell(3).setCellValue(user.getPlanStatus());
			headerRow1.createCell(4).setCellValue(user.getGender());
			headerRow1.createCell(5).setCellValue(user.getMobile());
			headerRow1.createCell(6).setCellValue(user.getSsn());
			index++;
		}
		*/
		//getting output stream from response object
		ServletOutputStream outputStream = response.getOutputStream();
		//writing work book to out put stream to display on browser
		book.write(outputStream);
		
		//closing work book
		book.close();
		//closing output stream
		outputStream.close();
	
		
		
		
	}
	//creating work book header row
	public void createWorkBookHeaderRow(Integer index,String column[],HSSFRow headerRow,CellStyle cellstyle) {
	System.out.println("CitizenPlanServiceImpl.createWorkBookHeaderRow()");

		int i=0;
		for(String col:column) {
			HSSFCell c=headerRow.createCell(i);
			c.setCellValue(column[i]);
			c.setCellStyle(cellstyle);
			i++;
			
			
			
		}
		
	}
	//creating data row for work book	
	public void createDataRowForWorkBook(HSSFSheet sheet,CellStyle cellstyle,List<CitizenPlan> citizens) {
		
		int rowIndex=1;
		for(CitizenPlan citizen:citizens) {
			//creating data row
			HSSFRow row=sheet.createRow(rowIndex);
			//creating cell1 
			HSSFCell cell1=row.createCell(0);
			//adding value to cell1
			cell1.setCellValue(citizen.getId());
			//adding style to cell1
			cell1.setCellStyle(cellstyle);
			//creating cell2
			HSSFCell cell2=row.createCell(1);
			//adding value to cell2
			cell2.setCellValue(citizen.getName());
			//adding style to cell2
			cell2.setCellStyle(cellstyle);
			//creating cell3
			HSSFCell cell3=row.createCell(2);
			cell3.setCellValue(citizen.getPlansName());
			//adding style to cell3
			cell3.setCellStyle(cellstyle);
			//creating cell4
			HSSFCell cell4=row.createCell(3);
			cell4.setCellValue(citizen.getPlanStatus());
			//adding style to cell4
			cell4.setCellStyle(cellstyle);
			//creating cell5
			HSSFCell cell5=row.createCell(4);
			//adding data to cell5
			cell5.setCellValue(citizen.getGender());
			//setting style to cell5
			cell5.setCellStyle(cellstyle);
			//creating cell6
			HSSFCell cell6=row.createCell(5);
			//adding data to cell6
			cell6.setCellValue(citizen.getMobile());
			//setting style to cell6
			cell6.setCellStyle(cellstyle);
			//creating cell7
			HSSFCell cell7=row.createCell(6);
			//adding data to cell7
			cell7.setCellValue(citizen.getSsn());	
			//setting style to cell7
			cell7.setCellStyle(cellstyle);
			//increamenting row count
			rowIndex++;
		}
		
	}
	

	@Override
	public void generatePdf(HttpServletResponse response) throws Exception {
		//Getting Output stream
		ServletOutputStream outputStream = response.getOutputStream();
		Document doc=new Document();

		PdfWriter.getInstance(doc, outputStream);	
		doc.open();
		Font font=FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(Color.CYAN);
		
		//Adding Title 
		Paragraph p=new Paragraph("Report For Citizens");
		p.setFont(font);
		
		p.setAlignment(Element.ALIGN_CENTER);
		p.add(new Paragraph(""));
		p.add(new Paragraph(""));
		doc.add(p);
		
		//creating Table
		PdfPTable table=new PdfPTable(7);
		table.setWidthPercentage(100);
		 Font f=FontFactory.getFont(FontFactory.TIMES_BOLD);
		 f.setSize(12);
		 f.setColor(Color.BLACK);
		table.setWidths(new int[] {2,3,2,3,2,3,4});
		//creating header
		PdfPCell cell1=new PdfPCell();
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setVerticalAlignment(Element.ALIGN_CENTER);
		cell1.setBackgroundColor(Color.YELLOW);
		cell1.setPadding(5);
		cell1.setPhrase(new Phrase("sno",f));
		table.addCell(cell1);
		cell1.setPhrase(new Phrase("Name",f));
		table.addCell(cell1);
		cell1.setPhrase(new Phrase("Plan Names",f));
		table.addCell(cell1);
		cell1.setPhrase(new Phrase("Plan Status",f));
		table.addCell(cell1);
		cell1.setPhrase(new Phrase("Gender",f));
		table.addCell(cell1);
		cell1.setPhrase(new Phrase("Mobile",f));
		table.addCell(cell1);
		cell1.setPhrase(new Phrase("SSN",f));
		table.addCell(cell1);
		
		//creating data rows
		List<CitizenPlan> plan=repo.findAll();
		
		for(CitizenPlan c:plan) {
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setPadding(5);
			table.addCell(String.valueOf(c.getId()));
			table.addCell(c.getName());
			table.addCell(c.getPlansName());
			table.addCell(c.getPlanStatus());
			table.addCell(c.getGender());
			table.addCell(String.valueOf(c.getMobile()));
			table.addCell(String.valueOf(c.getSsn()));
			
		}
	
		doc.add(table);
		doc.addCreationDate();
		doc.addCreator("prem kumar kalla");
		
		
		doc.close();
		
		
		
		
		
	
		
		
		
	}

	@Override
	public String sendingPdfToEmail() throws DocumentException, IOException {
		//creating Work book For creating sheets
				HSSFWorkbook book=new HSSFWorkbook();
				
				//creating sheet to write data in rows and columns
				HSSFSheet sheet = book.createSheet("user Data");
				
				//creating header row in the sheet ,Row index and column index must starts with Zero index
				HSSFRow headerRow = sheet.createRow(0);
			
				//creating cell styles
				CellStyle cellstyle=book.createCellStyle();
				cellstyle.setBorderTop(BorderStyle.MEDIUM);
				cellstyle.setBorderLeft(BorderStyle.MEDIUM);
				cellstyle.setBorderRight(BorderStyle.MEDIUM);
				cellstyle.setBorderBottom(BorderStyle.MEDIUM);
				cellstyle.setAlignment(HorizontalAlignment.CENTER);
				
				//It is only for header rows
				HSSFFont f=book.createFont();
				//setting font size 
				f.setBold(true);
				//adding font to cells
				cellstyle.setFont(f);
				
			
				String columns[]= {"S.NO","Name",	"Plan",	"Status","Gender","Mobil","SSN"};
				//creating header row for sheet
				createWorkBookHeaderRow(0, columns,headerRow,cellstyle);
				//getting all citizens from data base
				List<CitizenPlan> citizens=repo.findAll();
				
				//creating font for data row
				HSSFFont dataFont = book.createFont();
				//setting font size
				dataFont.setBold(false);
				cellstyle.setFont(dataFont);
				
				//creating data row for sheet
				createDataRowForWorkBook(sheet, cellstyle, citizens);
				
				File f1=new File("data.xls");
				FileOutputStream fos=new FileOutputStream(f1);
				book.write(fos);
				utils.sendEmail(f1);
					
				
				
				//closing work book
				book.close();
				fos.close();
				//closing output stream
			
				
				
				
	
	
	return null;}

	
public void sendPdftoEmailasAttachment() throws IOException {
	
			Document doc=new Document();
			
			File file=new File("download.pdf");
			FileOutputStream fos=new FileOutputStream(file.getName());

		
		      PdfWriter.getInstance(doc,fos );
			

			
		
			doc.open();
			Font font=FontFactory.getFont(FontFactory.TIMES_ROMAN);
			font.setColor(Color.CYAN);
			
			//Adding Title 
			Paragraph p=new Paragraph("Report For Citizens");
			p.setFont(font);
			
			p.setAlignment(Element.ALIGN_CENTER);
			p.add(new Paragraph(""));
			p.add(new Paragraph(""));
			doc.add(p);
			
			//creating Table
			PdfPTable table=new PdfPTable(7);
			table.setWidthPercentage(100);
			 Font f=FontFactory.getFont(FontFactory.TIMES_BOLD);
			 f.setSize(12);
			 f.setColor(Color.BLACK);
			table.setWidths(new int[] {2,3,2,3,2,3,4});
			//creating header
			PdfPCell cell1=new PdfPCell();
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setVerticalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.YELLOW);
			cell1.setPadding(5);
			cell1.setPhrase(new Phrase("sno",f));
			table.addCell(cell1);
			cell1.setPhrase(new Phrase("Name",f));
			table.addCell(cell1);
			cell1.setPhrase(new Phrase("Plan Names",f));
			table.addCell(cell1);
			cell1.setPhrase(new Phrase("Plan Status",f));
			table.addCell(cell1);
			cell1.setPhrase(new Phrase("Gender",f));
			table.addCell(cell1);
			cell1.setPhrase(new Phrase("Mobile",f));
			table.addCell(cell1);
			cell1.setPhrase(new Phrase("SSN",f));
			table.addCell(cell1);
			
			//creating data rows
			List<CitizenPlan> plan=repo.findAll();
			
			for(CitizenPlan c:plan) {
				table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
				table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				table.getDefaultCell().setPadding(5);
				table.addCell(String.valueOf(c.getId()));
				table.addCell(c.getName());
				table.addCell(c.getPlansName());
				table.addCell(c.getPlanStatus());
				table.addCell(c.getGender());
				table.addCell(String.valueOf(c.getMobile()));
				table.addCell(String.valueOf(c.getSsn()));
				
			}
		
			doc.add(table);
			doc.addCreationDate();
			doc.addCreator("prem kumar kalla");
			


			doc.close();
			
			System.out.println(utils.sendEmail(file));

			
			
			

}
}
