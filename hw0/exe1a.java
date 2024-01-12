public class exe1a {
	private static void starprinter(int times){
		int current = 0;
		while (current < times ){
			System.out.print("*");
			current++;
		}
		System.out.println();
	}
	
	public static void main(String[] args){
	for (int i=1;i<=5;i++){
	starprinter(i);
	}
	}

}
