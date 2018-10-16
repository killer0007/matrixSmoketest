package environment;

import com.aventstack.extentreports.ExtentTest;
import environment.Utill;
import pages.*;
import dashboard.*;
import actions.*;

public class Pages {
	private  ExtentTest logger;
	private static Login login;
	private static Utill utill;
	private static Home home;
	private static CaseRegistration caseregistration;
	private static DcaseRegistration dcaseregistration;
	private static DbConnection db;
	private static CaseTracker casetracker;
	private static CaseOwnerInsuffClear CaseOwnerInsuffClear;
	private static DataEntrySupervision DataEntrySupervision;
	private static DataEntry dataentry;
	private static dataEntry.Address address;
	private static dataEntry.Education education;
	private static dataEntry.Employment employement;
	private static dataEntry.Reference reference;
	private static dataEntry.Database database;
	private static dataEntry.Criminal criminal;
	private static dataEntry.Credit credit;
	private static dataEntry.Court court;
	private static dataEntry.Id id;
	private static CEP cep;
	private static dataEntry.CaseInformation caseinformation;

	public Pages(ExtentTest logger) {
		this.logger = logger;
	}

	public Utill Utill() {
		return (utill == null) ? utill = new Utill(logger) : utill;
	}

	public Login Login() {
		return (login == null) ? login = new Login(logger) : login;
	}

	public Home Home() {
		return (home == null) ? home = new Home(logger) : home;
	}

	public CaseRegistration CaseRegistration() {
		return (caseregistration == null) ? caseregistration = new CaseRegistration(logger) : caseregistration;
	}

	public DcaseRegistration DcaseRegistration() {
		return (dcaseregistration == null) ? dcaseregistration = new DcaseRegistration(logger)
				: dcaseregistration;
	}

	public DbConnection DbConnection() throws Exception {
		return (db == null) ? db = new DbConnection() : db;
	}

	public CaseTracker CaseTracker() {
		return (casetracker == null) ? casetracker = new CaseTracker(logger) : casetracker;
	}

	public CaseOwnerInsuffClear CaseOwnerInsuffClear() {
		return (CaseOwnerInsuffClear == null) ? CaseOwnerInsuffClear = new CaseOwnerInsuffClear(logger)
				: CaseOwnerInsuffClear;
	}

	public DataEntrySupervision DataEntrySupervision() {
		return (DataEntrySupervision == null) ? DataEntrySupervision = new DataEntrySupervision(logger)
				: DataEntrySupervision;
	}

	public DataEntry DataEntry() {
		return (dataentry == null) ? dataentry = new DataEntry(logger) : dataentry;
	}

	public dataEntry.Address DeAddress() {
		return (address == null) ? address = new dataEntry.Address(logger) : address;
	}

	public dataEntry.Education DeEducation() {
		return (education == null) ? education = new dataEntry.Education(logger) : education;
	}

	public dataEntry.Employment DeEmployment() {
		return (employement == null) ? employement = new dataEntry.Employment(logger) : employement;
	}

	public dataEntry.Reference DeReference() {
		return (reference == null) ? reference = new dataEntry.Reference(logger) : reference;
	}

	public dataEntry.Database DeDatabase() {
		return (database == null) ? database = new dataEntry.Database(logger) : database;
	}

	public dataEntry.Criminal DeCriminal() {
		return (criminal == null) ? criminal = new dataEntry.Criminal(logger) : criminal;
	}

	public dataEntry.Credit DeCredit() {
		return (credit == null) ? credit = new dataEntry.Credit(logger) : credit;
	}

	public dataEntry.Court DeCourt() {
		return (court == null) ? court = new dataEntry.Court(logger) : court;
	}

	public dataEntry.Id DeId() {
		return (id == null) ? id = new dataEntry.Id(logger) : id;
	}
	public CEP CEP() {
		return (cep == null) ? cep = new CEP(logger) : cep;
	}
//CaseInformation
	public dataEntry.CaseInformation CaseInformation() {
		return (caseinformation == null) ? caseinformation = new dataEntry.CaseInformation(logger) : caseinformation;
	}
}
