package testManager;


import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

public class TestLauncher {
	
	public TestLauncher() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean launchParamTest(Class<?> classe, String testFuncName){
		boolean success=true;

		int parametersCount = Request.aClass(classe).getRunner().getDescription().getChildren().size();
	
		for (int i = 0; i < parametersCount; ++i) {
			System.out.println(testFuncName+"[" + i + "]");
    	    Result result = (new JUnitCore()).run(Request.method(classe, testFuncName+"[" + i + "]"));
    	    success=success && result.wasSuccessful();
    	}
		
    	System.out.println("success?"+success);
		return success;
	}

}
