package utils;

import java.util.List;

public class ValueGenerator {
	int nb=2;
	
	public ValueGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	public String getValues(List<Variable> variables){
		String code="return Arrays.asList(\n";
		
		for(int i=0;i<nb;i++){
			if(i!=0){
				code=code+",";
			}
			code=code+getObject(variables);
			//getIntMaxObject(variables)+
		}
		code=code+");\n";
		return code;
	}
	
	public String getObject(List<Variable> variables){
		String code="new Object[] { ";
		for(int i=0;i<variables.size();i++){
			if(i!=0){
				code=code+",";
			}
			code=code+variables.get(i).ctLiterals.get(0).toString();
		}

		code=code+"}\n";
		return code;
	}
	
	public String getIntMaxObject(List<Variable> variables){
		String code="new Object[] { ";
		for(int i=0;i<variables.size();i++){
			if(variables.get(i).type.equals("int")){
			if(i!=0){
				code=code+",";
			}
			code=code+Integer.MAX_VALUE;
			}
		}

		code=code+"}\n";
		return code;
	}

}
