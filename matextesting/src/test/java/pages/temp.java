package pages;

import java.io.IOException;


public class temp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//
	
		System.out.println(getpath("CRT"));
		
	}

	private static String getpath(String title) {
		switch (title) {
		case "Dashboard":
			System.out.println("dashboard");
			return ".//*[@id='ctl00_pnlMenu']/div/div[2]/div[2]/nav/ul/li[2]/ul/li[1]/a";
			//break;
		case "CRT":
			System.out.println("crt");
			return ".//*[@id='ctl00_pnlMenu']/div/div[2]/div[2]/nav/ul/li[2]/ul/li[1]/a";
			default:
				return title;
		}
}
}
