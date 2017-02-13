package utils;

import java.util.ArrayList;
import java.util.List;

import spoon.reflect.code.CtLiteral;


/**
 * represente variable dans le file generer
 * @author Admin
 *
 */
public class Variable {
	//public String literal;
	public String name;
	public String type;
	public List<CtLiteral> ctLiterals;
	
	public Variable() {
		ctLiterals=new ArrayList<CtLiteral>();
	}
	
	

}
