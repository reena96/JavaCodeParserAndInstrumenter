package in.naishe.algo;
public class Test {
    int j = 1, k =5;

    public static void main(String[] args) {
        int i = 0, m =9, a;
        while( i<m ) {
            i++;
        }
        a = i+m;
		Template.instrum("10", "ExpressionStatement", a, i, m,
				"in.naishe.algo.Test.main.a", "in.naishe.algo.Test.main.i",
				"in.naishe.algo.Test.main.m");
		Template.instrum("10", "ExpressionStatement", a, i, m,
				"in.naishe.algo.Test.main.a", "in.naishe.algo.Test.main.i",
				"in.naishe.algo.Test.main.m");
        new Test().abc();
        System.out.println("Local Variable Declarations inside main() : "+ i +" , "+ m+" , "+a);
    }

    public void abc() {
        if (j == k+3) {
            int g = 45;
            System.out.println("Local Variable Declarations inside abc() : " + g);
        }
        j = j*k;
		Template.instrum("23", "ExpressionStatement", j, j, k,
				"in.naishe.algo.Test.abc.j", "in.naishe.algo.Test.abc.j",
				"in.naishe.algo.Test.abc.k");
		Template.instrum("20", "ExpressionStatement", j, j, k,
				"in.naishe.algo.Test.abc.j", "in.naishe.algo.Test.abc.j",
				"in.naishe.algo.Test.abc.k");
        System.out.println("Global Variable Declarations inside abc() :  "+ j + " , " + k);
    }
}