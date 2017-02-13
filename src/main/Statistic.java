package main;

public class Statistic {
	public int nbTestMethod=0;
	public int nbTestMethodLiteral=0;
	public int nbTestMethodParamPass=0;
	
	public Statistic() {
		// TODO Auto-generated constructor stub
	}
	
	public String toString(){
		return "****Statistic\n"+
				"nbTestMethod : "+nbTestMethod+"\n"+
				"nbTestMethodWithLiteral : "+nbTestMethodLiteral+"\n"+
				"nbTestMethodParamPass : "+nbTestMethodParamPass+"\n";
	}

}
