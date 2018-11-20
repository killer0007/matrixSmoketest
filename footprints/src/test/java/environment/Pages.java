package environment;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import environment.Utill;
import pages.*;
import dashboard.*;
import actions.*;
import candidate.Basic;
import candidate.Register;

public class Pages {

	private  ExtentTest logger;
	private Login login;
	private Utill utill;
	private Home home;
	private CaseRegistration caseregistration;
	private DcaseRegistration dcaseregistration;
	private DbConnection db; 
	private CaseTracker casetracker;
	private CaseOwnerInsuffClear CaseOwnerInsuffClear;
	private DataEntrySupervision DataEntrySupervision;
	private DataEntryQCSupervision DataEntryQCSupervision;
	private DataEntry dataentry;
	private DataEntryQC dataentryqc;
	private dataEntry.Address address;
	private dataEntry.Education education;
	private dataEntry.Employment employement;
	private dataEntry.Reference reference;
	private dataEntry.Database database;
	private dataEntry.Criminal criminal;
	private dataEntry.Credit credit;
	private dataEntry.Court court;
	private dataEntry.Id id;
	private CEP cep;
	private dataEntry.CaseInformation caseinformation;
	private VerificationSupervisor verificationsupervisor;
	private Verification verification;
	private ReportGenerationSupervision reportGenerationSupervision;
	private ReportGeneration reportGeneration;
	private ReportValidationSupervision reportValidationSupervision;
	private ReportValidation reportValidation;
	private WebDriver driver;
	private Register register;
	private Basic basic;
	private candidate.Address Caddress;
	private candidate.Education Ceducation;
	private candidate.Employment Cemployment;
	private candidate.Id Cid;
	private candidate.Reference Creference;

	public Pages(WebDriver driver, ExtentTest logger) {
		this.driver=driver;
		this.logger = logger;
	}

	public Utill Utill() {
		return (utill == null) ? utill = new Utill(driver,logger) : utill;
	}

	public Login Login() {
		return (login == null) ? login = new Login(driver,logger) : login;
	}

	public Home Home() {
		return (home == null) ? home = new Home(driver,logger) : home;
	}

	public CaseRegistration CaseRegistration() {
		return (caseregistration == null) ? caseregistration = new CaseRegistration(driver,logger) : caseregistration;
	}

	public DcaseRegistration DcaseRegistration() {
		return (dcaseregistration == null) ? dcaseregistration = new DcaseRegistration(driver, logger)
				: dcaseregistration;
	}

	public DbConnection DbConnection() throws Exception {
		return (db == null) ? db = new DbConnection() : db;
	}

	public CaseTracker CaseTracker() {
		return (casetracker == null) ? casetracker = new CaseTracker(driver,logger) : casetracker;
	}

	public CaseOwnerInsuffClear CaseOwnerInsuffClear() {
		return (CaseOwnerInsuffClear == null) ? CaseOwnerInsuffClear = new CaseOwnerInsuffClear(driver, logger)
				: CaseOwnerInsuffClear;
	}

	public DataEntrySupervision DataEntrySupervision() {
		return (DataEntrySupervision == null) ? DataEntrySupervision = new DataEntrySupervision(driver, logger)
				: DataEntrySupervision;
	}

	public DataEntry DataEntry() {
		return (dataentry == null) ? dataentry = new DataEntry(driver, logger) : dataentry;
	}

	public dataEntry.Address DeAddress() {
		return (address == null) ? address = new dataEntry.Address(driver,logger) : address;
	}

	public dataEntry.Education DeEducation() {
		return (education == null) ? education = new dataEntry.Education(driver,logger) : education;
	}

	public dataEntry.Employment DeEmployment() {
		return (employement == null) ? employement = new dataEntry.Employment(driver,logger) : employement;
	}

	public dataEntry.Reference DeReference() {
		return (reference == null) ? reference = new dataEntry.Reference(driver,logger) : reference;
	}

	public dataEntry.Database DeDatabase() {
		return (database == null) ? database = new dataEntry.Database(driver,logger) : database;
	}

	public dataEntry.Criminal DeCriminal() {
		return (criminal == null) ? criminal = new dataEntry.Criminal(driver,logger) : criminal;
	}

	public dataEntry.Credit DeCredit() {
		return (credit == null) ? credit = new dataEntry.Credit(driver,logger) : credit;
	}

	public dataEntry.Court DeCourt() {
		return (court == null) ? court = new dataEntry.Court(driver,logger) : court;
	}

	public dataEntry.Id DeId() {
		return (id == null) ? id = new dataEntry.Id(driver,logger) : id;
	}
	public DataEntryQCSupervision DataEntryQCSupervision() {
		return (DataEntryQCSupervision == null) ? DataEntryQCSupervision = new DataEntryQCSupervision(driver, logger)
				: DataEntryQCSupervision;
	}
	public DataEntryQC DataEntryQC() {
		return (dataentryqc == null) ? dataentryqc = new DataEntryQC(driver, logger) : dataentryqc;
	}
	public CEP CEP() {
		return (cep == null) ? cep = new CEP(driver, logger) : cep;
	}
	public dataEntry.CaseInformation CaseInformation() {
		return (caseinformation == null) ? caseinformation = new dataEntry.CaseInformation(driver, logger) : caseinformation;
	}
	public VerificationSupervisor VerificationSupervisor() {
		return (verificationsupervisor == null) ? verificationsupervisor = new VerificationSupervisor(driver, logger) : verificationsupervisor;
	}
	public Verification Verification() {
		return (verification == null) ? verification = new Verification(driver, logger) : verification;
	}
	
	public ReportGenerationSupervision ReportGenerationSupervision() {
		return (reportGenerationSupervision == null) ? reportGenerationSupervision = new ReportGenerationSupervision(driver, logger) : reportGenerationSupervision;
	}
	public ReportGeneration ReportGeneration() {
		return (reportGeneration == null) ? reportGeneration = new ReportGeneration(driver, logger) : reportGeneration;
	}
	public ReportValidationSupervision ReportValidationSupervision() {
		return (reportValidationSupervision == null) ? reportValidationSupervision = new ReportValidationSupervision(driver, logger) : reportValidationSupervision;
	}
	public ReportValidation ReportValidation() {
		return (reportValidation == null) ? reportValidation = new ReportValidation(driver, logger) : reportValidation;
	}
	public Register Register() {
		return (register == null) ? register = new Register(driver, logger) : register;
	}
	public Basic Basic() {
		return (basic == null) ? basic = new Basic(driver, logger) : basic;
	}
	public candidate.Address Address() {
		return (Caddress == null) ? Caddress = new candidate.Address(driver,logger) : Caddress;
	}
	public candidate.Education Education() {
		return (Ceducation == null) ? Ceducation = new candidate.Education(driver,logger) : Ceducation;
	}
	public candidate.Employment Employment() {
		return (Cemployment == null) ? Cemployment = new candidate.Employment(driver,logger) : Cemployment;
	}
	public candidate.Reference Reference() {
		return (Creference == null) ? Creference = new candidate.Reference(driver,logger) : Creference;
	}
	public candidate.Id Id() {
		return (Cid == null) ? Cid = new candidate.Id(driver,logger) : Cid;
	}
}
