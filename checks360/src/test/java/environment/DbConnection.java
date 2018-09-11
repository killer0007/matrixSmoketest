package environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DbConnection {
	
	public static void main(String[] args)throws Exception {
//		List<String> data =getcontractdetails("demo automation");
//		for (int i = 0; i < data.size(); i++) {
//			System.out.println(data.get(i).toString());
//		}
	}
public static String GetlocalPassword(String uname) throws Exception{
	
		String pass=null;
		final String url="jdbc:sqlserver://192.168.2.17:1433;"+"databaseName=FP_Checks360_V2.1s_Testing"+"";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		Connection conn = DriverManager.getConnection(url, "Sa", "Sql@123");	
//		String query="select  componentshortname from tblcontractdetails where contractid=10413";
			String query = "OPEN Symmetric KEy Mat_Key  DECRYPTION By Certificate Matrix_cert\r\n" + "   \r\n"
					+ "select User_name,convert(varchar(50),decryptbykey(User_password)),User_id,User_Userinfoid from tbl_master_webusers where User_name='"+uname+"'\r\n"
					+ "\r\n" + "close Symmetric KEy Mat_Key";
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			//System.out.println(rs);
			while (rs.next()) {
				//String us = rs.getString("User_name");
				pass = rs.getString("");
				//System.out.println(us + "  " + pass);
				
			}
			System.out.println("password is :"+pass);
		return pass;
	
}
public List<String> getcontractdetails(String contractname) throws Exception {
	List<String> result = new ArrayList<String>();
	String id=null;
	final String url="jdbc:sqlserver://192.168.2.17:1433;"+"databaseName=FP_Checks360_V2.1s_Testing"+"";
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	Connection conn = DriverManager.getConnection(url, "Sa", "Sql@123");
//	String idquery="select id from tblClientContractMaster where shortname='demo automation'";
	String idquery="select componentshortname from tblClientContractMaster c ,tblcontractdetails m where c.ShortName='"+contractname+"' and c.id=m.contractid";
	Statement st = conn.createStatement();
	ResultSet rs = st.executeQuery(idquery);
	while(rs.next()) {
		id = rs.getString("componentshortname");
		result.add(id);
	}
	conn.close();
	return result;
}

}
