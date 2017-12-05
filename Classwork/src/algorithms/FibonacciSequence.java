package algorithms;

public class FibonacciSequence {
	
	private final static void main(String[] arg) {
		System.out.print(FibonacciSequence(1));
	}
	
	private static int FibonacciSequence(int num) {
		if(num == 0) {
			return 1;
		}
		if(num < 0) {
			return 0;
		}
		else {
			return FibonacciSequence(num-1) + FibonacciSequence(num-2);
		}
	}
}
