package utils;

import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.factory.Factory;

public class Literal {
	static Factory factory;
	public Literal(Factory fact) {
		
		
	}
	
	public static CtCodeSnippetStatement getCodeInitVariable(CtLiteral literal){
		return factory.Code().createCodeSnippetStatement("int x = 5");
	}
	
	
}
