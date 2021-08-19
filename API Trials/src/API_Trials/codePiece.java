package API_Trials;

public class codePiece {
	
	static int a =10;
	final int b = 12;
	static String stcs = "before";
	
	public void intChange(int i)
	{
		System.out.println("int is: "+a);
		a=i;
		//b = 13;
		System.out.println("int is: "+a);
		codePiece cp1 = new codePiece();
		cp1.a=24;
		System.out.println("int is: "+cp1.a);
		codePiece.a=114;
		System.out.println("int is: "+a);
	}
	
	public void codehausTest(String a, String b)
	{
		System.out.println("Static value with mvn params: "+stcs+" and value "+a+" and "+b);
		codePiece.stcs = "After";
		System.out.println("Static variable changed to :" +stcs);
	}

	//mvn exec:java@before-execution -DfName=Benit -DlName=Shetty
	//RestProj_Bizs>mvn clean install package -Drelease.artifactId=RestAPIBiz -Drelease.version=10.6 -Drelease.svm.version=74
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		codePiece cp = new codePiece();
		//cp.intChange(8);
		//System.out.println("int is: "+cp.a);
		cp.codehausTest(args[0], args[1]);
	}

}
