package jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class AddNumberTest implements JavaSamplerClient{

	 @Override public void setupTest(JavaSamplerContext javaSamplerContext) {
	    }
	    @Override
	    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
	        String var1 = javaSamplerContext.getParameter("var1");
	        String var2 = javaSamplerContext.getParameter("var2");
	        SampleResult result = new SampleResult();
	        result.sampleStart();
	        result.setSampleLabel("Test Sample");
	        // Test Code
	        AddNumbers addNumbers = new AddNumbers();
	        if(addNumbers.addTwoNumbers(Integer.valueOf(var1), Integer.valueOf(var2))==2) {
	            result.sampleEnd();
	            result.setResponseCode("200");
	            result.setResponseMessage("OK");
	            result.setSuccessful(true);
	        } else {
	            result.sampleEnd();
	            result.setResponseCode("500");
	            result.setResponseMessage("NOK");
	            result.setSuccessful(false);
	        }
	        return result;
	    }
	    @Override public void teardownTest(JavaSamplerContext javaSamplerContext) {
	    }
	    @Override public Arguments getDefaultParameters() {
	        Arguments defaultParameters = new Arguments();
	        defaultParameters.addArgument("var1","1");
	        defaultParameters.addArgument("var2","2");
	        return defaultParameters;
	    }

}
