public class BreakContinue {
  public static void windowPosSum(int[] a, int n) {
    /** your code here */ 
    int elements = a.length;
    for (int i=0;i<elements;i++){
        if (a[i]<0){
            continue;
        }
        for (int k=i+1;k<=i+n;k++){
            if (k>=elements){
                break;
            }
            a[i]+=a[k];
        }
    }

  }

  public static void main(String[] args) {
    int[] a = {1, 2, -3, 4, 5, 4};
    int n = 3;
    windowPosSum(a, n);

    // Should print 4, 8, -3, 13, 9, 4
    System.out.println(java.util.Arrays.toString(a));
  }
}