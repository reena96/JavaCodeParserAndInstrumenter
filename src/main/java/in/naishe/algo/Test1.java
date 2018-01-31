package in.naishe.algo;
public class Test1 {
    int j = 1, k =5;

    public static void main(String[] args) {
        int i = 0, m =9, a;
        while( i<m ) {
            i++;
        }
        a = i+m;
        new Test1().abc();
        System.out.println("Local Variable Declarations inside main() : "+ i +" , "+ m+" , "+a);
    }

    public void abc() {
        if (j == k+3) {
            int g = 45;
            System.out.println("Local Variable Declarations inside abc() : " + g);
        }
        j = j*k;
        System.out.println("Global Variable Declarations inside abc() :  "+ j + " , " + k);
    }
}