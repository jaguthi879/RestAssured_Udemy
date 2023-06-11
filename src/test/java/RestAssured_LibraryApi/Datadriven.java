package RestAssured_LibraryApi;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Datadriven {
	
	//Identify Testcases column by scanning the entire 1st row
   //once column is identified then scan entire testcase column to identify purchase testcase row
	//after you grab purchase testcase row = pull all the data of that row and feed into test
	  
	public ArrayList<String> getdata(String testcasename) throws IOException {
		
		ArrayList<String> a=new ArrayList<String>();
		FileInputStream fis=new FileInputStream("C:\\Users\\237529\\Documents\\Demodata.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		 int sheets=workbook.getNumberOfSheets();
		 for(int i=0;i<sheets;i++) {
			 
			 if(workbook.getSheetName(i).equalsIgnoreCase("RESTapi")) {
				 
			 XSSFSheet sheet=workbook.getSheetAt(i);
			 Iterator<Row> rows=sheet.iterator();
			 Row firstrow=rows.next();
			 Iterator<Cell> ce=firstrow.cellIterator();
			 int k=0;
			 int column=0;
			 while(ce.hasNext()) {
				 Cell value=ce.next();
				 if(value.getStringCellValue().equals("Testcases"))
				 {
					 column=k;
				 }					 
			    k++;
			 }
		System.out.println(column);
	
			 while(rows.hasNext()) {
				 
				 Row r=rows.next();
				 if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename)) {
					 
				   Iterator<Cell>cv=r.cellIterator();
				   while(cv.hasNext()) {
					 a.add(cv.next().getStringCellValue());				  
			 }
	}
			 }			
	}
	}
		return a;
	}
}




			 
 
	
			 
			


