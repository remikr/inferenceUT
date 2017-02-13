package fileManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.ParamTestClass;

public class FileCreator {
	
	public FileCreator() {
		// TODO Auto-generated constructor stub
	}
	
	

	
	public File createJavaFile(String name,String content) throws IOException{
		File file = new File(name);
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
		System.out.println("file create : "+name);
		return file;
	}

}
