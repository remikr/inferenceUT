package main;

import processor.TestClassProcessor;
import spoon.Launcher;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String input = "../exemple-pj";
    	
    	final Launcher launcher = new Launcher();
    	//launcher.createFactory();
    	//pour eviter les erreurs d'import
    	launcher.getEnvironment().setNoClasspath(true);
    	launcher.addInputResource(input);
    	//launcher.getFactory().getEnvironment().setAutoImports(true);
    	launcher.addProcessor(new TestClassProcessor());

    	launcher.run();
    }
}
