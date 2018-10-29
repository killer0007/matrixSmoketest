package environment;

import com.aventstack.extentreports.ExtentTest;
import environment.Utill;
import pages.*;
import dashboard.*;
import actions.*;

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
	public DataEntryQCSupervision DataEntryQCSupervision() {
		return (DataEntryQCSupervision == null) ? DataEntryQCSupervision = new DataEntryQCSupervision(logger)
				: DataEntryQCSupervision;
	}
	public DataEntryQC DataEntryQC() {
		return (dataentryqc == null) ? dataentryqc = new DataEntryQC(logger) : dataentryqc;
	}
	public CEP CEP() {
		return (cep == null) ? cep = new CEP(logger) : cep;
	}
	public dataEntry.CaseInformation CaseInformation() {
		return (caseinformation == null) ? caseinformation = new dataEntry.CaseInformation(logger) : caseinformation;
	}
	public VerificationSupervisor VerificationSupervisor() {
		return (verificationsupervisor == null) ? verificationsupervisor = new VerificationSupervisor(logger) : verificationsupervisor;
	}
}
