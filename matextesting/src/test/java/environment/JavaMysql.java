package environment;
import java.sql.*;
public class JavaMysql {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
try {
	
	String myUrl = "jdbc:mysql://192.168.2.16:1433/FinalMatrixTestingDB";
	Class.forName("com.mysql.cj.jdbc.Driver");  	
    Connection conn = DriverManager.getConnection(myUrl, "Sa", "Sql@123");
    
    // our SQL SELECT query. 
    // if you only need a few columns, specify them by name instead of using "*"
    String query = "OPEN Symmetric KEy Mat_Key  DECRYPTION By Certificate Matrix_cert\r\n" + 
    		"   \r\n" + 
    		"select User_name,convert(varchar(50),decryptbykey(User_password)),User_id,User_Userinfoid from tbl_master_webusers where user_name Like '%demotl%'\r\n" + 
    		"\r\n" + 
    		"close Symmetric KEy Mat_Key";

    // create the java statement
    Statement st = conn.createStatement();
    
    // execute the query, and get a java resultset
    ResultSet rs = st.executeQuery(query);
    System.out.println(rs);
} catch (Exception e) {
	System.out.println(e);
}
	}

}
