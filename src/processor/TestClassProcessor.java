package processor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import main.Statistic;

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
import utils.ParamTestMethod;

public class TestClassProcessor extends AbstractProcessor<CtElement>{
	Statistic stat;
	List<ParamTestMethod> testMethods=null;
	CtClass classe=null;
	//Literal literal = new Literal(getFactory());
	
	public TestClassProcessor(Statistic stat,List<ParamTestMethod> testMethods){
		this.stat=stat;
		this.testMethods=testMethods;
	}

	public void process(CtElement elem) {
		 if (elem instanceof CtClass) {
			 classe= (CtClass)elem;
			 //System.out.println(classe.getQualifiedName());
			 Set<CtMethod> methods = classe.getMethods();
			 for(CtMethod meth: methods){
				 //System.out.println("meth: "+meth.getSimpleName());
				 for(int i=0;i<meth.getAnnotations().size();i++){
					 CtAnnotation<?> annot = meth.getAnnotations().get(i);
					 //System.out.println("annot: "+annot.getAnnotationType().getSimpleName());
					 if(annot.getAnnotationType().getSimpleName().equals("Test")){
						 stat.nbTestMethod++;
						 ParamTestMethod  method =testMethod(meth);
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
		 
		
	}
	
	public ParamTestMethod testMethod(CtMethod meth){
		ParamTestMethod method= new ParamTestMethod();
		//System.out.println("nom meth: "+meth.getSimpleName());
		//method.name=meth.getSimpleName();
		//method.signature=meth.getSignature();
		//method.beforeBlock=meth.getBody();
		//System.out.println("method de test");
		//System.out.println(meth.getBody().getStatements().size());
		List<CtLiteral> literalOfMethod = new ArrayList<CtLiteral>();
		for(int i=0;i<meth.getBody().getStatements().size();i++){
		
			CtStatement line =meth.getBody().getStatement(i);
			
			//System.out.println(line.toString());
			List<CtLiteral> list=line.getElements(new TypeFilter(CtLiteral.class));

			literalOfMethod.addAll(list);
			//method.literals.addAll(list);
			//System.out.println(list.size());
		}
		if(literalOfMethod.size()!=0){
			//System.out.println(literalOfMethod.size());
			method.literals=literalOfMethod;
			return method;
		}
		return null;
	}

}
