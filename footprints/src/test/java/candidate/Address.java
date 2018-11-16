package candidate;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

public class Address extends dataEntry.Address {

	public Address(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}
	public void CurrentAddress() throws Exception {
		Properties pro=candidatedata("address");
		super.addresscheck();
		super.AddressLine1(pro.getProperty("AddressLine1"));
		super.State();
		super.City();
		super.Pincode(pro.getProperty("Pincode"));
		super.LandMark(pro.getProperty("Landmark"));
		super.FromDate(FormateDate(pro.getProperty("FromDate")));
		super.ToDate(FormateDate(pro.getProperty("ToDate")));
		super.isRented(true);
		super.Name(pro.getProperty("LandLordName"));
		super.LandlordAddress(pro.getProperty("LandLordAddressLine1"));
		super.LandLordState();
		super.LandLordCity();
		super.LandLordPincode(pro.getProperty("LandLordPincode"));
		super.LandLordLandMark(pro.getProperty("LandLordLandmark"));
		super.ContactNo(pro.getProperty("LandLordContactNo"));
//		super.document();
//		super.UploadDocument("Address Proof", pro.getProperty("currentAddressproof"));
//		super.docclose();
//		super.comments(pro.getProperty("Comments"));
//		super.submit();
//		super.save();
		
	}
}
