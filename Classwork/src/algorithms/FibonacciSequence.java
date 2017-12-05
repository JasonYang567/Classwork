package algorithms;

public class FibonacciSequence {
	
	public final static void main(String[] arg) {
		System.out.print(FibonacciSequence(1));
	}
	
	public static int FibonacciSequence(int num) {
		if(num <= 1) {
			return 1;
		}
		return FibonacciSequence(num-1) + FibonacciSequence(num-2);
	}
}
