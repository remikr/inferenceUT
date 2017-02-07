package processor;

import java.io.File;
import java.util.List;
import java.util.Set;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtAnnotationMethod;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.AbstractFilter;
import spoon.reflect.visitor.filter.TypeFilter;
import utils.Literal;
import utils.Method;

public class TestClassProcessor extends AbstractProcessor<CtElement>{
	List<Method> testMethods=null;
	CtClass classe=null;
	Literal literal = new Literal(getFactory());
	
	public TestClassProcessor(List<Method> testMethods){
		this.testMethods=testMethods;
	}
	
	

	public void process(CtElement elem) {
		 if (elem instanceof CtClass) {
			 classe= (CtClass)elem;
			 Set<CtMethod> methods = classe.getMethods();
			 for(CtMethod meth: methods){
				 //System.out.println("meth: "+meth.getSimpleName());
				 for(int i=0;i<meth.getAnnotations().size();i++){
					 CtAnnotation<?> annot = meth.getAnnotations().get(i);
					 //System.out.println("annot: "+annot.getAnnotationType().getSimpleName());
					 if(annot.getAnnotationType().getSimpleName().equals("Test")){
						 Method  method =testMethod(meth);
						
						 if(method!=null){
							 method.method=meth;
							 method.packageName=classe.getPackage().toString();
							 testMethods.add(method);
						 }
					 }
				 }
			
			 }
			 //System.out.println(classe.getPackage());
			 //System.out.println("class: "+classe.getSimpleName());
			// System.out.println("******"+classe.toString());
			//System.out.println(elem.toString());
		 }
		 
		
		 if (elem instanceof CtMethod<?>) {
			 CtMethod meth = (CtMethod) elem;
			 //System.out.println("meth: "+meth.getSimpleName());
			
			 //System.out.println(meth);
		 }
		 
		 
		 
		/* if (elem instanceof CtLiteral) {
			System.out.println("literal");
			System.out.println(elem.toString());
			
		 }*/
	
		 
		
	}
	
	public Method testMethod(CtMethod meth){
		Method method= new Method();
		//System.out.println("nom meth: "+meth.getSimpleName());
		//method.name=meth.getSimpleName();
		//method.signature=meth.getSignature();
		//method.beforeBlock=meth.getBody();
		//System.out.println("method de test");
		//System.out.println(meth.getBody().getStatements().size());
	
		for(int i=0;i<meth.getBody().getStatements().size();i++){
		
			CtStatement line =meth.getBody().getStatement(i);
			
			//System.out.println(line.toString());
			List<CtLiteral> list=line.getElements(new TypeFilter(CtLiteral.class));
			if(list.size()!=0){
				method.literals=list;
				return method;
			}
			//method.literals.addAll(list);
			//System.out.println(list.size());
			/*for(int j=0;j<list.size();j++){
				CtLiteral lit=list.get(j);
				System.out.println("lit"+lit.toString()+lit.getType().toString());
				lit.replace( getFactory().Code().createCodeSnippetExpression("x") );
				System.out.println("litchange"+lit);
				System.out.println(line.toString());
			}*/
			
		}
		return null;
	}
	
	 /*public void compile() {
			JavaOutputProcessor fileOutput = new JavaOutputProcessor(new File(""));
			ByteCodeOutputProcessor classOutput = new ByteCodeOutputProcessor(fileOutput, new File(""));
			classOutput.setFactory(getFactory());
			classOutput.init();
			for (CtSimpleType<?> type : getFactory().Class().getAll()) {
				classOutput.process(type);
			}
			classOutput.processingDone();
		}*/
	

}