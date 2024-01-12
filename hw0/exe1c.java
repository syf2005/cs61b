public class exe1c {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int sup = m[0];
        for (int i=0;i<m.length;i++){
            if (m[i]>sup){
                sup=m[i];
            }
        }
        return sup;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
       System.out.printf("The maximum is: %d\n",max(numbers));   
    }
}