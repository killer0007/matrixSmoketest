package verification;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

public class CaseInformation extends dataEntry.CaseInformation {

	/**
	 * This is class for Case information part in data entry
	 * 
	 * @param logger logger instance
	 */
	public CaseInformation(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}
}
