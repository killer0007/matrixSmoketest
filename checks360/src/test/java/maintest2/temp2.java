package maintest2;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
public class temp2 {
//	 private static String FILE = "D:\\gopi\\msbuild\\FirstPdf.pdf";
	    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 100,
	            Font.BOLD);
	   

	    public static void main(String[] args) {
	    	
	     java.util.List<String> data = new ArrayList<String>();
	     data.add("address");
	     data.add("employment");
	     data.add("education");
	     data.add("criminal");
	     data.add("reference");
	     data.add("id");
	     data.add("database");
	     data.add("credit");
	     data.add("court");
	        	for (int i = 0; i < data.size(); i++) {
	        		   try {
	        			   String FILE = "D:\\gopi\\checks360\\insuff\\"+data.get(i).toString()+".pdf";
	        		 Document document = new Document();
	 	            PdfWriter.getInstance(document, new FileOutputStream(FILE));
	 	            document.open();
	 	          
	 	            addTitlePage(document,data.get(i).toString());
	 	            document.close();
	 	        } catch (Exception e) {
	 	            e.printStackTrace();
	 	        }
				}
	           
	    }

	    // iText allows to add metadata to the PDF which can be viewed in your Adobe
	    // Reader
	    // under File -> Properties
	   

	    private static void addTitlePage(Document document, String data)
	            throws DocumentException {
	        Paragraph preface = new Paragraph();
	        // We add one empty line
	        addEmptyLine(preface, 5);
	       
	        preface.add(new Paragraph(data +" insuff ", catFont));
	        preface.setAlignment(Element.ALIGN_CENTER);

	

	        document.add(preface);
	        // Start a new page
	        document.newPage();
	    }

	    private static void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	    }
}
