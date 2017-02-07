package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.filter.TypeFilter;

public class ParamTestFile {
	public String importS;
	public String className;
	public Method method;
	Factory factory;
	ValueGenerator valueGen=new ValueGenerator();
	
	public ParamTestFile(Method method,Factory factory) {
		this.factory=factory;
		this.method=method;
		className="Param"+method.getName().substring(0,1).toUpperCase()+method.getName().substring(1);
	}
	
	public String getFileName(){
		System.out.println(method.packageName+"/"+className+".java");
		return method.packageName+"/"+className+".java";
	}
	public String getPackageCode(){
		String code="";
		code=code+"package "+method.packageName+";\n";
		return code;
	}
	
	public String getImportCode(){
		return "import org.junit.runners.Parameterized.Parameters;\n"+
				"import org.junit.runner.RunWith;\n"+
				"import org.junit.runners.Parameterized;\n"+
				"import java.util.Collection;\n"+
				"import java.util.Arrays;\n";
	}
	
	public String getVariableInitCode(){
		List<Variable> variablesS =  getVariables(method.literals);
		String code="//variables\n";
		for(Variable v : variablesS){
			code=code+v.type+" "+v.name+";\n";
		}
		return code;
	}
	
	public String getVariableParamCode(){
		List<Variable> variablesS =  getVariables(method.literals);
		String code="";
		for(int i=0;i<variablesS.size()-1;i++){
			
			code=code+"final "+variablesS.get(i).type+" "+variablesS.get(i).name+",";
		}
		if(variablesS.size()>0){
			code=code+"final "+variablesS.get(variablesS.size()-1).type+" "+
					variablesS.get(variablesS.size()-1).name;
		}
		return code;
	}
	
	public String getVariableAssignCode(){
		List<Variable> variablesS =  getVariables(method.literals);
		String code="";
		for(Variable v : variablesS){
			code=code+"this."+v.name+"="+v.name+";\n";
		}
		return code;
	}
	
	
	public String getVariableName(int i){
		return Character.toString ((char) (i+97));
	}
	
	public List<Variable> getVariables(List<CtLiteral> literals){
		List<Variable> variables = new ArrayList<Variable>();
		//List<String> literalsName = new ArrayList<String>();
		HashMap<String,Variable> map = new HashMap<String,Variable>();
 		int i=0;
		for(CtLiteral literal : literals){
			if(!map.containsKey(literal.toString())){
				Variable  v= new Variable();
				//v.literal = literal.toString();
				
				v.type= literal.getType().toString();
				v.name=getVariableName(i++);
				v.ctLiterals.add(literal);
				
				map.put(literal.toString(),v);
				variables.add(v);
			}else{
				
				Variable  v=map.get(literal.toString());				
				//v.type= literal.getType().toString();
				//v.name=map.get(literal.toString()).name;
				v.ctLiterals.add(literal);
				//map.put(literal.toString(),v);

				//literalsName.get(literalsName.indexOf(literal.toString()));
			}
		}
		return variables;
		
	}
	
	
	public String getConstructorCode(){
		return "public "+className+"("+getVariableParamCode()+") {\n"+
		    getVariableAssignCode()+
		"}\n";
	}
	
	public String getParamCode(){
	return "@Parameters\n" +
			"public static Collection<Object[]> params() {\n" +
			valueGen.getValues(getVariables(method.literals))+
			"}\n";
	}
	
	public String getClasseCode(){
		return "@RunWith(Parameterized.class)\n"+
				"public class "+className+"{\n";
	}
	
	public CtMethod getNewMethodCode(){
		List<Variable> variables = getVariables(method.literals);
		for(Variable variable : variables){
			for(CtLiteral literal : variable.ctLiterals){
				literal.replace( factory.Code().createCodeSnippetExpression(variable.name) );
			}
		}
		return method.method;	
		
	}
	
	public String getFileCode(){
		String code="";
		code=code+getPackageCode()+"\n";
		code=code+getImportCode()+"\n";
		code=code+getClasseCode()+"\n";
		code=code+getVariableInitCode()+"\n";
		code=code+getConstructorCode()+"\n";
		code=code+getParamCode()+"\n";
		code=code+getNewMethodCode()+"\n";
		code=code+"}";
		return code;
	}

	public File getFile(){
		return new File("");
	}
}
