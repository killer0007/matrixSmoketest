package environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection {
	public static void main(String[] args)throws Exception {
		String pass =GetPassword("demotl");
		System.out.println(pass);
	}
public static String GetPassword(String uname) throws Exception{
	
		String pass=null;
		final String url="jdbc:sqlserver://192.168.2.16:1433;"+"databaseName=FinalMatrixTestingDB"+"";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		Connection conn = DriverManager.getConnection(url, "Sa", "Sql@123");	
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
		return pass;
	
}
}
