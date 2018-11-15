package environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbConnection  { 
	public static void main(String[] args) throws Exception{
		DbConnection d = new DbConnection();
		System.out.println(d.getLastCase("demo client1234"));
	}
	/**
	 * Takes contract name as input and return list if sub components in that
	 * contract
	 * 
	 * @param contractname contract name
	 * @return List of sub component
	 * @throws Exception when SQL connection failed
	 */ 
	  
	public synchronized List<String> getcontractdetails(String contractname) throws Exception {
		List<String> result = new ArrayList<String>();
		String id = null;
		final String url = "jdbc:sqlserver://192.168.2.17:1433;" + "databaseName=FP_Checks360_V2.1s_Testing" + "";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		Connection conn = DriverManager.getConnection(url, "Sa", "Sql@123");
		String idquery = "select componentshortname from tblClientContractMaster c ,tblcontractdetails m where c.ShortName='"
				+ contractname + "' and c.id=m.contractid";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(idquery);
		while (rs.next()) {
			id = rs.getString("componentshortname");
			result.add(id);
		}
		conn.close();
		return result;
	}

	/**
	 * Takes project name as input and returns most recent reference number
	 * 
	 * @param project Project Name
	 * @return refno case reference number
	 * @throws Exception when SQL connection failed
	 */
	public  String getLastrefno(String project) throws Exception {
//	select top 1 refno from tblcasemaster where projectshortname='demo client1234' order by id desc
		String result = null; 
		final String url = "jdbc:sqlserver://192.168.2.17:1433;" + "databaseName=FP_Checks360_V2.1s_Testing" + "";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		Connection conn = DriverManager.getConnection(url, "Sa", "Sql@123");
		String idquery = "select top 1 refno from tblcasemaster where projectshortname='" + project
				+ "' order by id desc";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(idquery);
		while (rs.next()) {
			result = rs.getString("refno");
		}
		conn.close();
		return result;
	}

	/**
	 * Takes project name as input and return hashmap which contains most recent
	 * case information like first name, last name and doc
	 * firstname, lastname, DateofBirth
	 * @param project Project Name
	 * @return hashmap first name, lastname, dob
	 * @throws Exception when SQL connection failed
	 */
	public synchronized HashMap<String, String> getLastCase(String project) throws Exception {
//	select top 1 firstname, lastname, DateofBirth from tblcasemaster where projectshortname='demo client1234' order by id desc
		HashMap<String, String> data = new HashMap<String, String>();
		final String url = "jdbc:sqlserver://192.168.2.17:1433;" + "databaseName=FP_Checks360_V2.1s_Testing" + "";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		Connection conn = DriverManager.getConnection(url, "Sa", "Sql@123");
		String idquery = "select top 1 firstname, lastname, DateofBirth from tblcasemaster where projectshortname='"
				+ project + "' order by id desc";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(idquery);
		while (rs.next()) {
			data.put("firstname", rs.getString("firstname"));
			data.put("lastname", rs.getString("lastname"));
			data.put("DateofBirth", rs.getString("DateofBirth"));
		}
		conn.close();
		return data;
	}

	/**
	 * Returns count of case already assigned to demoempl
	 * 
	 * @return count assigned case count
	 * @throws Exception when SQL connection failed
	 */
	public synchronized int getAssignedCount(String name) throws Exception {
		String re = null;
		final String url = "jdbc:sqlserver://192.168.2.17:1433;" + "databaseName=FP_Checks360_V2.1s_Testing" + "";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		Connection conn = DriverManager.getConnection(url, "Sa", "Sql@123");
		Statement st = conn.createStatement();
		//select count(distinct(caseid)) as count from tblCaseComponents where assignedto=(select id  from tblemployeemaster where LDAPUserName='demoempl' and serviceprovidershortname!='Null') and status in (3,15,20,21,39,40,152,158,159,166,173,174) and deleted=0 and NotRequired=0
		//String query = "select count(distinct(caseid)) as count from tblCaseComponents where assignedto=2270 and status in (3,15,20,21,39,40,152,158,159,166,173,174) and deleted=0 and NotRequired=0";
		String query="select count(distinct(caseid)) as count from tblCaseComponents where assignedto=(select id  from tblemployeemaster where LDAPUserName='"+name+"' and serviceprovidershortname!='Null') and status in (3,15,20,21,39,40,152,158,159,166,173,174) and deleted=0 and NotRequired=0";
		ResultSet out = st.executeQuery(query);
		while (out.next()) {
			re = out.getString("count");
		}
		conn.close();
		//System.out.println(re);
		return Integer.parseInt(re);

	}

}
