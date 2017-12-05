package algorithms;

public class TowerOfHanoi {

		public final static void main(String[] args) {
			hanoi(8, "A", "B", "C");
		}
		
		public static void hanoi(int num, String start, String helper, String end) {
			if(num == 1) {
				System.out.println(start + " to " + end);
			}
			else {
				hanoi(num-1, start, end, helper);
				System.out.println(start + " to " + end);
				hanoi(num-1, helper, start, end);
			}
		}
}
