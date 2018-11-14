package maintest2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class temp2 {

	public static void main(String[] args) throws Exception{
		 String RNO=JOptionPane.showInputDialog("Enter the Reference No:");
		  String WF=JOptionPane.showInputDialog("Enter the Workflow No (1.Candidate 2.Client 3.Employee  4.Bulk):");  
		  System.out.println(RNO);
		  System.out.println(WF);
		  
	}

}
