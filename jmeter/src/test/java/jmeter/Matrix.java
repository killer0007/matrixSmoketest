package jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class Matrix implements JavaSamplerClient{

	public Arguments getDefaultParameters() {
		Arguments da=new Arguments();
		da.addArgument("username", "demotl");
		da.addArgument("password", "pass@123");
		da.addArgument("headless", "yes");
		return da;
	}

	public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
		  String name = javaSamplerContext.getParameter("username");
		  String pass = javaSamplerContext.getParameter("password");
		  String headless = javaSamplerContext.getParameter("headless");
		  SampleResult result = new SampleResult();
		  result.sampleStart();
		  result.setSampleLabel("test starting");
		  Browser b = new Browser();
		  String op=b.matex(headless, name, pass);
		  if(op.equals("Welcome :")) {
			  result.sampleEnd();
			  result.setResponseCode("200");
			  result.setResponseMessage("OK");
			  result.setSuccessful(true);
			  
		  }
		  else {
			  result.sampleEnd();
			  result.setResponseCode("500");
			  result.setResponseMessage(op);
			  result.setSuccessful(false);
		  }
		return result;
	}

	public void setupTest(JavaSamplerContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void teardownTest(JavaSamplerContext arg0) {
		// TODO Auto-generated method stub
		
	}

}
