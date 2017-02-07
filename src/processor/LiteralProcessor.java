package processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.declaration.CtElement;

public class LiteralProcessor extends AbstractProcessor<CtLiteral> {

	public void process(CtLiteral arg0) {
		System.out.println("literal");
		System.out.println(arg0.toString());
		
	}

	

	

}
