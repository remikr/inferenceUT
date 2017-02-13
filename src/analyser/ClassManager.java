package analyser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.Statistic;

import processor.TestClassProcessor;

import spoon.Launcher;
import utils.ParamTestMethod;
import utils.ParamTestClass;
import fileManager.FileCompiler;
import fileManager.FileCreator;

public class ClassManager {
	String testFolder;
	Statistic stat;
	final Launcher launcher;
	
	public ClassManager(Statistic stat,String folder) {
		this.stat=stat;
		this.testFolder=folder;
		launcher = getLauncher();
	}
	
	public List<ParamTestMethod> getParamTestMethods(){
		List<ParamTestMethod> methods= new ArrayList<ParamTestMethod>();
		launcher.addInputResource(testFolder);
    	launcher.addProcessor(new TestClassProcessor(stat,methods));
    	launcher.run();
    	return methods;
	}
	
	public List<ParamTestClass> createParamTestClass( List<ParamTestMethod> methods){
		ArrayList<ParamTestClass> paramTestCodes = new ArrayList<ParamTestClass>();
		for(ParamTestMethod method : methods){
    		paramTestCodes.add(new ParamTestClass(method,launcher.getFactory()));
    	}
    	return paramTestCodes;
	}
	
	public List<Class<?>> getClasses(List<ParamTestClass> paramTestCodes,String jdkPath){
    	List<Class<?>> classes = new ArrayList<Class<?>>();
    	FileCreator fc = new FileCreator();
    	FileCompiler compiler = new FileCompiler(jdkPath,testFolder);
    	    	
    	for(ParamTestClass paramTestCode : paramTestCodes){
    		String packageName=paramTestCode.getPackageName();
    		String className=paramTestCode.getClassName();
    		String path=testFolder+"/"+packageName.replace(".","/")+"/"+className+".java";
    		//System.out.println(path);
  
    		try {
    			
    			File file = fc.createJavaFile(path,paramTestCode.getCode());
    			Class<?> classe= compiler.compile(packageName,className);
    			classes.add(classe);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
    	}
    	return classes;
    }
	
	
    public Launcher getLauncher(){
    	Launcher launcher =  new Launcher();
     	//launcher.createFactory();
     	//pour eviter les erreurs d'import
     	launcher.getEnvironment().setNoClasspath(true);
     	
     	//launcher.addInputResource("../exemple-pj/src");
     	//launcher.setBinaryOutputDirectory("/target");
     	launcher.getEnvironment().setCopyResources(false);
     	//launcher.getFactory().getEnvironment().setAutoImports(false);

     	return launcher;
    }

}
