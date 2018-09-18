package environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class DbConnection {
	
	public static void main(String[] args)throws Exception {	
		DbConnection db = new DbConnection();
		HashMap<String, String> data =db.getLastCase("demo client1234");
		System.out.println(data.size());
		
			System.out.println(data.get("firstname"));
			System.out.println(data.get("lastname"));
			System.out.println(data.get("DateofBirth"));
		
	}
public List<String> getcontractdetails(String contractname) throws Exception {
	List<String> result = new ArrayList<String>();
	String id=null;
	final String url="jdbc:sqlserver://192.168.2.17:1433;"+"databaseName=FP_Checks360_V2.1s_Testing"+"";
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	Connection conn = DriverManager.getConnection(url, "Sa", "Sql@123");
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
public String getLastrefno(String project) throws Exception {
//	select top 1 refno from tblcasemaster where projectshortname='demo client1234' order by id desc
	String result=null;
	final String url="jdbc:sqlserver://192.168.2.17:1433;"+"databaseName=FP_Checks360_V2.1s_Testing"+"";
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	Connection conn = DriverManager.getConnection(url, "Sa", "Sql@123");
	String idquery="select top 1 refno from tblcasemaster where projectshortname='"+project+"' order by id desc";
	Statement st = conn.createStatement();
	ResultSet rs = st.executeQuery(idquery);
	while(rs.next()) {
		result = rs.getString("refno");	
	}
	conn.close();
	return result;
}
public HashMap<String, String> getLastCase(String project) throws Exception {
//	select top 1 firstname, lastname, DateofBirth from tblcasemaster where projectshortname='demo client1234' order by id desc
	HashMap<String, String> data =new HashMap<String, String>();
	final String url="jdbc:sqlserver://192.168.2.17:1433;"+"databaseName=FP_Checks360_V2.1s_Testing"+"";
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	Connection conn = DriverManager.getConnection(url, "Sa", "Sql@123");
	String idquery="select top 1 firstname, lastname, DateofBirth from tblcasemaster where projectshortname='"+project+"' order by id desc";
	Statement st = conn.createStatement();
	ResultSet rs = st.executeQuery(idquery);
	while(rs.next()) {
		data.put("firstname", rs.getString("firstname"));
		data.put("lastname", rs.getString("lastname"));
		data.put("DateofBirth", rs.getString("DateofBirth"));
	}
	conn.close();
	return data;
}

}
