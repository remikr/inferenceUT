package fileManager;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.DiagnosticCollector;
import javax.tools.StandardJavaFileManager;

import org.apache.commons.io.FileUtils;

import main.*;


public class FileCompiler {
	String jdkPath;
	String testFolder;
	
	public FileCompiler(String jdkPath, String testFolder) {
		this.jdkPath = jdkPath;
		this.testFolder=testFolder;
	}
	
	@SuppressWarnings("restriction")
	public Class<?> compile(String packageName, String className) throws ClassNotFoundException, MalformedURLException{
		//System.out.println("get:"+System.getProperty("java.home"));
    	System.setProperty("java.home",jdkPath);
    	String filePath=testFolder+"/"+packageName.replace(".", "/")+"/"+className+".java";
    	System.out.println("filePath compil : "+filePath);

		
		//compiler.run(null, null, null, filePath);
		
		//option
		//System.out.println(System.getProperty("java.class.path"));
		//fichiers a compiler
		
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(filePath)); 
		List<String> optionList = Arrays.asList("-classpath",System.getProperty("java.class.path"));
		
		JavaCompiler.CompilationTask task = compiler.getTask(null,null,null,optionList,null,compilationUnits);
		task.call();
    	
		File file=new File(testFolder);
		//System.out.println("file :"+file.toURI().toURL());
		URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { file.toURI().toURL()});
		System.out.println("ko:"+packageName+"."+className);
		Class<?> cls = classLoader.loadClass(packageName+"."+className);
		System.out.println("compilation done : "+cls.getName());

		return cls;
	}
	


}
