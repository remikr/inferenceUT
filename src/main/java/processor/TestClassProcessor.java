package processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtAnnotationMethod;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.support.reflect.declaration.CtAnnotationMethodImpl;

public class TestClassProcessor extends AbstractProcessor<CtElement>{

	public void process(CtElement elem) {
		 if (elem instanceof CtClass) {
			 CtClass classe = (CtClass)elem;
			 //System.out.println("class: "+classe.getSimpleName());
		 
			//System.out.println(elem.toString());
		 }
		 
		 if (elem instanceof CtMethod<?>) {
			 CtMethod meth = (CtMethod) elem;
			 //System.out.println("meth: "+meth.getSimpleName());
			 for(int i=0;i<meth.getAnnotations().size();i++){
				 CtAnnotation<?> annot = meth.getAnnotations().get(i);
				 //System.out.println("annot: "+annot.getAnnotationType().getSimpleName());
				 if(annot.getAnnotationType().getSimpleName().equals("Test")){
					 TestMethod(meth);
				 }
				 
			 }
			
			 
			 
			
		 }
		 
		 if (elem instanceof CtAnnotationMethod) {
			 //System.out.println("annotmeth");
			
		 }
	
			 
		
	}
	
	public void TestMethod(CtMethod meth){
		System.out.println("meth: "+meth.getSimpleName());
		System.out.println("method de test");
		System.out.println(meth.getBody().getStatements().size());
		for(int i=0;i<meth.getBody().getStatements().size();i++){
			
		}
	}

}
