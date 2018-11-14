package verification;


public interface  Verification {


	
	/**
	 * Takes doctype and file name as input and waits for given document to upload
	 * @param doctype type of document
	 * @param filepath file to upload
	 */
	public void WaitforFileUpdate(String doctype,String filepath) ;
	public void UpdateReportComments() ;
}
