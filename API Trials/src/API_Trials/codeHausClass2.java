package API_Trials;

class codeHausClass2 {
	
	static String stcs = "After";

	public void codehausTest(String a, String b) {
		System.out.println("Static value with mvn params: " + stcs + " and value " + a + " and " + b);
		codePiece.stcs = "After";
		System.out.println("Static variable changed to :" + stcs);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		codeHausClass2 ch = new codeHausClass2();
		ch.codehausTest(args[1], args[0]);
	}

}
