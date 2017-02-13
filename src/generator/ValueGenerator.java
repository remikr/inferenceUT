package generator;

import java.util.ArrayList;
import java.util.List;

import utils.Variable;

public class ValueGenerator {
	

	public ValueGenerator() {
		
	}
	
	public String getValues(List<Variable> variables){
		//System.out.println("size variable:"+variables.size());
		String code="return Arrays.asList(\n";
		
		code=code+getListValues(variables);
		
		code=code+");\n";
		return code;
	}
	
	
	public String getListValues(List<Variable> variables){
	
		String code="new Object[][] {\n";
		int nbLine= getNbLine(variables);
		
		//System.out.println("nbline:"+nbLine);
		for(int i=0;i<nbLine;i++){
			if(i!=0){
				code=code+",";
			}
			String codeInArray="";
			for(int j=0;j<variables.size();j++){
				if(j!=0){
					codeInArray=codeInArray+",";
				}
				Variable v= variables.get(j);
				//System.out.println("v.type:"+v.type);
				if(v.type.equals("int")){
					List<Integer> ints= getIntValues((Integer)v.ctLiterals.get(0).getValue());
					codeInArray=codeInArray+ints.get(i%ints.size());
				}else if(v.type.equals("java.lang.String")){
					List<String> strs=getStringValues((String)v.ctLiterals.get(0).getValue());
					//System.out.println(strs.size());
					//System.out.println("get:"+strs.get(i%strs.size()));
					codeInArray=codeInArray+"\""+strs.get(i%strs.size())+"\"";
				}
			}
			//System.out.println(codeInArray);
			code=code+("{ "+codeInArray+"}\n");
		}
		code=code+"}";
		return code;
		
	}
	
	
	public int getNbLine(List<Variable> variables){
		int nbLine=0;
		for(Variable v : variables){
			int nb=0;
			//System.out.println("v.type:"+v.type);
			if(v.type.equals("int")){
				nb=getIntValues((Integer)v.ctLiterals.get(0).getValue()).size();
				
			}else if(v.type.equals("java.lang.String")){
				nb=getStringValues((String)v.ctLiterals.get(0).getValue()).size();
			}
			if(nb>nbLine){
				nbLine=nb;
			}
		}
		return nbLine;
	}
	
	
	
	
	public List<Integer> getIntValues(int i){
		List<Integer> ints = new ArrayList<Integer>();
		ints.add(i);
		ints.add(Integer.MAX_VALUE);
		ints.add(Integer.MIN_VALUE);
		ints.add(0);
		return ints;
	}
	
	public List<String> getStringValues(String s){
		List<String> strs = new ArrayList<String>();
		strs.add(s);
		strs.add("");
		return strs;
	}
	

}
