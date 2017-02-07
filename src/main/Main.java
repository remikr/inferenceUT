package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import processor.LiteralProcessor;
import processor.TestClassProcessor;
import spoon.Launcher;
import spoon.support.JavaOutputProcessor;
import utils.Method;
import utils.ParamTestFile;


/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ){
    	String input = "../exemple-pj/test";
    	
    	List<Method> methods = new ArrayList<Method>();
    	final Launcher launcher = new Launcher();
    	//launcher.createFactory();
    	//pour eviter les erreurs d'import
    	launcher.getEnvironment().setNoClasspath(true);
    	launcher.addInputResource(input);
    	//launcher.setSourceOutputDirectory("target");
    	launcher.getEnvironment().setCopyResources(false);
    

    	//launcher.getFactory().getEnvironment().setAutoImports(false);
    	launcher.addProcessor(new TestClassProcessor(methods));
    	//launcher.addProcessor(new LiteralProcessor());

    	launcher.run();
    	
    	//System.out.println(methods.toString());
    	ArrayList<ParamTestFile> paramTestFiles = new ArrayList<ParamTestFile>();
    	for(Method method : methods){
    		paramTestFiles.add(new ParamTestFile(method,launcher.getFactory()));
    	}
    	
    	createNewTestFiles(paramTestFiles,input);
    	
    	
    }
    
    
    public static void createNewTestFiles(List<ParamTestFile> paramTestFiles,String input){
    	for(ParamTestFile paramTestFile : paramTestFiles){
    		System.out.println(paramTestFile.getFileCode());
    		try {
    			File file = new File(input+"/"+paramTestFile.getFileName());
    			FileWriter fw = new FileWriter(file.getAbsoluteFile());
    			BufferedWriter bw = new BufferedWriter(fw);
				bw.write(paramTestFile.getFileCode());
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    
   
    
 
}
