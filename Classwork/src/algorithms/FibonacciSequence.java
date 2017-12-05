package algorithms;

public class FibonacciSequence {
	
	private final static void main(String[] arg) {
		System.out.print(FibonacciSequence(1));
	}
	
	private static int FibonacciSequence(int num) {
		if(num <= 1) {
			return 1;
		}
		return FibonacciSequence(num-1) + FibonacciSequence(num-2);
	}
}
