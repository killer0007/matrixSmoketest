package pages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.fluttercode.datafactory.impl.DataFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class temp   {

	public static void main(String[] args) {
	
		
	
}
public void screen(WebDriver driver) throws Exception{
	String path = System.getProperty("user.dir") + "/temp/" + System.currentTimeMillis() + ".png";
	System.out.println(path);
	Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
            .takeScreenshot(driver);
    ImageIO.write(screenshot.getImage(), "PNG", new File(path));
    System.out.println("success");
}
public void particularcreen(WebDriver driver, WebElement element) throws Exception{
	String path = System.getProperty("user.dir") + "/temp/" + System.currentTimeMillis() + ".png";
	File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	  
	  //Used selenium getSize() method to get height and width of element.
	  //Retrieve width of element.
	  int ImageWidth = element.getSize().getWidth();
	  //Retrieve height of element.
	  int ImageHeight = element.getSize().getHeight();  
	  
	  //Used selenium Point class to get x y coordinates of Image element.
	  //get location(x y coordinates) of the element.
	  Point point = element.getLocation();
	  int xcord = point.getX();
	  int ycord = point.getY();
	  System.out.println(xcord +" : "+ycord);
	  System.out.println(ImageWidth +" : "+ImageHeight);
	  //Reading full image screenshot.
	  BufferedImage img = ImageIO.read(screen);
	  
	  //cut Image using height, width and x y coordinates parameters.
	  BufferedImage dest = img.getSubimage(xcord, ycord, ImageWidth, ImageHeight);
	  ImageIO.write(dest, "png", screen);
	 
	  //Used FileUtils class of apache.commons.io.
	  //save Image screenshot In D: drive.
	  FileUtils.copyFile(screen, new File(path));
	 }

}
