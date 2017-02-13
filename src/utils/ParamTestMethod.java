package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.declaration.CtMethod;

public class ParamTestMethod {
	public String packageName;
	public List<CtLiteral> literals;
	public CtMethod method;
	
	public ParamTestMethod() {
		literals=new ArrayList<CtLiteral>();
	}
	
	public String getName(){
		return method.getSimpleName();
	}
	
	public void tranformMethod(){
		
		
		//if(){
			//line.insertBefore(Literal.getCodeInitVariable(literal));
		
	}
	
	
	public List<CtLiteral> getLiteral(){
		return literals;
	}
	
	
	public String toString(){
		return "package "+packageName+"\n"+method;
	}

}
