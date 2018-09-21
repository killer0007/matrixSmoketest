package environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbConnection2 {
	Connection conn = null;

	public DbConnection2() throws Exception {
		final String url = "jdbc:sqlserver://192.168.2.17:1433;" + "databaseName=FP_Checks360_V2.1s_Testing" + "";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		conn = DriverManager.getConnection(url, "Sa", "Sql@123");
	}

	public static void main(String[] args) throws Exception {
		DbConnection2 db = new DbConnection2();
		db.getAssignedCount();
	}

	public int getAssignedCount() throws Exception {
		String re = null;
		Statement st = conn.createStatement();
		//select count(distinct(caseid)) as count from tblCaseComponents where assignedto=(select id from tblemployeemaster where ldapusername='demoempl') and status=3
		String query = "select count(distinct(caseid)) as count from tblCaseComponents where assignedto=2270 and status in (3,15,20,21,39,40,152,158,159,166,173,174) and deleted=0 and NotRequired=0";
		ResultSet out = st.executeQuery(query);
		while (out.next()) {
			re = out.getString("count");
		}
		conn.close();
		System.out.println(re);
		return Integer.parseInt(re);

	}

}
