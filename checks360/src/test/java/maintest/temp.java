package maintest;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class temp {
public static void main(String[] args){
	String url1 = "http://192.168.2.17:97/Images/c360_logo_green.png";

	
}
public boolean isimage(String url) {
	try {
		
		Image image = ImageIO.read(new URL(url));
		if(image != null){
		  return true;
		    
		}else{
		    return false;
		}
	} catch (MalformedURLException e) {
		return false;
	} catch (IOException e) {
		return false;
	}
}
}
