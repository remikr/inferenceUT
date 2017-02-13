package main;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.jdt.core.JavaCore;

import analyser.ClassManager;


import testManager.TestLauncher;

import utils.ParamTestClass;
import utils.ParamTestMethod;
import utils.Variable;





public class Main 
{
    public static void main( String[] args ){
    	long begin1 = System.nanoTime();
    	String jdkPath = args[0];//= "C:\\Program Files\\Java\\jdk1.8.0_25";
    	
    	String testFolder = args[1] ;//"../exemple-pj/test";
    	String jarPath = args[2]; //"../exemple-pj/toto.jar";
    	
    	
    	/*String jarPath="C:\\Users\\Admin\\Desktop\\jsoup.jar";
    	String testFolder = "C:/Users/Admin/Desktop/src/test/java";*/
    	
    	//pour la compilation des test
    	System.setProperty("java.class.path", System.getProperty("java.class.path")+";"+jarPath);
    	//pour l'execution des test
    	addToClasspath(jarPath);
    	Statistic stat= new Statistic();
    	
    	ClassManager classManager = new ClassManager(stat,testFolder);
    	//getallmethode parametrable
    	List<ParamTestMethod> methods = classManager.getParamTestMethods();
    	stat.nbTestMethodLiteral=methods.size();
    	long end1 = System.nanoTime();
    	long begin2 = System.nanoTime();
    	//create parametrable file for methodes
    	List<ParamTestClass> paramTestClasses = classManager.createParamTestClass(methods);
    	//compile and get class<?>
    	List<Class<?>> classes = classManager.getClasses(paramTestClasses, jdkPath);
    	
    	//launch test
    	TestLauncher testLaunch = new TestLauncher();
    	List<Class<?>> fail = new ArrayList<Class<?>>();
    	for(Class<?> classe : classes){
    		String methodName = classe.getSimpleName().substring(5);
    		boolean success=testLaunch.launchParamTest(classe, methodName.substring(0, 1).toLowerCase()+methodName.substring(1));
    		if(success){
    			stat.nbTestMethodParamPass++;
    		}else{
    			fail.add(classe);
    		}
    	}
    	
    	System.out.println(stat);
    	
    	long end2 = System.nanoTime();
    	System.out.println((end1-begin1));
    	System.out.println((end2-begin2));
    }

    public static void addToClasspath(String pathToJar){
    	try {
    	
    		final Class[] parameters = new Class[]{URL.class};
    		URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
    		Class sysclass = URLClassLoader.class;
    	 
    		Method method = sysclass.getDeclaredMethod("addURL", parameters);
    		method.setAccessible(true);
    		method.invoke(sysloader, new Object[]{new File(pathToJar).toURI().toURL()});
    	
    	}catch (Exception e1) {
			e1.printStackTrace();
		} 
    }

   
    
 
}
