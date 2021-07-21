package API_Trials;

public class codePiece {
	
	static int a =10;
	final int b = 12;
	
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		codePiece cp = new codePiece();
		cp.intChange(8);
		System.out.println("int is: "+cp.a);
	}

}
