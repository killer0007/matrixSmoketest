package maintest2;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

public class temp2 {

	static public void main(String[] args) throws Exception {
		System.out.println(getcurrentdate());

	}

	public static String getcurrentdate() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date currentMonth = new Date();
		return df.format(currentMonth).toString();

	}

}
