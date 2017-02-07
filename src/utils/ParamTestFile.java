package utils;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

public class ParamTestFile {
	public String importS;
	public String className;
	public Method method;
	
	public ParamTestFile(Method method) {
		this.method=method;
		className="Param"+method.getName().substring(0,1).toUpperCase()+method.getName().substring(1);
	}
	
	public String getFileName(){
		System.out.println(method.packageName+"/"+className+".java");
		return method.packageName+"/"+className+".java";
	}
	public String getFileCode(){
		String code="";
		code=code+"package "+method.packageName+";\n";
		//code=code+importS+"\n";
		code=code+getClasseCode();
		return code;
	}
	
	public String getImportCode(){
		return "import org.junit.runners.Parameterized.Parameters;\n"+
				"import java.util.Collection;\n"+
				"import java.util.Arrays;\n";
	}
	
	
	public String getConstructorCode(){
		return "public "+className+"(final int number) {\n"+
		    "this.number = number;\n"+
		"}\n";
	}
	
	public String getParamCode(){
	return "@Parameters\n" +
			"public static Collection<Object[]> params() {\n" +
			"return Arrays.asList(\n" +
			"new Object[] { 3},\n" +
			"new Object[] { 5}\n" +
			");\n" +
			"}\n";
	}
	
	
	public String getClasseCode(){
		String code="";
		code=code+getImportCode()+"\n";
		code=code+"public class "+className+"{\n";
		/*code=code+variable;*/
		code=code+getConstructorCode()+"\n";
		code=code+getParamCode()+"\n";
		code=code+method.method+"\n";
		code=code+"}";
		return code;
	}

	public File getFile(){
		return new File("");
	}
}
