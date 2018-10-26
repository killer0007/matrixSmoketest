package dataEntryQC;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
public class temp {



public static void main(String[] args) throws Exception {
	String path="C:\\Users\\admin\\Documents\\pro.txt";
//	getethods(path);
//	put(path);
	filedata(path);

}
public static void filedata(String path) throws Exception{
	BufferedReader bf = new BufferedReader(new java.io.FileReader(new File(path)));
	String st;
	while ((st=bf.readLine())!=null) {
		//map.put("ToDate", pro.getProperty("ToDate"));
		String name=st.substring(0,st.lastIndexOf(":"));
		if(name.contains(":")) {
		name=name.replaceAll("\\:.*$", "");
		}
		String output="map.put(\""+name+"\", pro.getProperty(\""+name+"\"));";
		System.out.println(output);
	
	
	}
	bf.close();
}
public static void put(String path) throws IOException{
	//map.put("Country", this.Country());
	BufferedReader bf = new BufferedReader(new java.io.FileReader(new File(path)));
	String st;
	while ((st=bf.readLine())!=null) {
		
		String name=st.substring(0,st.lastIndexOf(":"));
		if(name.contains(":")) {
		name=name.replaceAll("\\:.*$", "");
		}
		String output="map.put(\""+name+"\", this."+name+"());";
		System.out.println(output);
	
	
	}
	bf.close();
}

public static void getethods(String path) throws IOException {
	BufferedReader bf = new BufferedReader(new java.io.FileReader(new File(path)));
	String st;
	while ((st=bf.readLine())!=null) {
		
		String name=st.substring(0,st.lastIndexOf(":"));
		if(name.contains(":")) {
		name=name.replaceAll("\\:.*$", "");
		}
		String output="public String "+name+"() { \n "
				+ "return pages.Utill().getvalue(\"locator\");\n"
				+ "}";
		System.out.println(output);
	
	
	}
	bf.close();
}
}

