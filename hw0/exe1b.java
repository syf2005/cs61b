public class exe1b {
    private static void starprinter(int times){
		int current = 0;
		while (current < times ){
			System.out.print("*");
			current++;
		}
		System.out.println();
	}

    private static void triangleprinter(int line){
        for (int i=1;i<=line;i++){
            starprinter(i);
        }
    }

    public static void main(String[] args) {
        triangleprinter(10);
    }
}
