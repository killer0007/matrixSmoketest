package environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class DbConnection {
	
	public static void main(String[] args)throws Exception {	
		List<String> data=new DbConnection().getcontractdetails("demo contractauto");
		for (int i = 0; i < data.size(); i++) {
//		for (int i = 21; i < 28; i++) {
			System.out.println(i+" = "+data.get(i).toString());
		}
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

}
